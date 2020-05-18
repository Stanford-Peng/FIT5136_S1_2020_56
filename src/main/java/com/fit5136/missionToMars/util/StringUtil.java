package com.fit5136.missionToMars.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

public class StringUtil {
    public static String removeBoundary(String input){
        StringBuffer buffer = new StringBuffer(input);
        if (buffer.length() > 1){
            buffer.delete(buffer.length() - 1, buffer.length()).delete(0, 1);
            return buffer.toString();
        }
        return "";
    }

    public static String getMD5Str(String message){

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(message.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return String.format("%032x", new BigInteger(1, md5.digest()));
    }
}
