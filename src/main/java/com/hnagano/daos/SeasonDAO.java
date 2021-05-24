/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.daos;

import com.hnagano.databases.Database;
import com.hnagano.models.Season;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryan
 */
public class SeasonDAO implements DAO<Season>{
    private Database database;
    
    public void setDatabase(Database database) {
        this.database = database;
    }
    
    public Season findClosestDate(LocalDate date) {
        Season season = null;
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT * FROM seasons WHERE date_start <= ? AND date_end >= ?");
            stm.setString(1, date.toString());
            stm.setString(2, date.toString());
            
            ResultSet res = stm.executeQuery();
            
            if (res.next()) {
                season = objectBuilder(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeasonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return season;
    }

    @Override
    public List<Season> findAll() {
        List<Season> seasons = new LinkedList<Season>();
        
        try {
            Statement stm = database.getInstance().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM seasons ORDER BY date_start DESC");
            
            while(res.next()) {
                seasons.add(objectBuilder(res));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeasonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return seasons;
    }

    @Override
    public Season find(int id) {
        Season season = null;
        
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT * FROM seasons WHERE id = ?");
            ResultSet res = stm.executeQuery();
            
            if (res.next())
                season = objectBuilder(res);
        } catch (SQLException ex) {
            Logger.getLogger(SeasonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return season;
    }

    @Override
    public boolean create(Season season) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("INSERT INTO seasons(price, date_start, date_end, event) VALUES (?,?,?,?)");
            stm.setDouble(1, season.getPrice());
            stm.setDate(2, Date.valueOf(season.getDateStart()));
            stm.setDate(3, Date.valueOf(season.getDateEnd()));
            stm.setString(4, season.getEvent());
            
            int n = stm.executeUpdate();
            return n>0;
        
        } catch (SQLException ex) {
            Logger.getLogger(SeasonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(Season season) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("DELETE FROM seasons WHERE id = ?");
            stm.setInt(1, season.getId());
            
            int n = stm.executeUpdate();
            return n>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(SeasonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean update(Season season) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("UPDATE seasons SET date_start = ?, date_end = ?, price = ?, event = ? WHERE id = ?");
            stm.setDate(1, Date.valueOf(season.getDateStart()));
            stm.setDate(2, Date.valueOf(season.getDateEnd()));
            stm.setDouble(3, season.getPrice());
            stm.setString(4, season.getEvent());
            stm.setInt(5, season.getId());
            
            int n = stm.executeUpdate();
            return n>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(SeasonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public Season objectBuilder(ResultSet res) {
        Season season = new Season();
        
        try {
            season.setId(res.getInt("id"));
            season.setPrice(res.getDouble("price"));
            season.setDateStart(res.getDate("date_start").toLocalDate());
            season.setDateEnd(res.getDate("date_end").toLocalDate());
            season.setEvent(res.getString("event"));
            
        } catch (SQLException ex) {
            Logger.getLogger(SeasonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return season;
    }
}
