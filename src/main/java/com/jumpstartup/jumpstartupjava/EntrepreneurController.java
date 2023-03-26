package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Database.EntrepreneurDatabase;
import com.jumpstartup.Database.FreeLancerDatabase;
import com.jumpstartup.Entrepreneur.EntrepreneurBean;
import com.jumpstartup.Freelancer.FreelancerBean;
import com.jumpstartup.Model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/entrepreneur")
@CrossOrigin(origins = "http://localhost:4200")
public class EntrepreneurController {

    private static final Logger logger = LoggerFactory.getLogger(EntrepreneurController.class);
    
    @PostMapping("/add")
    public ResponseEntity<?> addEntrepreneur(@RequestBody EntrepreneurBean entrepreneur) {
        logger.info("Adding new entrepreneur data: {}", entrepreneur.toString());
        EntrepreneurDatabase entrepreneurDatabase = new EntrepreneurDatabase();

        // Add the new entrepreneur to the database
        boolean isEntrepreneurAdded = entrepreneurDatabase.addEntrepreneur(entrepreneur);
        if (!isEntrepreneurAdded) {
            logger.error("Failed to add entrepreneur to database");
            return new ResponseEntity<>("Failed to add entrepreneur to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Add the new education to the database
        boolean isEducationAdded = entrepreneurDatabase.addEducation(entrepreneur.getUuid(), entrepreneur.getInstitution(), entrepreneur.getDegree(),
                entrepreneur.getMajor(), entrepreneur.getYear_of_completion());
        if (!isEducationAdded) {
            logger.error("Failed to add education to database");
            return new ResponseEntity<>("Failed to add education to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Add the new work experience to the database
        boolean isWorkExperienceAdded = entrepreneurDatabase.addWorkExperience(entrepreneur.getUuid(), entrepreneur.getWork_experience());
        if (!isWorkExperienceAdded) {
            logger.error("Failed to add work experience to database");
            return new ResponseEntity<>("Failed to add work experience to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(Status.buildStatus("ADDENT001","Added Entrepreneur succesfully"), HttpStatus.OK);
    }

//    @PutMapping("/update/{UUID}")
//    public ResponseEntity<String> updateEntrepreneur(@PathVariable String UUID, @RequestBody EntrepreneurBean entrepreneur) {
//        logger.info("Updating entrepreneur data: {}", entrepreneur.toString());
//        EntrepreneurDatabase entrepreneurDatabase = new EntrepreneurDatabase();
//
//        // Update the entrepreneur in the database
//        boolean isEntrepreneurUpdated = entrepreneurDatabase.updateEntrepreneur(UUID, entrepreneur);
//        if (!isEntrepreneurUpdated) {
//            logger.error("Failed to update entrepreneur {} in database", UUID);
//            return new ResponseEntity<>("Failed to update entrepreneur in database", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        logger.info("Entrepreneur record updated successfully.");
//        return new ResponseEntity<>("Updated", HttpStatus.OK);
//    }

    @PutMapping("/update/{UUID}")
    public ResponseEntity<String> updateEntrepreneur(@PathVariable String UUID, @RequestBody EntrepreneurBean entrepreneur) {
        logger.info("Updating entrepreneur data: {}", entrepreneur.toString());
        EntrepreneurDatabase entrepreneurDatabase = new EntrepreneurDatabase();

        // Update the entrepreneur in the database
        boolean isEntrepreneurUpdated = entrepreneurDatabase.updateEntrepreneur(UUID, entrepreneur);
        if (!isEntrepreneurUpdated) {
            logger.error("Failed to update entrepreneur {} in database", UUID);
            return new ResponseEntity<>("Failed to update entrepreneur in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Entrepreneur record updated successfully.");
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{UUID}")
    public ResponseEntity<String> deleteEntrepreneur(@PathVariable String UUID) {
        logger.info("Deleting entrepreneur: {}", UUID);
        EntrepreneurDatabase entrepreneurDatabase = new EntrepreneurDatabase();

        // Delete the entrepreneur from the database
        boolean isEntrepreneurDeleted = entrepreneurDatabase.deleteEntrepreneur(UUID);
        if (!isEntrepreneurDeleted) {
            logger.error("Failed to delete entrepreneur {}.", UUID);
            return new ResponseEntity<>("Failed to delete entrepreneur from database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Deleted entrepreneur {} successfully.", UUID);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/{UUID}")
    public ResponseEntity<EntrepreneurBean> getEntrepreneur(@PathVariable String UUID) {
        logger.info("Getting entrepreneur data: {}", UUID);
        EntrepreneurDatabase entrepreneurDatabase = new EntrepreneurDatabase();

        // Retrieve the entrepreneur from the database
        EntrepreneurBean entrepreneur = entrepreneurDatabase.getEntrepreneur(UUID);
        if (entrepreneur == null) {
            logger.error("Entrepreneur {} not found in database.", UUID);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the entrepreneur data as a JSON response
        logger.info("Entrepreneur {} details fetched successfully.", UUID);
        return new ResponseEntity<>(entrepreneur, HttpStatus.OK);
    }
}
