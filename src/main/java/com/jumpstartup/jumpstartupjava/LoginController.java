package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Database.LoginDatabase;
import com.jumpstartup.Encryption.PasswordEncryption;
import com.jumpstartup.LoginBody.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/{username}")
    public ResponseEntity<LoginRequest> login(@PathVariable String username) {

        LoginDatabase loginDatabase = new LoginDatabase();
        LoginRequest loginRequest = null;

        loginRequest = loginDatabase.getDetails(username);

        if (loginRequest == null) {
            logger.warn("User with username {} not found.", username);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("User with username {} exists.", username);
        return new ResponseEntity<>(loginRequest,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> loginSubmit(@RequestBody LoginRequest loginRequest) {
        if (PasswordEncryption.decryptPassword(loginRequest.getUsername(), loginRequest.getHashpass())) {
            logger.info("User {} has been authorized.", loginRequest.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.warn("Failed to authorize user {}.", loginRequest.getUsername());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signupSubmit(@RequestBody LoginRequest loginRequest) {
        loginRequest.setHashpass(PasswordEncryption.encryptPassword(loginRequest.getHashpass()));
        boolean success = signup(loginRequest.getUuid(), loginRequest.getUsername(), loginRequest.getHashpass(), loginRequest.getEmail(), loginRequest.getType());
        if (success) {
            logger.info("User {} signed up successfully.", loginRequest.getUsername());
            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            logger.warn("Failed to sign up user {}.", loginRequest.getUsername());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private boolean authenticate(String username, String password) {
        LoginDatabase auth = new LoginDatabase();
        return auth.authenticate(username,password);
    }

    private boolean signup(String UUID, String username, String password, String email, String type) {
        LoginDatabase addUser = new LoginDatabase();
        return addUser.newUser(UUID,username,email,password,type);
    }


}
