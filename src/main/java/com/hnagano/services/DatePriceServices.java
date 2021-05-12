/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.services;

import com.hnagano.daos.DatePriceDAO;
import com.hnagano.models.DatePrice;
import com.hnagano.models.Room;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan
 */
public class DatePriceServices {
    private DatePriceDAO dao;

    public void setDao(DatePriceDAO dao) {
        this.dao = dao;
    }

    public ArrayList<DatePrice> findRoomPriceByDateRange(Room room, LocalDate dateStart, LocalDate dateEnd) {
        ArrayList<DatePrice> datePrices = new ArrayList<DatePrice>();
        
        while (dateStart.isBefore(dateEnd) || dateStart.isEqual(dateEnd)) {
            datePrices.add(dao.getRoomPriceByDate(dateStart, room));
            dateStart = dateStart.plusDays(1);
        }
        
        return datePrices;
    }
}
