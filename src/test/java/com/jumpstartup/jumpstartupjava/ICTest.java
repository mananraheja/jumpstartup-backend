package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Investor.InvestorBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class ICTest {

    @Mock
    InvestorController controller = new InvestorController();


    @Test
    public void testAddInvestor() {
        InvestorBean investor = new InvestorBean();
        investor.setUuid(UUID.randomUUID().toString());
        investor.setDomain("Artificial Intelligence");
        investor.setPhone_number("9876543210");
        investor.setBrands_built("ABC");
        investor.setFunding_available(100000);
        ResponseEntity<String> mockResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(controller.addInvestor(investor)).thenReturn(mockResponse);
        ResponseEntity<String> response = controller.addInvestor(investor);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAddInvestorFails(){
        InvestorBean investor = new InvestorBean();
        ResponseEntity<String> mockResponse = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Mockito.when(controller.addInvestor(investor)).thenReturn(mockResponse);
        ResponseEntity<String> response = controller.addInvestor(investor);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testUpdateInvestor() {
        InvestorBean investor = new InvestorBean();
        investor.setUuid(UUID.randomUUID().toString());
        investor.setDomain("Cloud Computing");
        investor.setPhone_number("5558889999");
        investor.setBrands_built("XYZ");
        investor.setFunding_available(75000);
        ResponseEntity<String> mockResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(controller.updateInvestor(investor.getUuid(), investor)).thenReturn(mockResponse);
        ResponseEntity<String> response = controller.updateInvestor(investor.getUuid(), investor);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateInvestorFailed() {
        InvestorBean investor = new InvestorBean();
        ResponseEntity<String> mockResponse = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Mockito.when(controller.updateInvestor(investor.getUuid(), investor)).thenReturn(mockResponse);
        ResponseEntity<String> response = controller.updateInvestor(investor.getUuid(), investor);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDeleteInvestor() {
        InvestorBean investor = new InvestorBean();
        investor.setUuid(UUID.randomUUID().toString());
        investor.setDomain("Data Analytics");
        investor.setPhone_number("1112223333");
        investor.setBrands_built("LMN");
        investor.setFunding_available(25000);
        ResponseEntity<String> mockResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(controller.deleteInvestor(investor.getUuid())).thenReturn(mockResponse);
        ResponseEntity<String> response = controller.deleteInvestor(investor.getUuid());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteInvestorError() {
        InvestorBean investor = new InvestorBean();
        ResponseEntity<String> mockResponse = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Mockito.when(controller.deleteInvestor(investor.getUuid())).thenReturn(mockResponse);
        ResponseEntity<String> response = controller.deleteInvestor(investor.getUuid());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetInvestor() {
        InvestorBean investor = new InvestorBean();
        String uuid = UUID.randomUUID().toString();
        investor.setUuid(uuid);
        investor.setDomain("Image Processing");
        investor.setBrands_built("XYX");
        investor.setPhone_number("1234567890");
        investor.setFunding_available(50000);
        ResponseEntity<InvestorBean> mockResponse = new ResponseEntity<>(investor,HttpStatus.OK);
        Mockito.when(controller.getInvestor(uuid)).thenReturn(mockResponse);
        ResponseEntity<InvestorBean> response = controller.getInvestor(uuid);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(investor, response.getBody());
    }

    @Test
    public void testGetInvestorError() {
        InvestorBean investor = new InvestorBean();
        ResponseEntity<InvestorBean> mockResponse = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Mockito.when(controller.getInvestor("67890")).thenReturn(mockResponse);
        ResponseEntity<InvestorBean> response = controller.getInvestor("67890");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

}
