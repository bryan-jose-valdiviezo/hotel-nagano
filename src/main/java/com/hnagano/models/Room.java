/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.models;

import java.util.ArrayList;

/**
 *
 * @author Bryan
 */
public class Room {
    private int id;
    private int floor;
    private Suite suite;
    private View view;
    private ArrayList<DatePrice> datePrices;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Suite getSuite() {
        return suite;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public ArrayList<DatePrice> getDatePrices() {
        return datePrices;
    }

    public void setDatePrices(ArrayList<DatePrice> datePrices) {
        this.datePrices = datePrices;
    }
    
    public double roomPrice(){
        double price = 0;
        
        for (DatePrice datePrice : datePrices) {
            price += datePrice.getDay().getPrice();
            price += datePrice.getSuitePrice().getPrice();
            price += datePrice.getViewPrice().getPrice();
            
            if (datePrice.getSeason() != null)
                price += datePrice.getSeason().getPrice();
        }
        
        return price;
    }
}
