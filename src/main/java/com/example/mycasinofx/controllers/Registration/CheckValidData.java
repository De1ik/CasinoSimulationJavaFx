package com.example.mycasinofx.controllers.Registration;

import com.example.mycasinofx.Model.exceptions.AgeFailAccess;

/**
 * Utility class for checking the validity of data.
 */
public class CheckValidData {

    /**
     * Checks if the given email is valid.
     * @param email The email to check.
     * @return True if the email is valid, false otherwise.
     */
    static public boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    /**
     * Checks if the given password meets the minimum length requirement.
     * @param password The password to check.
     * @return True if the password length is valid, false otherwise.
     */
    static public boolean isValidPasswordLength(String password){
        return (password.length() >= 6);
    }

    /**
     * Checks if the given name meets the minimum length requirement.
     * @param name The name to check.
     * @return True if the name length is valid, false otherwise.
     */
    static public boolean isValidNameLength(String name){
        return (name.length() >= 3);
    }

    /**
     * Validates the age against a minimum threshold.
     * @param age The age to validate.
     * @throws AgeFailAccess If the age is below the minimum threshold.
     */
    static void validate (int age) throws AgeFailAccess {
        if(age < 18){
            throw new AgeFailAccess("Minimum Age Is 18 Years");
        }
    }
}
