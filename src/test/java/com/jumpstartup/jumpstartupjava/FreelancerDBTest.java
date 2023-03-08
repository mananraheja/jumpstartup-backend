package com.jumpstartup.jumpstartupjava;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.jumpstartup.Database.FreeLancerDatabase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jumpstartup.Freelancer.FreelancerBean;
import org.springframework.test.context.jdbc.Sql;

@Sql({"/schema.sql"})
public class FreelancerDBTest {
    private static FreeLancerDatabase freelancerDB;

    @BeforeClass
    public static void setup() {
        freelancerDB = new FreeLancerDatabase();
    }

    @Test
    public void testAddFreelancer() {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid(UUID.randomUUID().toString());
        freelancer.setPhone_number("5551234567");
        freelancer.setSkills("Java, Python");
        freelancer.setLinkedin_link("https://www.linkedin.com/in/johndoe");

        assertTrue(freelancerDB.addFreelancer(freelancer));
    }

    @Test
    public void testAddEducation() {
        assertTrue(freelancerDB.addEducation("123456", "University of California, Berkeley", "Bachelor of Science", "Computer Science", 2023));
    }

    @Test
    public void testAddWorkExperience() {
        assertTrue(freelancerDB.addWorkExperience("123456", "Software Engineer at Google"));
    }

    @Test
    public void testUpdateFreelancer() {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setPhone_number("5554321");
        freelancer.setSkills("Java, Python, C++");
        freelancer.setLinkedin_link("https://www.linkedin.com/in/janedoe");
        freelancer.setInstitution("University of California, Los Angeles");
        freelancer.setDegree("Master of Science");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion(2022);
        freelancer.setWork_experience("Software Engineer at Facebook");

        assertTrue(freelancerDB.updateFreelancer("123456", freelancer));
    }

    @Test
    public void testDeleteFreelancer() {
        assertTrue(freelancerDB.deleteFreelancer("123456"));
    }

    @Test
    public void testAddFreelancerWithInvalidUuid() {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("123"); // Invalid UUID, should be 36 characters long
        freelancer.setPhone_number("5551234567");
        freelancer.setSkills("Java, Python");
        freelancer.setLinkedin_link("https://www.linkedin.com/in/johndoe");

        assertFalse(freelancerDB.addFreelancer(freelancer));
    }

    @Test
    public void testAddEducationWithInvalidUuid() {
        assertFalse(freelancerDB.addEducation("123", "University of California, Berkeley", "Bachelor of Science", "Computer Science", 2023));
    }

    @Test
    public void testAddEducationWithInvalidYearOfCompletion() {
        assertFalse(freelancerDB.addEducation("123456", "University of California, Berkeley", "Bachelor of Science", "Computer Science", 2050));
    }

    @Test
    public void testAddWorkExperienceWithInvalidUuid() {
        assertFalse(freelancerDB.addWorkExperience("123", "Software Engineer at Google"));
    }

    @AfterClass
    public static void cleanup() {
        FreeLancerDatabase.deleteFreelancer("123456");
    }
}
