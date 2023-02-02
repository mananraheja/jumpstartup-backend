package com.jumpstartup.jumpstartupjava;

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
        if (authenticate(loginRequest.getUser(), loginRequest.getPass())) {

            return new ResponseEntity<>("AUTHORIZED",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NOT AUTHORIZED",HttpStatus.UNAUTHORIZED);
        }
    }


    private boolean authenticate(String username, String password) {
        // Database logic here
        return true;
    }
}
