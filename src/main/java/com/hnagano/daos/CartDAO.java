/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.daos;

import com.hnagano.databases.Database;
import com.hnagano.models.Cart;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryan
 */
public class CartDAO implements DAO<Cart>{
    private Database database;
    
    public void setDatabase(Database database) {
        this.database = database;
    }

    @Override
    public List<Cart> findAll() {
        List<Cart> carts = new LinkedList<Cart>();
        Cart cart;
        
        try {
            Statement stm = database.getInstance().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM carts");
            
            while (res.next()) {
                cart = objectBuilder(res);
                carts.add(cart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return carts;
    }

    @Override
    public Cart find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Cart cart) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("INSERT INTO carts(date_start, date_end, user_email)"
                        + " VALUES (?,?,?)");
            
            stm.setString(1, cart.getDateStart().toString());
            stm.setString(2, cart.getDateEnd().toString());
            stm.setString(3, cart.getEmail());
            
            int n = stm.executeUpdate();
            return n>0;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(Cart cart) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("DELETE FROM carts WHERE email = ?");
            
            stm.setString(1, cart.getEmail());
            
            int n = stm.executeUpdate();
            return n>0;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean update(Cart cart) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("UPDATE carts SET date_start = ?, date_end = ? WHERE email = ?");
            stm.setString(1, cart.getDateStart().toString());
            stm.setString(2, cart.getDateEnd().toString());
            stm.setString(3, cart.getEmail());
            
            int n = stm.executeUpdate();
            return n>0;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public Cart objectBuilder(ResultSet res) {
        Cart cart = new Cart();
        
        try {
            cart.setEmail(res.getString("email"));
            cart.setDateStart(res.getDate("date_start").toLocalDate());
            cart.setDateEnd(res.getDate("date_end").toLocalDate());
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cart;
    }
    
}
