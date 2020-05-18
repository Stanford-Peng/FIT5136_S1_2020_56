package com.fit5136.missionToMars.util;

public class StringUtil {
    public static String removeBoundary(String input){
        StringBuffer buffer = new StringBuffer(input);
        if (buffer.length() > 1){
            buffer.delete(buffer.length() - 1, buffer.length()).delete(0, 1);
            return buffer.toString();
        }
        return "";
    }
}
