package com.jumpstartup.jumpstartupjava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumpstartup.Database.FreeLancerDatabase;
import com.jumpstartup.Freelancer.FreelancerBean;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FCTest {

    private MockMvc mockMvc;

    @InjectMocks
    private FreeLancerController freeLancerController;

    @Mock
    private FreeLancerDatabase freeLancerDatabase;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(freeLancerController).build();
    }

    @Test
    @Order(1)
    public void testAddFreelancerDataSuccess() throws Exception {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("1234");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setPhone_number("1234567890");
        freelancer.setInstitution("University of Waterloo");
        freelancer.setDegree("Bachelor of Science");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("5 years");

        when(freeLancerDatabase.addFreelancer(any(FreelancerBean.class))).thenReturn(true);
        when(freeLancerDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        when(freeLancerDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/freelancer/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(freelancer)))
                .andExpect(status().isOk())
                .andReturn();

        verify(freeLancerDatabase).addFreelancer(freelancer);
        verify(freeLancerDatabase).addEducation(freelancer.getUuid(), freelancer.getInstitution(),
                freelancer.getDegree(), freelancer.getMajor(), freelancer.getYear_of_completion());
        verify(freeLancerDatabase).addWorkExperience(freelancer.getUuid(), freelancer.getWork_experience());



        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(2)
    public void testAddFreelancerDataEducationFailure() throws Exception {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("1234");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setPhone_number("1234567890");
        freelancer.setInstitution("University of Waterloo");
        freelancer.setDegree("Bachelor of Science");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("5 years");

        when(freeLancerDatabase.addFreelancer(any(FreelancerBean.class))).thenReturn(true);
        when(freeLancerDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(false);
        when(freeLancerDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/freelancer/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(freelancer)))
                .andExpect(status().isOk())
                .andReturn();

        verify(freeLancerDatabase).addFreelancer(freelancer);
        verify(freeLancerDatabase).addEducation(freelancer.getUuid(), freelancer.getInstitution(),
                freelancer.getDegree(), freelancer.getMajor(), freelancer.getYear_of_completion());
        verify(freeLancerDatabase, never()).addWorkExperience(any(String.class), any(String.class));

        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(3)
    public void testAddFreelancerDataWorkExperienceFailure() throws Exception {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("1234");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setPhone_number("1234567890");
        freelancer.setInstitution("University of Waterloo");
        freelancer.setDegree("Bachelor of Science");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("5 years");
        when(freeLancerDatabase.addFreelancer(any(FreelancerBean.class))).thenReturn(true);
        when(freeLancerDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        when(freeLancerDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(false);

        MvcResult result = mockMvc.perform(post("/freelancer/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(freelancer)))
                .andExpect(status().isOk())
                .andReturn();

        verify(freeLancerDatabase).addFreelancer(freelancer);
        verify(freeLancerDatabase).addEducation(freelancer.getUuid(), freelancer.getInstitution(),
                freelancer.getDegree(), freelancer.getMajor(), freelancer.getYear_of_completion());
        verify(freeLancerDatabase).addWorkExperience(freelancer.getUuid(), freelancer.getWork_experience());

        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(4)
    public void testAddFreelancerDataDuplicate() throws Exception {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("1234");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setPhone_number("1234567890");
        freelancer.setInstitution("University of Waterloo");
        freelancer.setDegree("Bachelor of Science");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("5 years");
        when(freeLancerDatabase.addFreelancer(any(FreelancerBean.class))).thenReturn(false);

        MvcResult result = mockMvc.perform(post("/freelancer/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(freelancer)))
                .andExpect(status().isOk())
                .andReturn();

        verify(freeLancerDatabase).addFreelancer(freelancer);
        verify(freeLancerDatabase, never()).addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class));
        verify(freeLancerDatabase, never()).addWorkExperience(any(String.class), any(String.class));

        assertEquals(500, result.getResponse().getStatus());
    }

        @Test
    @Order(5)
    public void testUpdateFreelancerData() throws Exception {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("1234");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setPhone_number("1234567890");
        freelancer.setInstitution("ABC University");
        freelancer.setDegree("Bachelor of Science");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("5 years");

        when(freeLancerDatabase.updateFreelancer(any(String.class), any(FreelancerBean.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(put("/freelancer/update/" + freelancer.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(freelancer)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(freeLancerDatabase).updateFreelancer(freelancer.getUuid(), freelancer);

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(6)
    public void testDeleteEntrepreneurData() throws Exception {
        String uuid = "1234";

        when(freeLancerDatabase.deleteFreelancer(any(String.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(delete("/freelancer/delete/{uuid}", uuid))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(freeLancerDatabase).deleteFreelancer(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(7)
    public void testGetFreelancerData() throws Exception {
        String uuid = "1234";
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid(uuid);
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setPhone_number("1234567890");
        freelancer.setInstitution("ABC University");
        freelancer.setDegree("Bachelor of Science");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("5 years");

        when(freeLancerDatabase.getFreelancer(any(String.class))).thenReturn(freelancer);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/freelancer/{uuid}", uuid))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(freeLancerDatabase).getFreelancer(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

}