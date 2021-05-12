/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.daos;

import com.hnagano.databases.Database;
import com.hnagano.models.Suite;
import com.hnagano.models.View;
import com.hnagano.models.ViewPrice;
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
public class ViewDAO implements DAO<View>{
    private Database database;
    
    public void setDatabase(Database database) {
        this.database = database;
    }
    
    public ViewPrice findLatestPriceByDate(LocalDate date, int view_id) {
        ViewPrice viewPrice = null;
        
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT views.id, view_type, start_at, price FROM views "+
                    "INNER JOIN view_prices ON views.id = view_prices.view_id "+
                    "WHERE start_at <= ? AND views.id = ? ORDER BY start_at DESC LIMIT 1");
            stm.setDate(1, Date.valueOf(date));
            stm.setInt(2, view_id);
            
            ResultSet res = stm.executeQuery();
            
            if (res.next()) {
                viewPrice = new ViewPrice(objectBuilder(res));
                viewPrice.setDateStart(res.getDate("start_at").toLocalDate());
                viewPrice.setPrice(res.getDouble("price"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ViewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return viewPrice;
    }

    @Override
    public List<View> findAll() {
        List<View> views = new LinkedList<View>();
        View view;
        
        try {
            Statement stm = database.getInstance().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM views");
            
            while(res.next()) {
                view = objectBuilder(res);
                views.add(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return views;
    }
    
    public List<ViewPrice> findAllViewPricesForView(int id) {
        List<ViewPrice> viewPrices = new LinkedList<ViewPrice>();
        ViewPrice viewPrice;
        
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT views.id, view_type, start_at, price FROM views "+
                    "INNER JOIN view_prices ON views.id = view_prices.view_id "+
                    "WHERE views_id = ? ORDER BY view_prices.id DESC;");
            ResultSet res = stm.executeQuery();
            
            while (res.next()) {
                viewPrice = new ViewPrice(objectBuilder(res));
                viewPrice.setDateStart(res.getDate("start_at").toLocalDate());
                viewPrice.setPrice(res.getDouble("price"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ViewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return viewPrices;
    }

    @Override
    public View find(int id) {
        View view = null;
        
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT * FROM views WHERE id = ?");
            
            stm.setInt(1, id);
            
            ResultSet res = stm.executeQuery();
            
            if (res.next()) {
                view = objectBuilder(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return view;
    }

    @Override
    public boolean create(View view) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("INSERT INTO views (view_type) VALUES (?)");
            
            stm.setString(1, view.getName());
            
            int n = stm.executeUpdate();
            return n>0;
        } catch (SQLException ex) {
            Logger.getLogger(SuiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean createViewPrice(ViewPrice viewPrice) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("INSERT INTO view_prices (view_id, start_at, price) VALUES (?,?,?)");
            stm.setInt(1, viewPrice.getId());
            stm.setDate(2, Date.valueOf(viewPrice.getDateStart()));
            stm.setDouble(3, viewPrice.getPrice());
            
            int n = stm.executeUpdate();
            
            return n>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(SuiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(View view) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("DELETE FROM views WHERE id = ?");
            
            stm.setInt(1, view.getId());
            
            int n = stm.executeUpdate();
            return n>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ViewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean update(View view) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("UPDATE views SET view_type = ? WHERE id = ?");
            
            stm.setString(1, view.getName());
            stm.setInt(2, view.getId());
            
            int n = stm.executeUpdate();
            return n>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ViewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public View objectBuilder(ResultSet res) {
        View view = new View();
        try {
            view.setId(res.getInt("id"));
            view.setName(res.getString("view_type"));
        } catch (SQLException ex) {
            Logger.getLogger(ViewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return view;
    }
    
}
