package com.jumpstartup.jumpstartupjava;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import org.junit.jupiter.api.*;

import com.jumpstartup.Database.LoginDatabase;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.StreamUtils;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginDBTest {

    private LoginDatabase loginDatabase;
    private String testUsername = "testuser";
    private String testPassword = "testpass";
    private String testUUID = "1234-5678-9012-3456";
    private String testEmail = "testuser@example.com";
    private String testType = "regular";

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        // Load H2 schema
        Resource resource = new ClassPathResource("schema.sql");
        String sqlScript = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        System.out.println(sqlScript);
        // Set up H2 database
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:jsudb");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute(sqlScript);

    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        // Clean up any resources needed after all test methods
    }

    @BeforeEach
    void setUp() throws Exception {
        loginDatabase = new LoginDatabase();
    }

    @AfterEach
    void tearDown() throws Exception {
        // Clean up any resources needed after each test method
    }

    @Test
    void testAuthenticate() throws SQLException {
        // Test authentication with correct credentials
        assertTrue(loginDatabase.authenticate(testUsername, testPassword));
        // Test authentication with incorrect credentials
        assertFalse(loginDatabase.authenticate(testUsername, "wrongpass"));
        assertFalse(loginDatabase.authenticate("nonexistentuser", testPassword));
    }

    @Test
    void testNewUser() throws SQLException {
        // Test adding a new user
        assertTrue(loginDatabase.newUser(testUUID, testUsername, testEmail, testPassword, testType));
        // Test adding a duplicate user
        assertFalse(loginDatabase.newUser(testUUID, testUsername, testEmail, testPassword, testType));
    }
}
