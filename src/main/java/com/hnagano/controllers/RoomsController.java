/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.controllers;

import com.hnagano.dtos.RoomSearchDTO;
import com.hnagano.models.Cart;
import com.hnagano.models.Room;
import com.hnagano.models.Suite;
import com.hnagano.models.View;
import com.hnagano.modules.JsonWriter;
import com.hnagano.services.RoomServices;
import com.hnagano.services.SuiteServices;
import com.hnagano.services.ViewServices;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Bryan
 */

@Controller
@RequestMapping("rooms")
public class RoomsController {
    private ViewServices viewServices;
    private SuiteServices suiteServices;
    private RoomServices roomServices;

    public void setViewServices(ViewServices viewServices) {
        this.viewServices = viewServices;
    }

    public void setSuiteServices(SuiteServices suiteServices) {
        this.suiteServices = suiteServices;
    }

    public void setRoomServices(RoomServices roomServices) {
        this.roomServices = roomServices;
    }
    
    
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String welcome(ModelMap model, HttpSession session) {
        RoomSearchDTO roomSearchDTO = new RoomSearchDTO();
        
        Cart cart = (Cart) session.getAttribute("cart");
        
        if (cart != null && cart.getDateStart() != null) {
            roomSearchDTO.setDateStart(cart.getDateStart().toString());
            roomSearchDTO.setDateEnd(cart.getDateEnd().toString());
        } else {
            roomSearchDTO.setDateStart(LocalDate.now().toString());
            roomSearchDTO.setDateEnd(LocalDate.now().plusDays(1).toString());
        }

        roomSearchDTO.setFloor(0);
        roomSearchDTO.setSuiteID(0);
        roomSearchDTO.setViewID(0);
               
        List<View> views = viewServices.getAllViews();
        List<Suite> suites = suiteServices.getAllSuites();
        List<Room> rooms = roomServices.getAvailableRoomsForFilter(roomSearchDTO);
        
        model.put("roomSearchDTO", roomSearchDTO);
        model.addAttribute("rooms", rooms);
        model.addAttribute("suites", suites);
        model.addAttribute("views",views);
        
        return "rooms/roomsSearch";
    }
    
    @RequestMapping(value="/search", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView searchAvailableRooms(@ModelAttribute RoomSearchDTO form, ModelMap model) {
        List<Room> rooms = roomServices.getAvailableRoomsForFilter(form);
        
        
        model.addAttribute("rooms", rooms);
        
        return new ModelAndView("rooms/roomListPartial");
    }
    
    @RequestMapping(value="/book-in", method = RequestMethod.POST)
    @ResponseBody
    public String bookIn(HttpSession session, String dateStart, String dateEnd, int roomID) {
        JsonWriter writer = new JsonWriter();
        Room room = roomServices.findRoom(roomID);
        
        if (session.getAttribute("cart") == null)
            session.setAttribute("cart", new Cart());
        
        Cart cart = (Cart) session.getAttribute("cart");
        
        
        String buttonText = cart.updateCartWithButtonStatus(room, LocalDate.parse(dateStart), LocalDate.parse(dateEnd));
        
        writer.AddMessage("cartAmount", Integer.toString(cart.getRooms().size()), false);
        writer.AddMessage("buttonText", buttonText, true);
        
        return writer.getMessage();
    }
}
