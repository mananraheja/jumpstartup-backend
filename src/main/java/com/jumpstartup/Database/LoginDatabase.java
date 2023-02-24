package com.jumpstartup.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jumpstartup.Connection.DatabaseConnector;


public class LoginDatabase {
    public boolean authenticate(String username, String password) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
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
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean newUser(String UUID,String username, String email, String hashpass, String type) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "INSERT INTO myUser(UUID, username, email, hashpass, type) VALUES(?,?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, UUID);
            statement.setString(2, username);
            statement.setString(3, email);
            statement.setString(4, hashpass);
            statement.setString(5, type);

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
            DatabaseConnector.closeConnection(connection);
        }
    }
}
