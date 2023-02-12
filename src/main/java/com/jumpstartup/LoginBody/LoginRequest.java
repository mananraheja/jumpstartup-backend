package com.jumpstartup.LoginBody;

public class LoginRequest {

    private String username;
    private String email;
    private String hashpass;
    private String type;

    public LoginRequest(){
    }
    public LoginRequest(String username, String email, String hashpass, String type){
        this.username = username;
        this.email = email;
        this.hashpass = hashpass;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashpass() {
        return hashpass;
    }

    public void setHashpass(String hashpass) {
        this.hashpass = hashpass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
