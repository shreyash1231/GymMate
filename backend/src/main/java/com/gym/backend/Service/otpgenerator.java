package com.gym.backend.Service;

import java.util.Random;

import org.springframework.stereotype.Service;


@Service
public class otpgenerator {
    public String generateOTP(int length) {  
        String numbers = "0123456789";  
        Random rndm_method = new Random();  
        char[] otp = new char[length];  
        for (int i = 0; i < length; i++) {  
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));  
        }  
        return new String(otp);  
    }  
}
