package com.jumpstartup.jumpstartupjava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumpstartup.Database.InvestorDatabase;
import com.jumpstartup.Investor.InvestorBean;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ICTest {

    private MockMvc mockMvc;

    @InjectMocks
    private InvestorController investorController;

    @Mock
    private InvestorDatabase investorDatabase;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(investorController).build();
    }

    @Test
    @Order(1)
    public void testAddInvestorData() throws Exception {
        InvestorBean investor = new InvestorBean();
        investor.setUuid("1234");
        investor.setFirstName("John");
        investor.setLastName("Doe");
        investor.setPhone_number("1234567890");
        investor.setDomain("Blockchain");
        investor.setBrands_built("FTX");
        investor.setFunding_available("10000000");
        investor.setInstitution("University of Waterloo");
        investor.setDegree("Bachelor of Science");
        investor.setMajor("Computer Science");
        investor.setYear_of_completion("2020");
        investor.setWork_experience("5 years");

        when(investorDatabase.addInvestor(any(InvestorBean.class))).thenReturn(true);
        when(investorDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        when(investorDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/investor/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(investor)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(investorDatabase).addInvestor(investor);
        verify(investorDatabase).addEducation(investor.getUuid(), investor.getInstitution(),
                investor.getDegree(), investor.getMajor(), investor.getYear_of_completion());
        verify(investorDatabase).addWorkExperience(investor.getUuid(), investor.getWork_experience());

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(2)
    public void testUpdateInvestorData() throws Exception {
        InvestorBean investor = new InvestorBean();
        investor.setUuid("1234");
        investor.setFirstName("John");
        investor.setLastName("Doe");
        investor.setPhone_number("1234567890");
        investor.setDomain("Blockchain");
        investor.setBrands_built("FTX");
        investor.setFunding_available("10000000");
        investor.setInstitution("ABC University");
        investor.setDegree("Bachelor of Science");
        investor.setMajor("Computer Science");
        investor.setYear_of_completion("2020");
        investor.setWork_experience("5 years");

        when(investorDatabase.updateInvestor(any(String.class), any(InvestorBean.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(put("/investor/update/" + investor.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(investor)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(investorDatabase).updateInvestor(investor.getUuid(), investor);

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(4)
    public void testDeleteInvestorData() throws Exception {
        String uuid = "1234";

        when(investorDatabase.deleteInvestor(any(String.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(delete("/investor/delete/{uuid}", uuid))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(investorDatabase).deleteInvestor(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(3)
    public void testGetInvestorData() throws Exception {
        String uuid = "1234";
        InvestorBean investor = new InvestorBean();
        investor.setUuid(uuid);
        investor.setFirstName("John");
        investor.setLastName("Doe");
        investor.setDomain("Blockchain");
        investor.setBrands_built("FTX");
        investor.setFunding_available("10000000");
        investor.setPhone_number("1234567890");
        investor.setInstitution("ABC University");
        investor.setDegree("Bachelor of Science");
        investor.setMajor("Computer Science");
        investor.setYear_of_completion("2020");
        investor.setWork_experience("5 years");

        when(investorDatabase.getInvestor(any(String.class))).thenReturn(investor);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/investor/{uuid}", uuid))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(investorDatabase).getInvestor(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }
}