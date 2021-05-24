/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.controllers;

import com.hnagano.services.ReservationServices;
import com.hnagano.dtos.ReservationSearchDTO;
import com.hnagano.models.Reservation;
import com.hnagano.models.User;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Bryan
 */

@Controller
public class IndexController {
    private ReservationServices reservationServices;

    public void setReservationServices(ReservationServices reservationServices) {
        this.reservationServices = reservationServices;
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String welcome(ModelMap model, HttpSession session) {
        
        User user = (User) session.getAttribute("user");
        if (user != null && user.getRole().equals("ADMIN")) {
            return "redirect:/admin/";
        }
        
        model.put("reservationSearchDTO", new ReservationSearchDTO());
        return "mainPage";
    }
    
    @RequestMapping(value="/", method = RequestMethod.POST)
    public String findReservation(@ModelAttribute ReservationSearchDTO form, HttpSession session) {
        Reservation reservation = reservationServices.findByIdAndEmail(form.getId(), form.getEmail());
        
        if (reservation != null) {
            session.setAttribute("reservationPermission", true);
            return "redirect:/reservations/" + reservation.getId();
        }
        
        return "redirect:/";
    }
}

