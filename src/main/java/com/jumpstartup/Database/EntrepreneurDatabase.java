package com.jumpstartup.Database;

import com.jumpstartup.Connection.DatabaseConnector;
import com.jumpstartup.Entrepreneur.EntrepreneurBean;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EntrepreneurDatabase {
    
    public boolean addEntrepreneur(EntrepreneurBean entrepreneur) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "INSERT INTO Entrepreneur (uuid,phone_number, domain) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entrepreneur.getUuid());
            statement.setString(2, entrepreneur.getPhone_number());
            statement.setString(3, entrepreneur.getDomain());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            //Logging statement here System.out.println("Error while trying to add entrepreneur: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }
    
    public boolean updateEntrepreneur(String uuid, EntrepreneurBean entrepreneur) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();

            // update entrepreneur information
            String sql = "UPDATE Entrepreneur SET phone_number = ?, domain = ? WHERE uuid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entrepreneur.getPhone_number());
            statement.setString(2, entrepreneur.getDomain());
            statement.setString(5, uuid);

            int rowsAffected = statement.executeUpdate();

            // check if updates were successful
            if (rowsAffected > 0 ) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            //Logging statement here System.out.println("Error while trying to update entrepreneur: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean deleteEntrepreneur(String UUID) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();

            // Delete entrepreneur
            String sql = "DELETE FROM Entrepreneur WHERE uuid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, UUID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0 ) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            //Logging statement here System.out.println("Error while trying to delete entrepreneur: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public EntrepreneurBean getEntrepreneur(String UUID) {
        EntrepreneurBean entrepreneur = null;
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Entrepreneur WHERE uuid = ?");
            statement.setString(1, UUID);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                entrepreneur = new EntrepreneurBean();
                entrepreneur.setUuid(result.getString("uuid"));
                entrepreneur.setPhone_number(result.getString("phone_number"));
                entrepreneur.setDomain(result.getString("domain"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        //Logging statement here System.out.println("FETCHED ENTREPRENEUR SUCCESSFULLY !!");
        return entrepreneur;
    }

}
