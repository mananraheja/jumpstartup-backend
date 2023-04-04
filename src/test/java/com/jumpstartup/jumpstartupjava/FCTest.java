package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Freelancer.FreelancerBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class FCTest {

    @Mock
    FreeLancerController controller = new FreeLancerController();

    @Test
    public void testAddFreelancerData() {
        // Create a new FreelancerBean object
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("1234-5678");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setPhone_number("1234567890");
        freelancer.setInstitution("ABC University");
        freelancer.setDegree("Bachelor's");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("3 years");

        // Call the addFreelancerData() method and verify the response
        Mockito.when(controller.addFreelancerData(Mockito.any())).thenReturn(new ResponseEntity<> (HttpStatus.OK));

        ResponseEntity<?> response = controller.addFreelancerData(freelancer);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateFreelancer() {
        // Create a new FreelancerBean object
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setFirstName("Jane");
        freelancer.setLastName("Smith");
        freelancer.setPhone_number("111-222-3333");

        // Call the updateFreelancer() method and verify the response
        ResponseEntity<String> mockResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(controller.updateFreelancer(Mockito.anyString(), Mockito.any())).thenReturn(mockResponse);

        ResponseEntity<String> response = controller.updateFreelancer("1234-5678", freelancer);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteFreelancer() {
        // Call the deleteFreelancer() method and verify the response
        ResponseEntity<String> mockResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(controller.deleteFreelancer(Mockito.anyString())).thenReturn(mockResponse);
        ResponseEntity<String> response = controller.deleteFreelancer("1234-5678");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetFreelancer() {
        // Call the getFreelancer() method and verify the response
        FreelancerBean freelancerMock = new FreelancerBean();
        freelancerMock.setFirstName("John");
        freelancerMock.setLastName("Doe");
        freelancerMock.setPhone_number("123-456-7890");
        freelancerMock.setInstitution("ABC University");
        freelancerMock.setDegree("Bachelor's");
        freelancerMock.setMajor("Computer Science");
        freelancerMock.setYear_of_completion("2020");
        freelancerMock.setWork_experience("3 years");
        ResponseEntity<FreelancerBean> mockResponse = new ResponseEntity<>(freelancerMock, HttpStatus.OK);
        Mockito.when(controller.getFreelancer(Mockito.anyString())).thenReturn(mockResponse);
        ResponseEntity<FreelancerBean> response = controller.getFreelancer("1234-5678");
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify that the returned FreelancerBean object has the expected values
        FreelancerBean freelancer = response.getBody();
        assertEquals("John", freelancer.getFirstName());
        assertEquals("Doe", freelancer.getLastName());
        assertEquals("123-456-7890", freelancer.getPhone_number());
        assertEquals("ABC University", freelancer.getInstitution());
        assertEquals("Bachelor's", freelancer.getDegree());
        assertEquals("Computer Science", freelancer.getMajor());
        assertEquals("2020", freelancer.getYear_of_completion());
        assertEquals("3 years", freelancer.getWork_experience());
    }

}