package com.hnagano.daos;

import com.hnagano.databases.Database;
import com.hnagano.models.User;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryan
 */
public class UserDAO {

    private Database database;

    public void setDatabase(Database database) {

        this.database = database;

    }

    public Database getDatabase(){

        return database;

    }

    public User findUser(String username, String password) {

        User user = null;

        try {
            if (username.contains("@")) {

                PreparedStatement stm = database.getInstance().prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");

                stm.setString(1, username);
                stm.setString(2, password);

                ResultSet res = stm.executeQuery();

                if (res.next()) {
                    user = new User();
                    user.setName(res.getString("name"));
                    user.setPhone(res.getInt("phone"));
                    user.setEmail(res.getString("email"));
                    user.setAddress(res.getString("address"));
                    user.setRole(res.getString("role_id"));
                    user.setUsername(res.getString("username"));
                    user.setPassword(res.getString("password"));
                }

            } else {

                
                PreparedStatement stm = database.getInstance().prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");

                stm.setString(1, username);
                stm.setString(2, password);

                System.out.println(username);
                System.out.println(password);

                ResultSet res = stm.executeQuery();

                System.out.print(res);

                if (res.next()) {
                    user = new User();
                    user.setName(res.getString("name"));
                    user.setPhone(res.getInt("phone"));
                    user.setEmail(res.getString("email"));
                    user.setAddress(res.getString("address"));
                    user.setRole(res.getString("role"));
                    user.setUsername(res.getString("username"));
                    user.setPassword(res.getString("password"));
                }
            }

        } catch (SQLException e) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);

        } catch (Exception e) {
        	System.out.print("failed driver");
        }

        return user;
    }

    public Boolean createUser(String name, String email, int phone, String address, String role,String username, String password ) {

        try {
            PreparedStatement stm = database.getInstance().prepareStatement("INSERT INTO user SET name = ?, email = ?, phone = ?," +
                    " address = ?, role_id = ?, username = ?, password = ?");

            stm.setString(1, name);
            stm.setString(2, email);
            stm.setInt(3, phone);
            stm.setString(4, address);
            stm.setString(5, role);
            stm.setString(6, username);
            stm.setString(7, password);

            stm.execute();

        } catch (SQLException e) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);

        }

        return null;
    }

    public Boolean updateUser(String name, String email, int phone, String address, String role, String username, String password ) {

        try {

            PreparedStatement stm = database.getInstance().prepareStatement("UPDATE user SET name = ?, phone = ?," +
                    " address = ?, role = ?, username = ?, password = ? WHERE email = ?");

            stm.setString(1, name);
            stm.setInt(2, phone);
            stm.setString(3, address);
            stm.setString(4, role);
            stm.setString(5, username);
            stm.setString(6, password);
            stm.setString(7, email);

            stm.execute();

        } catch (SQLException e) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);

        }

        return null;

    }

    public Boolean deleteUser( String username, String password ) {

        try {

            PreparedStatement stm = database.getInstance().prepareStatement("DELETE FROM user WHERE username = ?, password = ?");

            stm.setString(1, username);
            stm.setString(2, password);

            stm.execute();

        } catch (SQLException e){

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);

        }

        return null;
    }

}

