/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.models;

import java.time.LocalDate;

/**
 *
 * @author Bryan
 */
public class ViewPrice extends View{
    private LocalDate dateStart;
    private double price;
    
    public ViewPrice(View view) {
        setId(view.getId());
        setName(view.getName());
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
