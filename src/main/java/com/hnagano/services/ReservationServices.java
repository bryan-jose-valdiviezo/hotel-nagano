/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.services;

import com.hnagano.daos.ReservationDAO;
import com.hnagano.dtos.ReservationSearchDTO;
import com.hnagano.models.Reservation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan
 */
public class ReservationServices {
    ReservationDAO dao;

    public void setDao(ReservationDAO dao) {
        this.dao = dao;
    }
    
    public List<Reservation> findAll() {
        return dao.findAll();
    }
    
    public ArrayList<Reservation> findAllByFilter(ReservationSearchDTO form) {
        return dao.findAllByFilter(form);
    }
    
    public Reservation findReservation(int id) {
        return dao.findWithRooms(id, true);
    }
    
    public int createReservation(Reservation reservation) {
        return dao.createReservationWithReturn(reservation);
    }
    
    public boolean updateReservation(Reservation reservation) {
        return dao.update(reservation);
    }
    
    public double findTodayIncome() {
        double income = 0.0;
        ArrayList<Reservation> reservations = dao.findAllMadeToday();
        
        for (Reservation reservation : reservations) {
            income += reservation.totalPrice();
        }
        
        return income;
    }
    
    public boolean deleteReservation(Reservation reservation) {
        return dao.delete(reservation);
    }
}
