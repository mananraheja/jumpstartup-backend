package com.jumpstartup.jumpstartupjava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.jumpstartup.jumpstartupjava.InvestorController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jumpstartup.Investor.InvestorBean;
import com.jumpstartup.Database.InvestorDatabase;

import java.util.UUID;

public class ICTest {

    private InvestorController investorController;
    private InvestorDatabase investorDatabase;

    @Before
    public void setUp() {
        investorController = new InvestorController();
        investorDatabase = mock(InvestorDatabase.class);
    }

    @Test
    public void testAddInvestor() {
        // Create an instance of InvestorBean to be added to the database
        InvestorBean investor = new InvestorBean();
        investor.setUuid(UUID.randomUUID().toString());
        investor.setDomain("Artificial Intelligence");
        investor.setPhone_number("9876543210");
        investor.setBrands_built("ABC");
        investor.setFunding_available(100000);

        // Mock the behavior of the InvestorDatabase class
        when(investorDatabase.addInvestor(investor)).thenReturn(true);

        // Call the addInvestor method of the InvestorController class
        ResponseEntity<String> response = investorController.addInvestor(investor);

        // Check the response status code and message
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Record Added", response.getBody());

        // Test for scenario when adding InvestorBean to the database fails
        when(investorDatabase.addInvestor(investor)).thenReturn(false);
        response = investorController.addInvestor(investor);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to add investor to database", response.getBody());
    }

    @Test
    public void testUpdateInvestor() {
        // Create an instance of InvestorBean to be updated in the database
        InvestorBean investor = new InvestorBean();
        investor.setUuid(UUID.randomUUID().toString());
        investor.setDomain("Cloud Computing");
        investor.setPhone_number("5558889999");
        investor.setBrands_built("XYZ");
        investor.setFunding_available(75000);

        // Mock the behavior of the InvestorDatabase class
        when(investorDatabase.updateInvestor(investor.getUuid(), investor)).thenReturn(true);

        // Call the updateInvestor method of the InvestorController class
        ResponseEntity<String> response = investorController.updateInvestor(investor.getUuid(), investor);

        // Check the response status code and message
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated", response.getBody());

        // Test for scenario when updating InvestorBean in the database fails
        when(investorDatabase.updateInvestor(investor.getUuid(), investor)).thenReturn(false);
        response = investorController.updateInvestor(investor.getUuid(), investor);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to update investor in database", response.getBody());
    }

    @Test
    public void testDeleteInvestor() {
        // Create an instance of InvestorBean to be deleted from the database
        InvestorBean investor = new InvestorBean();
        investor.setUuid(UUID.randomUUID().toString());
        investor.setDomain("Data Analytics");
        investor.setPhone_number("1112223333");
        investor.setBrands_built("LMN");
        investor.setFunding_available(25000);

        // Mock the behavior of the InvestorDatabase class
        when(investorDatabase.deleteInvestor(investor.getUuid())).thenReturn(true);

        // Call the deleteInvestor method of the InvestorController class
        ResponseEntity<String> response = investorController.deleteInvestor(investor.getUuid());

        // Check the response status code and message
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted", response.getBody());

        // Test for scenario when deleting InvestorBean from the database fails
        when(investorDatabase.deleteInvestor(investor.getUuid())).thenReturn(false);
        response = investorController.deleteInvestor(investor.getUuid());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to delete investor from database", response.getBody());
    }

    @Test
    public void testGetInvestor() {
        // Create an instance of InvestorBean to be retrieved from the database
        InvestorBean investor = new InvestorBean();
        String uuid = UUID.randomUUID().toString();
        investor.setUuid(uuid);
        investor.setDomain("Image Processing");
        investor.setBrands_built("XYX");
        investor.setPhone_number("1234567890");
        investor.setFunding_available(50000);

        // Mock the behavior of the InvestorDatabase class
        when(investorDatabase.getInvestor(uuid)).thenReturn(investor);

        // Call the getInvestor method of the InvestorController class
        ResponseEntity<InvestorBean> response = investorController.getInvestor("12345");

        // Check the response status code and InvestorBean
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(investor, response.getBody());

        // Test for scenario when InvestorBean is not found in the database
        when(investorDatabase.getInvestor("67890")).thenReturn(null);
        response = investorController.getInvestor("67890");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

}