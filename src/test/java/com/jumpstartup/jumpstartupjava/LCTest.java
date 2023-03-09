package com.jumpstartup.jumpstartupjava;

import static org.junit.jupiter.api.Assertions.*;

import com.jumpstartup.Database.LoginDatabase;
import com.jumpstartup.Encryption.PasswordEncryption;
import com.jumpstartup.LoginBody.LoginRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class LCTest {

    private LoginController loginController;

    @Mock
    private LoginDatabase loginDatabase;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        // Load schema.sql
        Resource resource = new ClassPathResource("schema.sql");
        String sqlScript = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        // Set up H2 database
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:jsudb");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute(sqlScript);

    }
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        loginController = new LoginController();
    }

    @Test
    public void testLogin() {
        ResponseEntity<Object> response = loginController.login();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testLoginSubmitAuthenticated() {
        LoginRequest loginRequest = new LoginRequest("username","email@gmail.com","password","freelacner");
        PasswordEncryption encryption = new PasswordEncryption();
        String encryptedPassword = encryption.encryptPassword(loginRequest.getHashpass());
        when(loginDatabase.authenticate(loginRequest.getUsername(), encryptedPassword)).thenReturn(true);
        ResponseEntity<String> response = loginController.loginSubmit(loginRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("AUTHORIZED", response.getBody());
    }

    @Test
    public void testLoginSubmitNotAuthenticated() {
        LoginRequest loginRequest = new LoginRequest("username","email@gmail.com","password","freelacner");
        PasswordEncryption encryption = new PasswordEncryption();
        String encryptedPassword = encryption.encryptPassword(loginRequest.getHashpass());
        when(loginDatabase.authenticate(loginRequest.getUsername(), encryptedPassword)).thenReturn(false);
        ResponseEntity<String> response = loginController.loginSubmit(loginRequest);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("NOT AUTHORIZED", response.getBody());
    }

    @Test
    public void testSignupSubmitSuccess() {
        LoginRequest loginRequest = new LoginRequest("username","email@gmail.com","password","freelacner");
        loginRequest.setUuid(UUID.randomUUID());
        PasswordEncryption encryption = new PasswordEncryption();
        String encryptedPassword = encryption.encryptPassword(loginRequest.getHashpass());
        when(loginDatabase.newUser(loginRequest.getUuid(), loginRequest.getUsername(), loginRequest.getEmail(), encryptedPassword, loginRequest.getType())).thenReturn(true);
        ResponseEntity<String> response = loginController.signupSubmit(loginRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SIGNUP SUCCESSFUL", response.getBody());
    }

    @Test
    public void testSignupSubmitFailure() {
        LoginRequest loginRequest = new LoginRequest("username","email@gmail.com","password","freelacner");
        loginRequest.setUuid(UUID.randomUUID());
        PasswordEncryption encryption = new PasswordEncryption();
        String encryptedPassword = encryption.encryptPassword(loginRequest.getHashpass());
        when(loginDatabase.newUser(loginRequest.getUuid(), loginRequest.getUsername(), loginRequest.getEmail(), encryptedPassword, loginRequest.getType())).thenReturn(false);
        ResponseEntity<String> response = loginController.signupSubmit(loginRequest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("SIGNUP FAILED", response.getBody());
    }
}
