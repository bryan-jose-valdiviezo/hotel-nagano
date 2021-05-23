/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.daos;

import com.hnagano.databases.Database;
import com.hnagano.models.Reservation;
import com.hnagano.models.Room;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryan
 */
public class ReservationDAO implements DAO<Reservation>{
    private Database database;
    private RoomDAO roomDAO;
    
    public void setDatabase(Database database) {
        this.database = database;
    }

    public void setRoomDAO(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new LinkedList<Reservation>();
        
        try {
            Statement stm = database.getInstance().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM reservations");
            
            while (res.next())
                reservations.add(objectBuilder(res));
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservations;
    }   

    @Override
    public Reservation find(int id) {
        Reservation reservation = null;
        
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT * FROM reservations WHERE id = ?");
            stm.setInt(1, id);
            
            ResultSet res = stm.executeQuery();
            
            if (res.next())
                reservation = objectBuilder(res);
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservation;
    }
    
    public Reservation findWithRooms(int id, boolean withPrices) {
        Reservation reservation = find(id);
        reservation.setRooms(roomDAO.findAllForReservation(reservation, withPrices));
        
        return reservation;
    }
    
    public List<Reservation> findAllWithRooms(boolean withPrices) {
        List<Reservation> reservations = findAll();
        for (int i = 0; i < reservations.size() ; i++) {
            reservations.get(i).setRooms(roomDAO.findAllForReservation(reservations.get(i), withPrices));
        }
        
        return reservations;
    }
    
    public Reservation findByIdAndEmail(int id, String email) {
        Reservation reservation = null;
        
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT * FROM reservations WHERE id = ? AND email LIKE ?");
            stm.setInt(1, id);
            stm.setString(2, email);
            
            ResultSet res = stm.executeQuery();
            
            if (res.next()) {
                reservation = objectBuilder(res);
                reservation.setRooms(roomDAO.findAllForReservation(reservation, true));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservation;
    }
    
    public ArrayList<Reservation> findAllMadeToday() {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        Reservation reservation;
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT * FROM reservations WHERE creation_date = CURRENT_DATE");
            ResultSet res = stm.executeQuery();
            
            if (res.next()) {
                reservation = objectBuilder(res);
                reservation.setRooms(roomDAO.findAllForReservation(reservation, true));
                reservations.add(reservation);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservations;
    }

    @Override
    public boolean create(Reservation reservation) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("INSERT INTO reservations (customer_count, date_start, date_end, special_instructions, email, name, phone) "
                    + "VALUES (?,?,?,?,?,?,?)");
            
            stm.setInt(1, reservation.getCustomerCount());
            stm.setDate(2, Date.valueOf(reservation.getDateStart()));
            stm.setDate(3, Date.valueOf(reservation.getDateEnd()));
            stm.setString(4, reservation.getSpecialInstructions());
            stm.setString(5, reservation.getEmail());
            stm.setString(6, reservation.getName());
            stm.setInt(7, reservation.getPhone());
            
            int n = stm.executeUpdate();
            
            if (n>0) {
                int id = stm.getGeneratedKeys().getInt(1);
                
                for (Room room : reservation.getRooms()) {
                    createReservationRoomLink(id, room.getId());
                }
                
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public int createReservationWithReturn(Reservation reservation) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("INSERT INTO reservations (customer_count, date_start, date_end, creation_date, special_instructions, email, name, phone) "
                    + "VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            
            stm.setInt(1, reservation.getCustomerCount());
            stm.setDate(2, Date.valueOf(reservation.getDateStart()));
            stm.setDate(3, Date.valueOf(reservation.getDateEnd()));
            stm.setDate(4, Date.valueOf(LocalDate.now()));
            stm.setString(5, reservation.getSpecialInstructions());
            stm.setString(6, reservation.getEmail());
            stm.setString(7, reservation.getName());
            stm.setInt(8, reservation.getPhone());
            
            int n = stm.executeUpdate();
            
            if (n>0) {
                ResultSet res = stm.getGeneratedKeys();
                res.next();
                int id = res.getInt(1);
                
                for (Room room : reservation.getRooms()) {
                    createReservationRoomLink(id, room.getId());
                }
                
                return id;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    public boolean createReservationRoomLink(int reservationID, int roomID) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("INSERT INTO reservation_rooms (room_id, reservation_id) VALUES (?,?)");
            stm.setInt(1, roomID);
            stm.setInt(2, reservationID);
            
            int n = stm.executeUpdate();
            
            return n>0;
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(Reservation reservation) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("DELETE FROM reservations WHERE id = ?");
            stm.setInt(1, reservation.getId());
            
            int n = stm.executeUpdate();
            
            return n>0;
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean update(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservation objectBuilder(ResultSet res) {
        Reservation reservation = new Reservation();
        
        try {
            reservation.setId(res.getInt("id"));
            reservation.setCustomerCount(res.getInt("customer_count"));
            reservation.setDateStart(res.getDate("date_start").toLocalDate());
            reservation.setDateEnd(res.getDate("date_end").toLocalDate());
            reservation.setCreationDate(res.getDate("creation_date").toLocalDate());
            reservation.setSpecialInstructions(res.getString("special_instructions"));
            reservation.setEmail(res.getString("email"));
            reservation.setName(res.getString("name"));
            reservation.setPhone(res.getInt("phone"));
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservation;
    }
    
}
