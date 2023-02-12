package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Database.LoginDatabase;
import com.jumpstartup.LoginBody.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ResponseEntity<Object> login() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> loginSubmit(@RequestBody LoginRequest loginRequest) {
        if (authenticate(loginRequest.getUsername(), loginRequest.getHashpass())) {
            return new ResponseEntity<>("AUTHORIZED",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NOT AUTHORIZED",HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signupSubmit(@RequestBody LoginRequest loginRequest) {
        boolean success = signup(loginRequest.getUsername(),loginRequest.getHashpass(),loginRequest.getEmail(), loginRequest.getType());
        if (success) {
            return new ResponseEntity<>("SIGNUP SUCCESSFUL", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("SIGNUP FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private boolean authenticate(String username, String password) {
        LoginDatabase auth = new LoginDatabase();
        return auth.authenticate(username,password);
    }

    private boolean signup(String username,String password,String email, String type){
        LoginDatabase addUser = new LoginDatabase();
        return addUser.newUser(username,email,password,type);
    }


}
