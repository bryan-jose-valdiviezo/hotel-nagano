package com.hnagano.services;

import com.hnagano.daos.UserDAO;
import com.hnagano.models.User;

public class UserServices {

    private UserDAO dao;

    public UserServices(){

    }

    public void setDao(UserDAO dao) { this.dao = dao; }

    public UserDAO getDao() { return dao; }


    public User findUser(String username, String password) {

        return dao.findUser(username, password);

    }

    public void createUser(String name, String email, int phone, String address, String username, String password){
        dao.createUser(name, email, phone, address, username, password);
    }

}
