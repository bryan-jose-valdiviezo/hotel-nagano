/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.springframework.web.context.ServletContextAware;

/**
 *
 * @author Bryan
 */
public class Database implements ServletContextAware{
    private static Connection connection;
    private ServletContext context;

    @Override
    public void setServletContext(ServletContext sc) {
        this.context = sc;
    }
    
    public Connection getInstance(){
        try {
            if (connection == null || connection.isClosed()) {
                String pilote = context.getInitParameter("pilote");
                String url = context.getInitParameter("bdUrl");
                String user = context.getInitParameter("user");
                String password = context.getInitParameter("password");
                
                try {
                    Class.forName(pilote);
                    connection = DriverManager.getConnection(url, user, password);
                    if (connection == null)
                        System.out.println("Connection is null");
                    else
                        System.out.println("Connection is set");
                    
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
            
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return connection;
    }
    
}
