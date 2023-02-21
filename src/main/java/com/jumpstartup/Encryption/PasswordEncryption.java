package com.jumpstartup.Encryption;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordEncryption {
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}