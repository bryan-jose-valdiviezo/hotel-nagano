/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.daos;

import com.hnagano.databases.Database;
import com.hnagano.dtos.RoomSearchDTO;
import com.hnagano.models.DatePrice;
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
public class RoomDAO implements DAO<Room>{
    private Database database;
    private ViewDAO viewDAO;
    private SuiteDAO suiteDAO;
    private DatePriceDAO datePriceDAO;

    public DatePriceDAO getDatePriceDAO() {
        return datePriceDAO;
    }

    public void setDatePriceDAO(DatePriceDAO datePriceDAO) {
        this.datePriceDAO = datePriceDAO;
    }
    
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

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new LinkedList<Room>();
        Room room;
        
        try {
            Statement stm = database.getInstance().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM rooms");
            
            while (res.next()) {
                room = objectBuilder(res);
                rooms.add(room);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rooms;
    }
    
    public int findNumberAvailableRoomToday() {
        int availableRoomsCount = 0;
        
        try {
            String query = "SELECT rooms.* FROM rooms "
                    + "INNER JOIN reservation_rooms ON rooms.id = reservation_rooms.room_id "
                    + "INNER JOIN reservations ON reservations.id = reservation_rooms.reservation_id "
                    + "WHERE rooms.id NOT IN ("
                    + "SELECT rooms.id FROM rooms "
                    + "INNER JOIN reservation_rooms ON rooms.id = reservation_rooms.room_id "
                    + "INNER JOIN reservations ON reservations.id = reservation_rooms.reservation_id "
                    + "WHERE (CURRENT_DATE <= date_end AND CURRENT_DATE >= date_start)"
                    + ") GROUP BY rooms.id";
            PreparedStatement stm = database.getInstance().prepareStatement(query);
            
            ResultSet res = stm.executeQuery();
            
            while (res.next())
                availableRoomsCount++;
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return availableRoomsCount;
    }
    
    public ArrayList<Room> findAllAvailableRoomsByFilter(RoomSearchDTO filter) {
        ArrayList<Room> allRooms = new ArrayList<Room>();
        Room room;
        
        LocalDate dateStart = LocalDate.parse(filter.getDateStart());
        LocalDate dateEnd = LocalDate.parse(filter.getDateEnd());
        
        try {
            
            
            String query = "SELECT rooms.*, date_start, date_end FROM rooms "
                    + "INNER JOIN reservation_rooms ON rooms.id = reservation_rooms.room_id "
                    + "INNER JOIN reservations ON reservations.id = reservation_rooms.reservation_id "
                    + "WHERE rooms.id NOT IN ("
                    + "SELECT rooms.id FROM rooms "
                    + "INNER JOIN reservation_rooms ON rooms.id = reservation_rooms.room_id "
                    + "INNER JOIN reservations ON reservations.id = reservation_rooms.reservation_id "
                    + "WHERE (? <= date_end AND ? >= date_start)) ";
            
            if (filter.getFloor() > 0)
                query += "AND rooms.floor = "+ filter.getFloor() +" ";
            
            if (filter.getViewID() > 0)
                query += "AND rooms.view_id = "+ filter.getViewID() +" ";
            
            if (filter.getSuiteID() > 0)
                query += "AND rooms.suite_id = "+ filter.getSuiteID() +" ";
            
            query += " GROUP BY rooms.id";
            
            
            
            PreparedStatement stm = database.getInstance().prepareStatement(query);
            
            stm.setDate(1, Date.valueOf(dateStart));
            stm.setDate(2, Date.valueOf(dateEnd));
            
            
            ResultSet res = stm.executeQuery();
            
            while (res.next()) {
                room = objectBuilder(res);
                allRooms.add(room);
            }
            
                  
            for (int i = 0; i < allRooms.size() ; i++) {
                
                allRooms.get(i).setDatePrices(datePriceDAO.getTotalRoomPrice(allRooms.get(i), dateStart, dateEnd));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return allRooms;
    }
    
    public ArrayList<Room> findAllForReservation(Reservation reservation, boolean withPrices) {
        ArrayList<Room> rooms = new ArrayList<Room>();
        Room room;
        
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("SELECT rooms.* FROM `reservations` "
                    + "INNER JOIN reservation_rooms ON reservations.id = reservation_rooms.reservation_id "
                    + "INNER JOIN rooms ON reservation_rooms.room_id = rooms.id "
                    + "WHERE reservations.id = ?");
            
            stm.setInt(1, reservation.getId());
            
            ResultSet res = stm.executeQuery();
            
            while (res.next()) {
                room = objectBuilder(res);

                if (withPrices)
                    room.setDatePrices(datePriceDAO.getTotalRoomPrice(room, reservation.getDateStart(), reservation.getDateEnd()));
                
                rooms.add(room);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rooms;
    }
    
    public Room findRoomWithTotalPrices(int id, LocalDate dateStart, LocalDate dateEnd) {
        Room room = find(id);
        
        room.setDatePrices(datePriceDAO.getTotalRoomPrice(room, dateStart, dateEnd));
        
        return room;
    }

    @Override
    public Room find(int id) {
        Room room = null;
        
        try {
            PreparedStatement stm = database.getInstance().prepareCall("SELECT * FROM rooms WHERE id = ?");
            
            stm.setInt(1, id);
            
            ResultSet res = stm.executeQuery();
            
            if (res.next())
                room = objectBuilder(res);
            
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return room;
    }

    @Override
    public boolean create(Room room) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("INSERT INTO rooms (view_id, suite_id, floor) VALUES (?,?,?)");
            stm.setInt(1, room.getView().getId());
            stm.setInt(2, room.getSuite().getId());
            stm.setInt(3, room.getFloor());
            
            int n = stm.executeUpdate();
            
            return n>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(Room t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Room room) {
        try {
            PreparedStatement stm = database.getInstance().prepareStatement("UPDATE rooms SET view_id = ?, suite_id = ?, floor = ? WHERE id = ?");
            stm.setInt(1, room.getView().getId());
            stm.setInt(2, room.getSuite().getId());
            stm.setInt(3, room.getFloor());
            stm.setInt(4, room.getId());
            
            int n = stm.executeUpdate();
            
            return n>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public Room objectBuilder(ResultSet res) {
        Room room = new Room();
        
        try {
            room.setId(res.getInt("rooms.id"));
            room.setView(viewDAO.find(res.getInt("rooms.view_id")));
            room.setSuite(suiteDAO.find(res.getInt("rooms.suite_id")));
            room.setFloor(res.getInt("rooms.floor"));
            
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return room;
    }
    
}
