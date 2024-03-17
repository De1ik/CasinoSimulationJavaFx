package com.example.mycasinofx.controllers.Registration;

import com.example.mycasinofx.Model.exceptions.AgeFailAccess;

public class CheckValidData {

    static public boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    static public boolean isValidPasswordLength(String password){
        return (password.length() >= 6);
    }

    static public boolean isValidNameLength(String name){
        return (name.length() >= 3);
    }

    static void validate (int age) throws AgeFailAccess {
        if(age < 18){
            throw new AgeFailAccess("Minimum Age Is 18 Years");
        }
    }
}
