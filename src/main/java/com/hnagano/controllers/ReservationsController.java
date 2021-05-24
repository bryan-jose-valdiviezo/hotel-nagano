/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.controllers;

import java.time.LocalDate;
import com.hnagano.dtos.ReservationDTO;
import com.hnagano.models.Cart;
import com.hnagano.models.Reservation;
import com.hnagano.models.Room;
import com.hnagano.models.User;
import com.hnagano.modules.JsonWriter;
import com.hnagano.services.DatePriceServices;
import com.hnagano.services.ReservationServices;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Bryan
 */
@Controller
@RequestMapping("reservations")
public class ReservationsController {
    private DatePriceServices datePriceServices;
    private ReservationServices reservationServices;

    public void setDatePricesServices(DatePriceServices datePriceServices) {
        this.datePriceServices = datePriceServices;
    }

    public void setReservationServices(ReservationServices reservationServices) {
        this.reservationServices = reservationServices;
    }
    
    @RequestMapping(value="/new", method = RequestMethod.GET)
    public String newReservation(ModelMap model, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        ReservationDTO form = new ReservationDTO();
        
        if (user != null) {
            form.setName(user.getName());
            form.setPhone(user.getPhone());
            form.setEmail(user.getEmail());
            form.setAddress(user.getAddress());
        }
        
        if (cart != null && cart.getDateStart() != null) {
            for (Room room : cart.getRooms()) {
                room.setDatePrices(datePriceServices.findRoomPriceByDateRange(room, cart.getDateStart(), cart.getDateEnd()));
            }
            
            form.setDateStart(cart.getDateStart().toString());
            form.setDateEnd(cart.getDateEnd().toString());
            form.setPrice(cart.totalPrice());
        }
        
        model.put("reservationDTO", form);
        
        return "reservations/formPage";
    }
    
    @RequestMapping(value="/new", method = RequestMethod.POST)
    public String createReservation(@ModelAttribute ReservationDTO form, HttpSession session, ModelMap model) {
        System.out.println("Creating reservation");
        Cart cart = (Cart) session.getAttribute("cart");
        
        Reservation reservation = new Reservation();
        reservation.setCustomerCount(form.getCustomerCount());
        reservation.setDateStart(LocalDate.parse(form.getDateStart()));
        reservation.setDateEnd(LocalDate.parse(form.getDateEnd()));
        reservation.setEmail(form.getEmail());
        reservation.setName(form.getName());
        reservation.setPhone(form.getPhone());
        reservation.setSpecialInstructions(form.getSpecialInstructions());
        reservation.setRooms(cart.getRooms());
        
        int reservationMade = reservationServices.createReservation(reservation);
        System.out.println("Create reservation id: "+ reservationMade);
        if (reservationMade > 0) {
            session.removeAttribute("cart");
            return "redirect:/reservations/"+ reservationMade;
        }
        
        model.put("reservationDTO", form);
        
        return "reservations/formPage";
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String showReservation(@PathVariable("id") int id, ModelMap model, HttpSession session) {
        
        if (session.getAttribute("reservationPermission") != null)
            session.removeAttribute("reservationPermission");
        else
            return "redirect:/";
        
        Reservation reservation = reservationServices.findReservation(id);
        model.addAttribute("reservation", reservation);
        
       return "reservations/showReservation"; 
    }
    
    @RequestMapping(value="/remove-from-cart", method = RequestMethod.POST)
    @ResponseBody
    public String updateCart(int roomID, HttpSession session) {
        JsonWriter writer = new JsonWriter();
        Cart cart = (Cart) session.getAttribute("cart");
        Room room = new Room();
        room.setId(roomID);
        
        cart.RemoveFromCartByID(roomID);
        writer.AddMessage("newPrice", Double.toString(cart.totalPrice()), false);
        writer.AddMessage("cartCount", Integer.toString(cart.getRooms().size()), false);
        return writer.getMessage();
    }
}
