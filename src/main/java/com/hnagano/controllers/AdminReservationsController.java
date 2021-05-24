/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.controllers;

import com.hnagano.dtos.AdminReservationDTO;
import com.hnagano.dtos.ReservationSearchDTO;
import com.hnagano.models.Reservation;
import com.hnagano.models.Room;
import com.hnagano.modules.JsonWriter;
import com.hnagano.services.ReservationServices;
import com.hnagano.services.RoomServices;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Bryan
 */
@RequestMapping("admin/reservations")
public class AdminReservationsController {
    private ReservationServices reservationServices;
    private RoomServices roomServices;

    public void setReservationServices(ReservationServices reservationServices) {
        this.reservationServices = reservationServices;
    }

    public void setRoomServices(RoomServices roomServices) {
        this.roomServices = roomServices;
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.put("reservationSearchDTO", new ReservationSearchDTO());
        model.addAttribute("reservations", reservationServices.findAll());
        return "reservations/admin/adminReservationsIndex";
    }
    
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public ModelAndView search(@ModelAttribute ReservationSearchDTO form, ModelMap model) {
        model.addAttribute("reservations", reservationServices.findAllByFilter(form));
        return new ModelAndView("reservations/admin/adminReservationsList");
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") int id, ModelMap model) {
        Reservation reservation = reservationServices.findReservation(id);
        
        model.addAttribute("reservation", reservation);
        return "reservations/showReservation";
    }
    
    @RequestMapping(value="/{id}/modify-reservation", method = RequestMethod.GET)
    public ModelAndView modifyReservation(@PathVariable("id") int id, ModelMap model) {
        List<Integer> roomIDS = new ArrayList<Integer>();
        
        Reservation reservation = reservationServices.findReservation(id);
        ArrayList<Room> rooms = roomServices.findRoomsByDate(reservation.getDateStart(), reservation.getDateEnd());
        
        for (Room room : reservation.getRooms()) {
            rooms.add(room);
            roomIDS.add(room.getId());
        }

        AdminReservationDTO form = new AdminReservationDTO();
        form.setId(reservation.getId());
        form.setDateStart(reservation.getDateStart().toString());
        form.setDateEnd(reservation.getDateEnd().toString());
        form.setName(reservation.getName());
        form.setPrice(reservation.totalPrice());
        form.setCustomerCount(reservation.getCustomerCount());
        form.setSpecialInstructions(reservation.getSpecialInstructions());
        form.setRoomsID(roomIDS);
        
        model.put("adminReservationDTO", form);
        model.addAttribute("rooms", rooms);
        
        return new ModelAndView("reservations/admin/adminReservationForm");
    }
    
    @RequestMapping(value="/{id}/delete-reservation", method = RequestMethod.POST)
    public String deleteReservation(@PathVariable("id") int id) {
        Reservation reservation = reservationServices.findReservation(id);
        reservationServices.deleteReservation(reservation);        
        return "redirect:/admin/reservations/";
    }
    
    @RequestMapping(value="/update-reservation", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateReservation(@ModelAttribute AdminReservationDTO form, ModelMap model) {
        Reservation reservation = reservationServices.findReservation(form.getId());
        reservation.setCustomerCount(form.getCustomerCount());
        reservation.setSpecialInstructions(form.getSpecialInstructions());
        reservation.setName(form.getName());
        
        ArrayList<Room> rooms = new ArrayList<Room>();
        
        for (Integer id : form.getRoomsID()) {
            rooms.add(roomServices.findRoomWithPrices(id, reservation.getDateStart(), reservation.getDateEnd()));
        }
        
        reservation.setRooms(rooms);
        
        reservationServices.updateReservation(reservation);
        
        model.addAttribute("reservation", reservation);
        return new ModelAndView("reservations/reservationInfoPartial");
    }
    
    @RequestMapping(value="/change-reservation-price", method = RequestMethod.POST)
    @ResponseBody
    public String changePrice(
            @RequestParam("ids[]") Integer[] ids,
            @RequestParam("dateStart") String dateStart,
            @RequestParam("dateEnd") String dateEnd) {
        
        JsonWriter writer = new JsonWriter();
        double price = 0.0;
        
        LocalDate start = LocalDate.parse(dateStart);
        LocalDate end = LocalDate.parse(dateEnd);
        
        for (int i = 0 ; i < ids.length ; i++) {
            price += roomServices.findRoomWithPrices(ids[i], start, end).roomPrice();
        }
        writer.AddMessage("newPrice", Double.toString(price), false);
        return writer.getMessage();
    }
}
