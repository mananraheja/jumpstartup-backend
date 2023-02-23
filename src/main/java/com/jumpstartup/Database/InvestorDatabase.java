package com.jumpstartup.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jumpstartup.Investor.InvestorBean;


public class InvestorDatabase {
    
    public boolean addInvestor(InvestorBean investor) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:file:./jsudb.h2.db", "sa", "");
            String sql = "INSERT INTO Investor (uuid,phone_number, funding_available, brands_built) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, investor.getUuid());
            statement.setString(2, investor.getPhone_number());
            statement.setFloat(3, investor.getFunding_available());
            statement.setString(4, investor.getBrands_built());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to add investor: " + e.getMessage());
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error while trying to close investor database connection: " + e.getMessage());
                }
            }
        }
    }
    
    public boolean updateInvestor(String uuid, InvestorBean investor) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:file:./jsudb.h2.db", "sa", "");

            // update investor information
            String sql = "UPDATE Investor SET phone_number = ?, funding_available = ?, brands_built = ? WHERE uuid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, investor.getPhone_number());
            statement.setFloat(2, investor.getFunding_available());
            statement.setString(3, investor.getBrands_built());
            statement.setString(4, uuid);

            int rowsAffected = statement.executeUpdate();

            // check if updates were successful
            if (rowsAffected > 0 ) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to update investor: " + e.getMessage());
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error while trying to close investor database connection: " + e.getMessage());
                }
            }
        }
    }

    public boolean deleteInvestor(String UUID) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:file:./jsudb.h2.db", "sa", "");

            // Delete investor
            String sql = "DELETE FROM Investor WHERE uuid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, UUID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0 ) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to delete investor: " + e.getMessage());
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error while trying to close investor database connection: " + e.getMessage());
                }
            }
        }
    }

    public InvestorBean getInvestor(String UUID) {
        InvestorBean investor = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:h2:file:./jsudb.h2.db", "sa", "");
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Investor WHERE uuid = ?");
            statement.setString(1, UUID);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                investor = new InvestorBean();
                investor.setUuid(result.getString("uuid"));
                investor.setPhone_number(result.getString("phone_number"));
                investor.setFunding_available(result.getFloat("funding_available"));
                investor.setBrands_built(result.getString("brands_built"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error while trying to close database connection: " + e.getMessage());
                }
            }
        }
        System.out.println("FETCHED INVESTOR SUCCESSFULLY !!");
        return investor;
    }

}
