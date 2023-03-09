package com.jumpstartup.jumpstartupjava;


import com.jumpstartup.Investor.InvestorBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jumpstartup.Database.InvestorDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/investor")
public class InvestorController {

    private static final Logger logger = LoggerFactory.getLogger(FreeLancerController.class);

    @PostMapping("/add")
    public ResponseEntity<String> addInvestor(@RequestBody InvestorBean investor) {
        logger.info("Adding new investor data: {}", investor.toString());
        InvestorDatabase investorDatabase = new InvestorDatabase();

        // Add the new investor to the database
        boolean isInvestorAdded = investorDatabase.addInvestor(investor);
        if (!isInvestorAdded) {
            logger.error("Failed to add investor to database.");
            return new ResponseEntity<>("Failed to add investor to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Investor record added successfully.");
        return new ResponseEntity<>("Record Added", HttpStatus.OK);
    }

    @PutMapping("/update/{UUID}")
    public ResponseEntity<String> updateInvestor(@PathVariable String UUID, @RequestBody InvestorBean investor) {
        logger.info("Updating investor data: {}", UUID);
        InvestorDatabase investorDatabase = new InvestorDatabase();

        // Update the investor in the database
        boolean isInvestorUpdated = investorDatabase.updateInvestor(UUID, investor);
        if (!isInvestorUpdated) {
            logger.error("Failed to update investor {} in database.", UUID);
            return new ResponseEntity<>("Failed to update investor in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Investor record updated successfully.");
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{UUID}")
    public ResponseEntity<String> deleteInvestor(@PathVariable String UUID) {
        logger.info("Deleting investor: {}", UUID);
        InvestorDatabase investorDatabase = new InvestorDatabase();

        // Delete the investor from the database
        boolean isInvestorDeleted = investorDatabase.deleteInvestor(UUID);
        if (!isInvestorDeleted) {
            logger.error("Failed to delete investor {}.", UUID);
            return new ResponseEntity<>("Failed to delete investor from database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Deleted investor {} successfully.", UUID);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/{UUID}")
    public ResponseEntity<InvestorBean> getInvestor(@PathVariable String UUID) {
        logger.info("Getting investor data: {}", UUID);
        InvestorDatabase investorDatabase = new InvestorDatabase();

        // Retrieve the investor from the database
        InvestorBean investor = investorDatabase.getInvestor(UUID);
        if (investor == null) {
            logger.error("Investor {} not found in database.", UUID);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the investor data as a JSON response
        logger.info("Investor {} details fetched successfully.", UUID);
        return new ResponseEntity<>(investor, HttpStatus.OK);
    }
}
