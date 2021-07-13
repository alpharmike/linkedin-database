package com.project.linkedindatabase.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SHAHashing {

    public static String get_SHA_512_SecurePassword(String passwordToHash)
    {


        String generatedPassword = null;
        try {
            byte[] salt = getSalt();
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }



    //Add salt
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        byte[] salt = "9999999900000001".getBytes();;

        return salt;
    }
}
