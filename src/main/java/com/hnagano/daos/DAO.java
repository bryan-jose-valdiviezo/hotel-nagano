/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.daos;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Bryan
 */
public interface DAO<T> {
    public List<T> findAll();
    public T find(int id);
    public boolean create(T t);
    public boolean delete(T t);
    public boolean update(T t);
    public T objectBuilder(ResultSet res);
}
