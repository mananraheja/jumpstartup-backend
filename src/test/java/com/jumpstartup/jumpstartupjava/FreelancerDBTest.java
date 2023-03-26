package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Connection.DatabaseConnector;
import com.jumpstartup.Database.FreeLancerDatabase;
import com.jumpstartup.Freelancer.FreelancerBean;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FreelancerDBTest {

    FreeLancerDatabase freelancerDB = new FreeLancerDatabase();

    @Test
    public void testAddFreelancer() {
        DatabaseConnector databaseConnector = new DatabaseConnector();

        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid(UUID.randomUUID().toString());
        freelancer.setPhone_number("5551234567");
        freelancer.setSkills("Java, Python");
        freelancer.setLinkedin_link("https://www.linkedin.com/in/johndoe");
        assertTrue(freelancerDB.addFreelancer(freelancer));
    }

}
