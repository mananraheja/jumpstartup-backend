package com.jumpstartup.jumpstartupjava;

import static org.junit.jupiter.api.Assertions.*;

import com.jumpstartup.Database.InvestorDatabase;
import org.junit.jupiter.api.*;

import com.jumpstartup.Investor.InvestorBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InvestorDBTest {

    private InvestorDatabase investorDatabase;
    private InvestorBean investor;

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


    @AfterAll
    static void tearDownAfterClass() throws Exception {
        // Tear down any resources needed after all test methods
    }

    @BeforeEach
    void setUp() throws Exception {
        investorDatabase = new InvestorDatabase();
        investor = new InvestorBean();
        investor.setUuid("1234-5678-9012-3456");
        investor.setPhone_number("1234567890");
        investor.setDomain("tech");
        investor.setFunding_available(5000000.0f);
        investor.setBrands_built("Brand A, Brand B");
    }

    @AfterEach
    void tearDown() throws Exception {
        // Tear down any resources needed after each test method
    }

    @Test
    void testAddInvestor() {
        assertTrue(investorDatabase.addInvestor(investor));
        assertNotNull(investorDatabase.getInvestor(investor.getUuid()));
    }

    @Test
    void testUpdateInvestor() {
        investorDatabase.addInvestor(investor);
        investor.setPhone_number("9876543210");
        assertTrue(investorDatabase.updateInvestor(investor.getUuid(), investor));
        assertEquals("9876543210", investorDatabase.getInvestor(investor.getUuid()).getPhone_number());
    }

    @Test
    void testDeleteInvestor() {
        investorDatabase.addInvestor(investor);
        assertTrue(investorDatabase.deleteInvestor(investor.getUuid()));
        assertNull(investorDatabase.getInvestor(investor.getUuid()));
    }

    @Test
    void testGetInvestor() {
        investorDatabase.addInvestor(investor);
        InvestorBean retrievedInvestor = investorDatabase.getInvestor(investor.getUuid());
        assertNotNull(retrievedInvestor);
        assertEquals(investor.getUuid(), retrievedInvestor.getUuid());
        assertEquals(investor.getPhone_number(), retrievedInvestor.getPhone_number());
        assertEquals(investor.getDomain(), retrievedInvestor.getDomain());
        assertEquals(investor.getFunding_available(), retrievedInvestor.getFunding_available());
        assertEquals(investor.getBrands_built(), retrievedInvestor.getBrands_built());
    }

    @Test
    void testGetInvestorWithInvalidUUID() {
        assertNull(investorDatabase.getInvestor("invalid-uuid"));
    }

}
