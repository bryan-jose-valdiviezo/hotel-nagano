/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.daos;

import com.hnagano.databases.Database;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryan
 */
public class CartRoomDAO {
    private Database database;
    
    public void setDatabase(Database database) {
        this.database = database;
    }
    
    public boolean create(String email, int room_id) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("INSERT INTO cart_rooms(cart_email, room_id)"
                        + " VALUES (?,?)");
            stm.setString(1, email);
            stm.setInt(2, room_id);
            
            int n = stm.executeUpdate();
            return n>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(CartRoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean delete(String email, int room_id) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("DELETE FROM cart_rooms WHERE cart_email = ? AND room_id = ?");
            
            stm.setString(1, email);
            stm.setInt(2, room_id);
            
            int n = stm.executeUpdate();
            return n>0;
        } catch (SQLException ex) {
            Logger.getLogger(CartRoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
