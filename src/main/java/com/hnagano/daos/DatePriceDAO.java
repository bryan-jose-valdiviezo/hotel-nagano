/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.daos;

import com.hnagano.databases.Database;
import com.hnagano.models.DatePrice;
import com.hnagano.models.Room;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Bryan
 */
public class DatePriceDAO {
    private Database database;
    private ViewDAO viewDAO;
    private SuiteDAO suiteDAO;
    private DayDAO dayDAO;
    private SeasonDAO seasonDAO;
    
    public void setDatabase(Database database) {
        this.database = database;
    }

    public ViewDAO getViewDAO() {
        return viewDAO;
    }

    public void setViewDAO(ViewDAO viewDAO) {
        this.viewDAO = viewDAO;
    }

    public SuiteDAO getSuiteDAO() {
        return suiteDAO;
    }

    public void setSuiteDAO(SuiteDAO suiteDAO) {
        this.suiteDAO = suiteDAO;
    }

    public DayDAO getDayDAO() {
        return dayDAO;
    }

    public void setDayDAO(DayDAO dayDAO) {
        this.dayDAO = dayDAO;
    }

    public SeasonDAO getSeasonDAO() {
        return seasonDAO;
    }

    public void setSeasonDAO(SeasonDAO seasonDAO) {
        this.seasonDAO = seasonDAO;
    }
    
    public ArrayList<DatePrice> getTotalRoomPrice(Room room, LocalDate dateStart, LocalDate dateEnd) {
        ArrayList<DatePrice> datePrices = new ArrayList<DatePrice>();
        
        while (dateStart.isBefore(dateEnd) || dateStart.isEqual(dateEnd)) {
            datePrices.add(getRoomPriceByDate(dateStart, room));
            dateStart = dateStart.plusDays(1);
        }
        
        return datePrices;
    }
    
    public DatePrice getRoomPriceByDate(LocalDate date, Room room) {
        DatePrice datePrice = new DatePrice();
        datePrice.setDay(dayDAO.findDayPriceByDate(date));
        datePrice.setSeason(seasonDAO.findClosestDate(date));
        datePrice.setSuitePrice(suiteDAO.findLatestPriceByDate(date, room.getSuite().getId()));
        datePrice.setViewPrice(viewDAO.findLatestPriceByDate(date, room.getView().getId()));
        
        return datePrice;
    }
}
