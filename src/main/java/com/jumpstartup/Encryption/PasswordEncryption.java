package com.jumpstartup.Encryption;

import com.jumpstartup.Connection.DatabaseConnector;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordEncryption {

   static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public static String encryptPassword(String password) {
        return encoder.encode(password);
    }

    public static boolean decryptPassword(String username, String password){
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "select * from MYUSER where USERNAME = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println(username);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                String encodePassword = result.getString("HASHPASS");
                System.out.println(encodePassword);
                return encoder.matches(password, encodePassword);
            }
            else{
                // not found in db condition
                return false;
            }
        }
        catch(SQLException e){
            System.out.println("Error while login: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }
}