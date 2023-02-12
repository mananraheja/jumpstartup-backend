package com.jumpstartup.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDatabase {
    public boolean authenticate(String username, String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
            String sql = "SELECT * FROM myUser WHERE username = ? AND hashpass = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to authenticate user: " + e.getMessage());
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error while trying to close database connection: " + e.getMessage());
                }
            }
        }
    }

    public boolean newUser(String username, String email, String hashpass, String type) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
            String sql = "INSERT INTO myUser(username, email, hashpass, type) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, hashpass);
            statement.setString(4, type);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to add new user: " + e.getMessage());
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error while trying to close database connection: " + e.getMessage());
                }
            }
        }
    }



}
