package com.example.mycasinofx.Model.Hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {
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
