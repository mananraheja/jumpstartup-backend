package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Database.EntrepreneurDatabase;
import com.jumpstartup.Entrepreneur.EntrepreneurBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/entrepreneur")
public class EntrepreneurController {

    @PostMapping("/add")
    public ResponseEntity<String> addEntrepreneur(@RequestBody EntrepreneurBean entrepreneur) {

        EntrepreneurDatabase entrepreneurDatabase = new EntrepreneurDatabase();

        // Add the new entrepreneur to the database
        boolean isEntrepreneurAdded = entrepreneurDatabase.addEntrepreneur(entrepreneur);
        if (!isEntrepreneurAdded) {
            return new ResponseEntity<>("Failed to add entrepreneur to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Record Added", HttpStatus.OK);
    }

    @PutMapping("/update/{UUID}")
    public ResponseEntity<String> updateEntrepreneur(@PathVariable String UUID, @RequestBody EntrepreneurBean entrepreneur) {
        EntrepreneurDatabase entrepreneurDatabase = new EntrepreneurDatabase();

        // Update the entrepreneur in the database
        boolean isEntrepreneurUpdated = entrepreneurDatabase.updateEntrepreneur(UUID, entrepreneur);
        if (!isEntrepreneurUpdated) {
            return new ResponseEntity<>("Failed to update entrepreneur in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{UUID}")
    public ResponseEntity<String> deleteEntrepreneur(@PathVariable String UUID) {
        EntrepreneurDatabase entrepreneurDatabase = new EntrepreneurDatabase();

        // Delete the entrepreneur from the database
        boolean isEntrepreneurDeleted = entrepreneurDatabase.deleteEntrepreneur(UUID);
        if (!isEntrepreneurDeleted) {
            return new ResponseEntity<>("Failed to delete entrepreneur from database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/{UUID}")
    public ResponseEntity<EntrepreneurBean> getEntrepreneur(@PathVariable String UUID) {
        EntrepreneurDatabase entrepreneurDatabase = new EntrepreneurDatabase();

        // Retrieve the entrepreneur from the database
        EntrepreneurBean entrepreneur = entrepreneurDatabase.getEntrepreneur(UUID);
        if (entrepreneur == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the entrepreneur data as a JSON response
        return new ResponseEntity<>(entrepreneur, HttpStatus.OK);
    }
}
