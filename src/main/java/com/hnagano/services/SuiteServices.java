/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.services;

import com.hnagano.daos.SuiteDAO;
import com.hnagano.models.Suite;
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
    
    public List<Suite> getAllSuites(){
        return dao.findAll();
    }
}
