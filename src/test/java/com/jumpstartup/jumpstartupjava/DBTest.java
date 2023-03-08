package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Connection.DatabaseConnector;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;

class DBTest {

    @Test
    void testGetConnection() throws SQLException {
        Connection conn = DatabaseConnector.getConnection();
        assertNotNull(conn);
        assertTrue(conn.isValid(10));
        DatabaseConnector.closeConnection(conn);
    }

    @Test
    void testCloseConnection() throws SQLException {
        Connection conn = DatabaseConnector.getConnection();
        assertNotNull(conn);
        DatabaseConnector.closeConnection(conn);
        assertTrue(conn.isClosed());
    }

    @Test
    void testInvalidCredentials() {
        assertThrows(SQLException.class, () -> {
            Connection conn = DriverManager.getConnection(DatabaseConnector.getDbUrl(), "invalid_user", "invalid_password");
        });
    }
}
