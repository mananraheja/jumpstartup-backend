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
import com.fasterxml.jackson.core.type.TypeReference;
import com.jumpstartup.Company.CompanyBean;
import com.jumpstartup.Database.EntrepreneurDatabase;
import com.jumpstartup.Entrepreneur.EntrepreneurBean;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ECTest {

    private MockMvc mockMvc;

    @InjectMocks
    private EntrepreneurController entrepreneurController;

    @Mock
    private EntrepreneurDatabase entrepreneurDatabase;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(entrepreneurController).build();
    }

    @Test
    @Order(1)
    public void testAddEntrepreneurData() throws Exception {
        EntrepreneurBean entrepreneur = new EntrepreneurBean();
        entrepreneur.setUuid("1234");
        entrepreneur.setFirstName("John");
        entrepreneur.setLastName("Doe");
        entrepreneur.setPhone_number("1234567890");
        entrepreneur.setDomain("Blockchain");
        entrepreneur.setCompany_name("Anonymous");
        entrepreneur.setFunding_status("Series A");
        entrepreneur.setEquity_offered("20%");
        entrepreneur.setIs_registered("Yes");
        entrepreneur.setStakeholder("Govt. of Canada");
        entrepreneur.setAssets("CAD 10 MM");
        entrepreneur.setOpen_to_negotiations("Yes");
        entrepreneur.setProfits_in_last_fy("CAD 3 M");
        entrepreneur.setPitch("We are a startup aiming to be the Google of Blockchain.");
        entrepreneur.setInstitution("University of Waterloo");
        entrepreneur.setDegree("Bachelor of Science");
        entrepreneur.setMajor("Computer Science");
        entrepreneur.setYear_of_completion("2020");
        entrepreneur.setWork_experience("5 years");

        when(entrepreneurDatabase.addEntrepreneur(any(EntrepreneurBean.class))).thenReturn(true);
        when(entrepreneurDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        when(entrepreneurDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(true);
        when(entrepreneurDatabase.addCompanyDetails(any(EntrepreneurBean.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/entrepreneur/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(entrepreneur)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(entrepreneurDatabase).addEntrepreneur(entrepreneur);
        verify(entrepreneurDatabase).addEducation(entrepreneur.getUuid(), entrepreneur.getInstitution(),
                entrepreneur.getDegree(), entrepreneur.getMajor(), entrepreneur.getYear_of_completion());
        verify(entrepreneurDatabase).addWorkExperience(entrepreneur.getUuid(), entrepreneur.getWork_experience());
        verify(entrepreneurDatabase).addCompanyDetails(entrepreneur);

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());

        when(entrepreneurDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(false);

        // Call the controller method
        result = mockMvc.perform(post("/entrepreneur/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(entrepreneur)))
                .andExpect(status().isOk())
                .andReturn();

        verify(entrepreneurDatabase).addEducation(entrepreneur.getUuid(), entrepreneur.getInstitution(),
                entrepreneur.getDegree(), entrepreneur.getMajor(), entrepreneur.getYear_of_completion());

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());

        when(entrepreneurDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(false);

        // Call the controller method
        result = mockMvc.perform(post("/entrepreneur/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(entrepreneur)))
                .andExpect(status().isOk())
                .andReturn();

        verify(entrepreneurDatabase).addWorkExperience(entrepreneur.getUuid(), entrepreneur.getWork_experience());

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());

        when(entrepreneurDatabase.addCompanyDetails(any(EntrepreneurBean.class))).thenReturn(false);

        result = mockMvc.perform(post("/entrepreneur/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(entrepreneur)))
                .andExpect(status().isOk())
                .andReturn();

        verify(entrepreneurDatabase).addCompanyDetails(entrepreneur);

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(2)
    public void testUpdateEntrepreneurData() throws Exception {
        EntrepreneurBean entrepreneur = new EntrepreneurBean();
        entrepreneur.setUuid("1234");
        entrepreneur.setFirstName("John");
        entrepreneur.setLastName("Doe");
        entrepreneur.setPhone_number("1234567890");
        entrepreneur.setDomain("Blockchain");
        entrepreneur.setCompany_name("Not Anonymous");
        entrepreneur.setFunding_status("Series A");
        entrepreneur.setEquity_offered("20%");
        entrepreneur.setIs_registered("Yes");
        entrepreneur.setStakeholder("Govt. of Canada");
        entrepreneur.setAssets("CAD 10 MM");
        entrepreneur.setOpen_to_negotiations("Yes");
        entrepreneur.setProfits_in_last_fy("CAD 3 M");
        entrepreneur.setPitch("We are a startup aiming to be the Google of Blockchain.");
        entrepreneur.setInstitution("ABC University");
        entrepreneur.setDegree("Bachelor of Science");
        entrepreneur.setMajor("Computer Science");
        entrepreneur.setYear_of_completion("2020");
        entrepreneur.setWork_experience("5 years");

        when(entrepreneurDatabase.updateEntrepreneur(any(String.class), any(EntrepreneurBean.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(put("/entrepreneur/update/" + entrepreneur.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(entrepreneur)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(entrepreneurDatabase).updateEntrepreneur(entrepreneur.getUuid(), entrepreneur);

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(5)
    public void testDeleteEntrepreneurData() throws Exception {
        String uuid = "1234";

        when(entrepreneurDatabase.deleteEntrepreneur(any(String.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(delete("/entrepreneur/delete/{uuid}", uuid))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(entrepreneurDatabase).deleteEntrepreneur(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(3)
    public void testGetEntrepreneurData() throws Exception {
        String uuid = "1234";
        EntrepreneurBean entrepreneur = new EntrepreneurBean();
        entrepreneur.setUuid(uuid);
        entrepreneur.setFirstName("John");
        entrepreneur.setLastName("Doe");
        entrepreneur.setDomain("Blockchain");
        entrepreneur.setCompany_name("Not Anonymous");
        entrepreneur.setFunding_status("Series A");
        entrepreneur.setEquity_offered("20%");
        entrepreneur.setIs_registered("Yes");
        entrepreneur.setStakeholder("Govt. of Canada");
        entrepreneur.setAssets("CAD 10 MM");
        entrepreneur.setOpen_to_negotiations("Yes");
        entrepreneur.setProfits_in_last_fy("CAD 3 M");
        entrepreneur.setPitch("I am the next Steve Jobs.");
        entrepreneur.setPhone_number("1234567890");
        entrepreneur.setInstitution("ABC University");
        entrepreneur.setDegree("Bachelor of Science");
        entrepreneur.setMajor("Computer Science");
        entrepreneur.setYear_of_completion("2020");
        entrepreneur.setWork_experience("5 years");

        when(entrepreneurDatabase.getEntrepreneur(any(String.class))).thenReturn(entrepreneur);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/entrepreneur/{uuid}", uuid))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(entrepreneurDatabase).getEntrepreneur(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(4)
    public void testGetAllCompanies() throws Exception {
        // Create a list of companies to return
        List<CompanyBean> companies = new ArrayList<>();
        CompanyBean company1 = new CompanyBean();
        company1.setCompany_name("GooBlockChain Inc.");
        company1.setCompany_size("100");
        company1.setEntrepreneurUUID("1234");
        company1.setAssets("CAD 20 M");
        company1.setStakeholder("Govt. of Canada");
        company1.setIs_registered("Yes");
        company1.setEquity_offered("10%");
        company1.setProfits_in_last_fy("CAD 1 M");
        company1.setPitch("We are aiming to be the Google of Blockchain.");
        company1.setFunding_status("Seed");
        company1.setOpen_to_negotiations("Yes");
        companies.add(company1);

        CompanyBean company2 = new CompanyBean();
        company2.setCompany_name("NotGooBlockChain Inc.");
        company2.setCompany_size("101");
        company2.setEntrepreneurUUID("1234");
        company2.setAssets("CAD 20 M");
        company2.setStakeholder("Govt. of Canada");
        company2.setIs_registered("Yes");
        company2.setEquity_offered("11%");
        company2.setProfits_in_last_fy("CAD 20 M");
        company2.setPitch("We are not aiming to be the Google of Blockchain.");
        company2.setFunding_status("Series B");
        company2.setOpen_to_negotiations("Yes");
        companies.add(company2);

        // Set up the mock EntrepreneurDatabase to return the list of companies
        EntrepreneurDatabase entrepreneurDatabase = mock(EntrepreneurDatabase.class);
        when(entrepreneurDatabase.getCompanies()).thenReturn(companies);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/entrepreneur"))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method
        verify(entrepreneurDatabase).getCompanies();

        // Verify that the controller method returns the correct response
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        String responseBody = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<CompanyBean> responseCompanies = objectMapper.readValue(responseBody, new TypeReference<List<CompanyBean>>(){});
        assertEquals(companies.size(), responseCompanies.size());
        for (int i = 0; i < companies.size(); i++) {
            assertEquals(companies.get(i), responseCompanies.get(i));
        }
    }

}