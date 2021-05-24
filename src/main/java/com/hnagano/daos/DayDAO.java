/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.daos;

import com.hnagano.databases.Database;
import com.hnagano.models.Day;
import com.hnagano.models.DayPrice;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryan
 */
public class DayDAO implements DAO<Day>{
    private Database database;
    
    public void setDatabase(Database database) {
        this.database = database;
    }

    @Override
    public List<Day> findAll() {
        List<Day> days = new LinkedList<Day>();
        Day day;
        
        try {
            Statement stm = database.getInstance().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM days");
            
            while(res.next()) {
                day = objectBuilder(res);
                days.add(day);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return days;
    }
    
    public List<DayPrice> findAllWithPrices() {
        List<DayPrice> dayPrices = new LinkedList<DayPrice>();
        DayPrice dayPrice;
        
        try {
            Statement stm = database.getInstance().createStatement();
            ResultSet res = stm.executeQuery("SELECT days.id, day_type, start_at, price FROM days INNER JOIN day_prices ON days.id = day_prices.day_type_id");
            
            while(res.next()) {
                dayPrice = (DayPrice) objectBuilder(res);
                dayPrice.setDateStart(res.getDate("start_at").toLocalDate());
                dayPrice.setPrice(res.getDouble("price"));
                
                dayPrices.add(dayPrice);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dayPrices;
    }
    
    public List<DayPrice> findAllDayWithPrices() {
        List<DayPrice> dayPrices = new LinkedList<DayPrice>();
        DayPrice dayPrice;
        
        try {
            Statement stm = database.getInstance().createStatement();
            ResultSet res = stm.executeQuery("SELECT days.id, day_type, start_at, price FROM days INNER JOIN day_prices ON days.id = day_prices.day_type_id ORDER BY start_at DESC");
            
            while(res.next()) {
                dayPrice = new DayPrice(objectBuilder(res));
                dayPrice.setDateStart(res.getDate("start_at").toLocalDate());
                dayPrice.setPrice(res.getDouble("price"));
                
                dayPrices.add(dayPrice);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dayPrices;
    }

    @Override
    public Day find(int id) {
        Day day = null;
        
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT * FROM days WHERE id = ?");
            stm.setInt(1, id);
            
            ResultSet res = stm.executeQuery();
            
            if (res.next())
                day = objectBuilder(res);
            
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return day;
    }
    
    public DayPrice findWithCurrentPrice(int id) {
        DayPrice dayPrice = null;
        
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT days.id, day_type, start_at, price FROM days "+
                    "INNER JOIN day_prices ON days.id = day_prices.day_type_id "+
                    "WHERE start_at <= CURRENT_DATE AND days.id = ? ORDER BY start_at DESC LIMIT 1");
            
            stm.setInt(1, id);
            
            ResultSet res = stm.executeQuery();
            
            if (res.next()) {
                dayPrice = new DayPrice(objectBuilder(res));
                dayPrice.setDateStart(res.getDate("start_at").toLocalDate());
                dayPrice.setPrice(res.getDouble("price"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return dayPrice;
    }
    
    public DayPrice findDayPriceByDate(LocalDate date) {
        DayPrice dayPrice = null;
        String dayType = "weekday";
        
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)
            dayType = "weekend";
        
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT days.id, day_type, start_at, price FROM days "+
                    "INNER JOIN day_prices ON days.id = day_prices.day_type_id "+
                    "WHERE start_at <= ? AND day_type LIKE ? ORDER BY start_at DESC LIMIT 1");
            
            stm.setDate(1, Date.valueOf(date));
            stm.setString(2, dayType);
            
            ResultSet res = stm.executeQuery();
            
            if (res.next()) {
                dayPrice = new DayPrice(objectBuilder(res));
                dayPrice.setDateStart(res.getDate("start_at").toLocalDate());
                dayPrice.setPrice(res.getDouble("price"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return dayPrice;
    }

    @Override
    public boolean create(Day day) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("INSERT INTO days (day_type) VALUES (?)");
            stm.setString(1, day.getDayType());
            
            int n = stm.executeUpdate();
            
            return n>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean createDayPrice(DayPrice dayPrice) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("INSERT INTO day_prices (day_type_id, start_at, price) VALUES (?,?,?)");
            stm.setInt(1, dayPrice.getId());
            stm.setDate(2, Date.valueOf(dayPrice.getDateStart()));
            stm.setDouble(3, dayPrice.getPrice());
            
            int n = stm.executeUpdate();
            
            return n>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(Day t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Day t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Day objectBuilder(ResultSet res) {
        Day day = new Day();
        try {
            day.setId(res.getInt("id"));
            day.setDayType(res.getString("day_type"));
            
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return day;
    }
    
}
