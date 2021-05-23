/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.services;

import com.hnagano.daos.RoomDAO;
import com.hnagano.dtos.RoomSearchDTO;
import com.hnagano.models.Room;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan
 */
public class RoomServices {
    private RoomDAO dao;
    
    public void setDao(RoomDAO dao) {
        this.dao = dao;
    }
    
    public Room findRoom(int id) {
        return dao.find(id);
    }
    
    public ArrayList<Room> getAvailableRoomsForFilter(RoomSearchDTO filter){
        return dao.findAllAvailableRoomsByFilter(filter);
    }
    
    public int getTodayAvailableRooms() {
        return dao.findNumberAvailableRoomToday();
    }
    
    public List<Room> findAllRooms() {
        return dao.findAll();
    }
}
