package com.gym.backend.Service;

import org.springframework.stereotype.Service;

@Service
public class emailauthentication {
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
 
}