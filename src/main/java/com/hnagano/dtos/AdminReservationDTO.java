/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan
 */
public class AdminReservationDTO {
    private int id;
    private String name;
    private String dateStart;
    private String dateEnd;
    private int customerCount;
    private double price;
    private String specialInstructions;
    private List<Integer> roomsID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public List<Integer> getRoomsID() {
        return roomsID;
    }

    public void setRoomsID(List<Integer> roomsID) {
        this.roomsID = roomsID;
    }
}
