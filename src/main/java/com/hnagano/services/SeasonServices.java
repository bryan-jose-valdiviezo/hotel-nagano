/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.services;

import com.hnagano.daos.SeasonDAO;
import com.hnagano.models.Season;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Bryan
 */
public class SeasonServices {
    private SeasonDAO dao;

    public void setDao(SeasonDAO dao) {
        this.dao = dao;
    }
    
    public Season findCurrentSeasonAndPrice() {
        return dao.findClosestDate(LocalDate.now());
    }
    
    public List<Season> findUpcomingSeasonAndPrices() {
        return dao.findAll();
    }
    
    public boolean createSeasonPrice(Season season) {
        return dao.create(season);
    }
}
