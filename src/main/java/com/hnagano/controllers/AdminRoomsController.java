/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.controllers;

import com.hnagano.dtos.AdminRoomSearchDTO;
import com.hnagano.dtos.RoomDTO;
import com.hnagano.models.Room;
import com.hnagano.modules.JsonWriter;
import com.hnagano.services.RoomServices;
import com.hnagano.services.SuiteServices;
import com.hnagano.services.ViewServices;
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
@RequestMapping("admin/rooms")
public class AdminRoomsController {
    private RoomServices roomServices;
    private ViewServices viewServices;
    private SuiteServices suiteServices;

    public void setRoomServices(RoomServices roomServices) {
        this.roomServices = roomServices;
    }

    public void setViewServices(ViewServices viewServices) {
        this.viewServices = viewServices;
    }

    public void setSuiteServices(SuiteServices suiteServices) {
        this.suiteServices = suiteServices;
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String Index(ModelMap model) {
        model.put("adminRoomSearchDTO", new AdminRoomSearchDTO());
        model.addAttribute("rooms", roomServices.findAllRooms());
        model.addAttribute("views", viewServices.getAllViews());
        model.addAttribute("suites", suiteServices.getAllSuites());
        return "rooms/admin/adminRoomsIndex";
    }
    
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public ModelAndView search(@ModelAttribute AdminRoomSearchDTO form, ModelMap model) {
        model.addAttribute("rooms", roomServices.findRoomsByFilter(form));
        model.addAttribute("views", viewServices.getAllViews());
        model.addAttribute("suites", suiteServices.getAllSuites());
        return new ModelAndView("rooms/admin/adminRoomsListPartial");
    }
    
    @RequestMapping(value="/new-room", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView newRoom(ModelMap model) {
        model.addAttribute("formID", "CreateRoom");
        model.addAttribute("views", viewServices.getAllViews());
        model.addAttribute("suites", suiteServices.getAllSuites());
        model.put("roomDTO", new RoomDTO());
        return new ModelAndView("rooms/admin/adminRoomsForm");
    }
    
    @RequestMapping(value="/create-room", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView createRoom(@ModelAttribute RoomDTO form, ModelMap model) {
        Room room = new Room();
        room.setView(viewServices.findView(form.getViewID()));
        room.setSuite(suiteServices.findSuite(form.getSuiteID()));
        room.setFloor(form.getFloor());
        
        Room newRoom = roomServices.createRoom(room);
        
        model.addAttribute("room", newRoom);
        return new ModelAndView("rooms/admin/adminRoomsDivPartial");
    }
    
    @RequestMapping(value="/modify-room", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView modify(ModelMap model, int roomID) {
        Room room = roomServices.findRoom(roomID);
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomID(room.getId());
        roomDTO.setViewID(room.getView().getId());
        roomDTO.setSuiteID(room.getSuite().getId());
        roomDTO.setFloor(room.getFloor());
        
        model.addAttribute("formID", "ModifyRoom");
        model.addAttribute("isModify", true);
        model.addAttribute("views", viewServices.getAllViews());
        model.addAttribute("suites", suiteServices.getAllSuites());
        model.put("roomDTO", roomDTO);
        return new ModelAndView("rooms/admin/adminRoomsForm");
    }
    
    @RequestMapping(value="/update-room", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateRoom(@ModelAttribute RoomDTO form, ModelMap model) {
        Room room = new Room();
        room.setId(form.getRoomID());
        room.setView(viewServices.findView(form.getViewID()));
        room.setSuite(suiteServices.findSuite(form.getSuiteID()));
        room.setFloor(form.getFloor());
        
        roomServices.updateRoom(room);
        
        room = roomServices.findRoom(form.getRoomID());
        
        model.addAttribute("room", room);
        return new ModelAndView("rooms/admin/adminRoomsRowPartial");
    }
    
    @RequestMapping(value="/delete-room", method = RequestMethod.POST)
    @ResponseBody
    public String deleteRoom(int roomID, ModelMap model) {
        JsonWriter writer = new JsonWriter();
        Room room = roomServices.findRoom(roomID);
        if (roomServices.deleteRoom(room))
            writer.AddMessage("roomDeleted", "deleted", true);
        else
            writer.AddMessage("error", "error deleting room", true);
        
        return writer.getMessage();
    }
}
