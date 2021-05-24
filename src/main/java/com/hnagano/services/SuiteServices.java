/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.services;

import com.hnagano.daos.SuiteDAO;
import com.hnagano.models.Suite;
import com.hnagano.models.SuitePrice;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Bryan
 */
public class SuiteServices {
    private SuiteDAO dao;
    
    public void setDao(SuiteDAO dao) {
        this.dao = dao;
    }
    
    public Suite findSuite(int id) {
        return dao.find(id);
    }
    
    public List<Suite> getAllSuites(){
        return dao.findAll();
    }
    
    public List<SuitePrice> findAllCurrentSuitesAndPrices() {
        List<Suite> suites = getAllSuites();
        List<SuitePrice> suitePrices = new LinkedList<SuitePrice>();
        
        for (Suite suite : suites) {
            suitePrices.add(dao.findLatestPriceByDate(LocalDate.now(), suite.getId()));
        }
        
        return suitePrices;
    }
    
    public List<SuitePrice> findAllUpcomingSuitesAndPrices() {
        return dao.findAllUpcomingSuiteAndPrices();
    }
    
    public boolean createSuitePrice(SuitePrice suitePrice) {
        return dao.createSuitePrice(suitePrice);
    }
}
