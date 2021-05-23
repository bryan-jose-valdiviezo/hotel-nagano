/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.services;

import com.hnagano.daos.DayDAO;
import com.hnagano.models.Day;
import com.hnagano.models.DayPrice;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Bryan
 */
public class DayServices {
    private DayDAO dao;

    public void setDao(DayDAO dao) {
        this.dao = dao;
    }
    
    public List<Day> getAllDayType() {
        return dao.findAll();
    }
    
    public List<DayPrice> findCurrentDaysAndPrices() {
        List<Day> days = dao.findAll();
        List<DayPrice> dayPrices = new LinkedList<DayPrice>();
        
        for (Day day : days) {
            dayPrices.add(dao.findWithCurrentPrice(day.getId()));
        }
        
        return dayPrices;
    }
    
    public List<DayPrice> findUpcomingDaysAndPrices(){
        return dao.findAllDayWithPrices();
    }
    
    public boolean createDayPrice(DayPrice dayPrice) {
        return dao.createDayPrice(dayPrice);
    }
}
