package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Connection.DatabaseConnector;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DBTest {

    private Connection conn;

    @BeforeEach
    void setUp() throws SQLException {
        conn = DatabaseConnector.getConnection();
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            DatabaseConnector.closeConnection(conn);
        }
    }

    @Test
    void testGetConnection() {
        assertNotNull(conn);
        assertTimeoutPreemptively(
                java.time.Duration.ofSeconds(10),
                () -> assertTrue(conn.isValid(10))
        );
    }

    @Test
    void testCloseConnection() throws SQLException {
        DatabaseConnector.closeConnection(conn);
        assertTrue(conn.isClosed());
    }

    @Test
    void testCloseNullConnection() throws SQLException {
        Connection nullConnection = null;
        assertDoesNotThrow(() -> DatabaseConnector.closeConnection(nullConnection));
    }

    @Test
    void testGetDbUrl() {
        assertEquals("jdbc:h2:mem:jsudb", DatabaseConnector.getDbUrl());
    }

    @Test
    void testInvalidCredentials() {
        assertThrows(SQLException.class, () -> {
            Connection conn = DriverManager.getConnection(DatabaseConnector.getDbUrl(), "invalid_user", "invalid_password");
        });
    }


    @Test
    void testDoubleCloseConnection() {
        assertDoesNotThrow(() -> {
            Connection conn = DatabaseConnector.getConnection();
            assertNotNull(conn);
            DatabaseConnector.closeConnection(conn);
            assertTrue(conn.isClosed());
            // Attempt to close the connection again, which should throw an SQLException
            DatabaseConnector.closeConnection(conn);
        });
    }

}
