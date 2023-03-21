package com.jumpstartup.Encryption;

import com.jumpstartup.Connection.DatabaseConnector;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordEncryption {

    private static final Logger logger = LoggerFactory.getLogger(PasswordEncryption.class);

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
            if (result.next()) {
                String encodePassword = result.getString("HASHPASS");
                logger.info("encoded hashpass: {}", encodePassword);
                return encoder.matches(password, encodePassword);
            }
            else {
                logger.warn("Encoded hashpass not found in DB");
                return false;
            }
        }
        catch (SQLException e) {
            logger.error("Error while login: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }
}