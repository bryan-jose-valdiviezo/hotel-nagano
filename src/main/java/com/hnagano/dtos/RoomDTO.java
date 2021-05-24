/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.dtos;

/**
 *
 * @author Bryan
 */
public class RoomDTO {
    private int roomID;
    private int viewID;
    private int suiteID;
    private int floor;

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getViewID() {
        return viewID;
    }

    public void setViewID(int viewID) {
        this.viewID = viewID;
    }

    public int getSuiteID() {
        return suiteID;
    }

    public void setSuiteID(int suiteID) {
        this.suiteID = suiteID;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
    
    
}
