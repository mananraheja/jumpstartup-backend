package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Freelancer.FreelancerBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/freelancer")
public class FreeLancerController {

    @PostMapping("/add")
    public ResponseEntity<String> addFreelancer(@RequestBody FreelancerBean freelancer) {
        // Add logic to add a new freelancer to the database
        System.out.print(" I am here");
        return new ResponseEntity<>("Record Added",HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFreelancer(@PathVariable int id, @RequestBody FreelancerBean freelancer) {
        // Add logic to update a freelancer in the database

        return new ResponseEntity<>("Updated",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFreelancer(@PathVariable int id) {
        // Add logic to delete a freelancer from the database

        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public FreelancerBean getFreelancer(@PathVariable int id) {
        // Add logic to retrieve a freelancer from the database


        return new FreelancerBean();
    }
}
