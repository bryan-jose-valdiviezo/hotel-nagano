/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.services;

import com.hnagano.daos.ViewDAO;
import com.hnagano.models.View;
import com.hnagano.models.ViewPrice;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Bryan
 */
public class ViewServices {
    public ViewDAO dao;
    
    public void setDao(ViewDAO dao) {
        this.dao = dao;
    }
    
    public List<View> getAllViews() {
        return dao.findAll();
    }
    
    public List<ViewPrice> findAllCurrentViewsAndPrices() {
        List<View> views = getAllViews();
        List<ViewPrice> viewPrices = new LinkedList<ViewPrice>();
        
        for (View view : views) {
            viewPrices.add(dao.findLatestPriceByDate(LocalDate.now(), view.getId()));
        }
        
        return viewPrices;
    }
    
    public List<ViewPrice> findUpcomingViewPrices() {
        return dao.findAllUpcomingViewAndPrices();
    }
    
    public boolean createViewPrice(ViewPrice viewPrice) {
        return dao.createViewPrice(viewPrice);
    }
}
