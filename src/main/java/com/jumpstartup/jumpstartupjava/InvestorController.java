package com.jumpstartup.jumpstartupjava;


import com.jumpstartup.Investor.InvestorBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jumpstartup.Database.InvestorDatabase;


@RestController
@RequestMapping("/investor")
public class InvestorController {

    @PostMapping("/add")
    public ResponseEntity<String> addInvestor(@RequestBody InvestorBean investor) {

        InvestorDatabase investorDatabase = new InvestorDatabase();

        // Add the new investor to the database
        boolean isInvestorAdded = investorDatabase.addInvestor(investor);
        if (!isInvestorAdded) {
            return new ResponseEntity<>("Failed to add investor to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Record Added", HttpStatus.OK);
    }

    @PutMapping("/update/{UUID}")
    public ResponseEntity<String> updateInvestor(@PathVariable String UUID, @RequestBody InvestorBean investor) {
        InvestorDatabase investorDatabase = new InvestorDatabase();

        // Update the investor in the database
        boolean isInvestorUpdated = investorDatabase.updateInvestor(UUID, investor);
        if (!isInvestorUpdated) {
            return new ResponseEntity<>("Failed to update investor in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{UUID}")
    public ResponseEntity<String> deleteInvestor(@PathVariable String UUID) {
        InvestorDatabase investorDatabase = new InvestorDatabase();

        // Delete the investor from the database
        boolean isInvestorDeleted = investorDatabase.deleteInvestor(UUID);
        if (!isInvestorDeleted) {
            return new ResponseEntity<>("Failed to delete investor from database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/{UUID}")
    public ResponseEntity<InvestorBean> getInvestor(@PathVariable String UUID) {
        InvestorDatabase investorDatabase = new InvestorDatabase();

        // Retrieve the investor from the database
        InvestorBean investor = investorDatabase.getInvestor(UUID);
        if (investor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the investor data as a JSON response
        return new ResponseEntity<>(investor, HttpStatus.OK);
    }


}
