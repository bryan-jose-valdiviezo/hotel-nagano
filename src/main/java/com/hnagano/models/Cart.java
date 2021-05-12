/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.models;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Bryan
 */
public class Cart {
    private String email;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private ArrayList<Room> rooms;
    
    public Cart () {
        rooms = new ArrayList<Room>();
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
    
    public String updateCartWithButtonStatus(Room room, LocalDate dateStart, LocalDate dateEnd) {        
        if (rooms.size() == 0) {
            changeCartDate(dateStart, dateEnd, false);
            rooms.add(room);
            return "Cancel";
        } 
        
        else {
            for (int i = 0; i < rooms.size() ; i++) {
                if (rooms.get(i).getId() == room.getId()) {
                    rooms.remove(i);
                    if (rooms.size() == 0)
                        changeCartDate(null, null, true);
                    
                    return "Book";
                }              
            }
            
            rooms.add(room);
            return "Cancel";
        }             
    }
    
    public void RemoveFromCartByID(int id) {
        for (int i = 0 ; i < rooms.size() ; i ++) {
            if (rooms.get(i).getId() == id) {
                rooms.remove(i);
                if (rooms.size() == 0)
                        changeCartDate(null, null, true);
                break;
            }    
        }
    }
    
    public boolean isRoomInCart(int id) {
        for (int i = 0; i < rooms.size() ; i++) {
            if (rooms.get(i).getId() == id)
                return true;
        }
        
        return false;
    }
    
    public void changeCartDate(LocalDate dateStart, LocalDate dateEnd, boolean doEmpty) {
        if (doEmpty) {
            this.dateEnd = null;
            this.dateStart = null;
        } else {
            this.dateStart = dateStart;
            this.dateEnd = dateEnd;
        }
    }
    
    public double totalPrice(){
        double totalPrice = 0.0;
        
        for (Room room : rooms) {
            totalPrice += room.roomPrice();
        }
        
        return totalPrice;
    }
}
