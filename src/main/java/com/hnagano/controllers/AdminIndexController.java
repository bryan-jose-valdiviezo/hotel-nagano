package com.hnagano.controllers;

import com.hnagano.services.ReservationServices;
import com.hnagano.services.RoomServices;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bryan
 */
@RequestMapping("admin")
public class AdminIndexController {
    private RoomServices roomServices;
    private ReservationServices reservationServices;

    public void setRoomServices(RoomServices roomServices) {
        this.roomServices = roomServices;
    }

    public void setReservationServices(ReservationServices reservationServices) {
        this.reservationServices = reservationServices;
    }
    
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String Index(ModelMap model) {
        int totalRooms = roomServices.findAllRooms().size();
        int currentAvailableRooms = roomServices.getTodayAvailableRooms();
        double percentCapacity = 100.0 / totalRooms * currentAvailableRooms;
        
        model.addAttribute("totalRooms", totalRooms);
        model.addAttribute("percentCapacity", percentCapacity);
        model.addAttribute("todayIncome", reservationServices.findTodayIncome());
        model.addAttribute("todayAvailableRooms", currentAvailableRooms);
        return "mainPage";
    }
}
