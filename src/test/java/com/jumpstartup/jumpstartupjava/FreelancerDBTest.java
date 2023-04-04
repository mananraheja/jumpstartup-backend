package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Connection.DatabaseConnector;
import com.jumpstartup.Database.FreeLancerDatabase;
import com.jumpstartup.Freelancer.FreelancerBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FreelancerDBTest {

    @Mock
    FreeLancerDatabase freelancerDB;

    @Mock
    Connection connection;

    @Mock
    PreparedStatement preparedStatement;

    @Mock
    ResultSet resultSet;

    @InjectMocks
    DatabaseConnector databaseConnector;

    private FreelancerBean freelancer;

    @BeforeEach
    public void setUp() {
        freelancer = new FreelancerBean();
        freelancer.setUuid(UUID.randomUUID().toString());
        freelancer.setPhone_number("5551234567");
        freelancer.setSkills("Java, Python");
        freelancer.setLinkedin_link("https://www.linkedin.com/in/johndoe");
    }

    @Test
    public void testAddFreelancerSuccess() throws SQLException {
        when(databaseConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        assertTrue(freelancerDB.addFreelancer(freelancer));
    }

//    @Test
//    public void testAddFreelancerFailure() throws SQLException {
//        when(databaseConnector.getConnection()).thenReturn(connection);
//        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(0);
//
//        assertFalse(freelancerDB.addFreelancer(freelancer));
//    }
//
//    @Test
//    public void testAddEducationSuccess() throws SQLException {
//        when(databaseConnector.getConnection()).thenReturn(connection);
//        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(1);
//
//        assertTrue(freelancerDB.addEducation(freelancer.getUuid(), "MIT", "B.S.", "Computer Science", "2023"));
//    }
//
//    @Test
//    public void testAddEducationFailure() throws SQLException {
//        when(databaseConnector.getConnection()).thenReturn(connection);
//        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(0);
//
//        assertFalse(freelancerDB.addEducation(freelancer.getUuid(), "MIT", "B.S.", "Computer Science", "2023"));
//    }
//
//    @Test
//    public void testAddWorkExperienceSuccess() throws SQLException {
//        when(databaseConnector.getConnection()).thenReturn(connection);
//        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(1);
//
//        assertTrue(freelancerDB.addWorkExperience(freelancer.getUuid(), "Google, Software Engineer"));
//    }
//
//    @Test
//    public void testAddWorkExperienceFailure() throws SQLException {
//        when(databaseConnector.getConnection()).thenReturn(connection);
//        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(0);
//
//        assertFalse(freelancerDB.addWorkExperience(freelancer.getUuid(), "Google, Software Engineer"));
//    }
//
//    @Test
//    public void testUpdateFreelancerSuccess() throws SQLException {
//        when(databaseConnector.getConnection()).thenReturn(connection);
//        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(1);
//        assertTrue(freelancerDB.updateFreelancer(freelancer.getUuid(),freelancer));
//    }
//
//    @Test
//    public void testUpdateFreelancerFailure() throws SQLException {
//        when(databaseConnector.getConnection()).thenReturn(connection);
//        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(0);
//
//        assertFalse(freelancerDB.updateFreelancer(freelancer.getUuid(),freelancer));
//    }
//
//    @Test
//    public void testDeleteFreelancerSuccess() throws SQLException {
//        when(databaseConnector.getConnection()).thenReturn(connection);
//        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(1);
//
//        assertTrue(freelancerDB.deleteFreelancer(freelancer.getUuid()));
//    }
//
//    @Test
//    public void testDeleteFreelancerFailure() throws SQLException {
//        when(databaseConnector.getConnection()).thenReturn(connection);
//        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(0);
//
//        assertFalse(freelancerDB.deleteFreelancer(freelancer.getUuid()));
//    }
//
//    @Test
//    public void testGetFreelancerSuccess() throws SQLException {
//        when(databaseConnector.getConnection()).thenReturn(connection);
//        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
//        when(preparedStatement.executeQuery()).thenReturn(resultSet);
//        when(resultSet.next()).thenReturn(true);
//        when(resultSet.getString("uuid")).thenReturn(freelancer.getUuid());
//
//        FreelancerBean retrievedFreelancer = freelancerDB.getFreelancer(freelancer.getUuid());
//        assertTrue(retrievedFreelancer.getUuid().equals(freelancer.getUuid()));
//    }
//
//    @Test
//    public void testGetFreelancerFailure() throws SQLException {
//        when(databaseConnector.getConnection()).thenReturn(connection);
//        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
//        when(preparedStatement.executeQuery()).thenReturn(resultSet);
//        when(resultSet.next()).thenReturn(false);
//
//        FreelancerBean retrievedFreelancer = freelancerDB.getFreelancer(freelancer.getUuid());
//        assertTrue(retrievedFreelancer == null);
//    }
}
