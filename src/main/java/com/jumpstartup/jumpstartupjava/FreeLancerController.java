package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Freelancer.FreelancerBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jumpstartup.Database.FreeLancerDatabase;

@RestController
@RequestMapping("/freelancer")
public class FreeLancerController {

    @PostMapping("/add")
    public ResponseEntity<String> addFreelancerData(@RequestBody FreelancerBean freelancer) {

        FreeLancerDatabase flDatabase = new FreeLancerDatabase();

        // Add the new freelancer to the database
        boolean isFreelancerAdded = flDatabase.addFreelancer(freelancer);
        if (!isFreelancerAdded) {
            return new ResponseEntity<>("Failed to add freelancer to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Add the new education to the database
        boolean isEducationAdded = flDatabase.addEducation(freelancer.getUuid(), freelancer.getInstitution(), freelancer.getDegree(),
                freelancer.getMajor(), freelancer.getYear_of_completion());
        if (!isEducationAdded) {
            return new ResponseEntity<>("Failed to add education to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Add the new work experience to the database
        boolean isWorkExperienceAdded = flDatabase.addWorkExperience(freelancer.getUuid(), freelancer.getWork_experience());
        if (!isWorkExperienceAdded) {
            return new ResponseEntity<>("Failed to add work experience to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Record Added", HttpStatus.OK);
    }

    @PutMapping("/update/{UUID}")
    public ResponseEntity<String> updateFreelancer(@PathVariable String UUID, @RequestBody FreelancerBean freelancer) {
        FreeLancerDatabase flDatabase = new FreeLancerDatabase();

        // Update the freelancer in the database
        boolean isFreelancerUpdated = flDatabase.updateFreelancer(UUID, freelancer);
        if (!isFreelancerUpdated) {
            return new ResponseEntity<>("Failed to update freelancer in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{UUID}")
    public ResponseEntity<String> deleteFreelancer(@PathVariable String UUID) {
        FreeLancerDatabase flDatabase = new FreeLancerDatabase();

        // Delete the freelancer from the database
        boolean isFreelancerDeleted = flDatabase.deleteFreelancer(UUID);
        if (!isFreelancerDeleted) {
            return new ResponseEntity<>("Failed to delete freelancer from database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/{UUID}")
    public ResponseEntity<FreelancerBean> getFreelancer(@PathVariable String UUID) {
        FreeLancerDatabase flDatabase = new FreeLancerDatabase();

        // Retrieve the freelancer from the database
        FreelancerBean freelancer = flDatabase.getFreelancer(UUID);
        if (freelancer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the freelancer data as a JSON response
        return new ResponseEntity<>(freelancer, HttpStatus.OK);
    }



}
