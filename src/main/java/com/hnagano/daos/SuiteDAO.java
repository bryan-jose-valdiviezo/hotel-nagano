/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.daos;

import com.hnagano.databases.Database;
import com.hnagano.models.Suite;
import com.hnagano.models.SuitePrice;
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
public class SuiteDAO implements DAO<Suite>{
    private Database database;
    
    public void setDatabase(Database database) {
        this.database = database;
    }
    
    public SuitePrice findLatestPriceByDate(LocalDate date, int suite_id) {
        SuitePrice suitePrice = null;
        
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT suite.id, suite_type, start_at, price FROM suite "+
                    "INNER JOIN suite_prices ON suite.id = suite_prices.suite_id "+
                    "WHERE suite.id = ? AND start_at <= ? "+
                    "ORDER BY start_at DESC LIMIT 1");
            stm.setInt(1, suite_id);
            stm.setDate(2, Date.valueOf(date));
            
            ResultSet res = stm.executeQuery();
            
            if (res.next())
                suitePrice = new SuitePrice(objectBuilder(res));
                suitePrice.setPrice(res.getDouble("price"));
                suitePrice.setDateStart(res.getDate("start_at").toLocalDate());
                
            
        } catch (SQLException ex) {
            Logger.getLogger(ViewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return suitePrice;
    }
    
    public List<SuitePrice> findAllPricesForSuite(int id) {
        List<SuitePrice> suitePrices = new LinkedList<SuitePrice>();
        SuitePrice suitePrice;
        
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT suite.id, suite_type, start_at, price FROM suite "+
                    "INNER JOIN suite_prices ON suite.id = suite_prices.suite_id "+
                    "WHERE suite.id = ?"+
                    "ORDER BY suite_prices.suite_id DESC LIMIT 1");
            stm.setInt(1, id);
            
            ResultSet res = stm.executeQuery();
            
            while (res.next()) {
                suitePrice = new SuitePrice(objectBuilder(res));
                suitePrices.add(suitePrice);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ViewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return suitePrices;
    }

    @Override
    public List<Suite> findAll() {
        List<Suite> suites = new LinkedList<Suite>();
        Suite suite;
        
        try {
            Statement stm = database.getInstance().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM suite");
            
            while(res.next()) {
                suite = objectBuilder(res);
                suites.add(suite);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return suites;
    }

    @Override
    public Suite find(int id) {
        Suite suite = null;
        
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT * FROM suite WHERE id = ?");
            
            stm.setInt(1, id);
            
            ResultSet res = stm.executeQuery();
            
            if (res.next()) {
                suite = objectBuilder(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return suite;
    }

    @Override
    public boolean create(Suite suite) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("INSERT INTO suite (suite_type) VALUES (?)");
            
            stm.setString(1, suite.getName());
            
            int n = stm.executeUpdate();
            return n>0;
        } catch (SQLException ex) {
            Logger.getLogger(SuiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean createSuitePrice(SuitePrice suitePrice) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("INSERT INTO suite_prices (suite_id, start_at, price) VALUES (?,?,?)");
            stm.setInt(1, suitePrice.getId());
            stm.setDate(2, Date.valueOf(suitePrice.getDateStart()));
            stm.setDouble(3, suitePrice.getPrice());
            
            int n = stm.executeUpdate();
            return n>0;
        } catch (SQLException ex) {
            Logger.getLogger(SuiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(Suite suite) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("DELETE FROM suite WHERE id = ?");
            
            stm.setInt(1, suite.getId());
            
            int n = stm.executeUpdate();
            return n>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(SuiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean update(Suite suite) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("UPDATE suite SET suite_type = ? WHERE id = ?");
            
            stm.setString(1, suite.getName());
            stm.setInt(2, suite.getId());
            
            int n = stm.executeUpdate();
            return n>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(SuiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public Suite objectBuilder(ResultSet res) {
        Suite suite = new Suite();
        try {
            suite.setId(res.getInt("id"));
            suite.setName(res.getString("suite_type"));
        
        } catch (SQLException ex) {
            Logger.getLogger(SuiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return suite;
    }
    
}
