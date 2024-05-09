package com.example.mycasinofx.Model.passwordHashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The PasswordHash class provides functionality for hashing passwords using MD5 algorithm.
 */
public class PasswordHash {
    /**
     * Hashes the provided password using the MD5 algorithm.
     * @param password The password to be hashed.
     * @return The hashed password as a hexadecimal string.
     */
    public static String hashPassword(String password){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] passwordTypeByte = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();

            for (byte b : passwordTypeByte){
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
