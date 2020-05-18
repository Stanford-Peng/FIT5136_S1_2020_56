package com.fit5136.missionToMars.exception;

public class DuplicateUserNameException extends RuntimeException{
    public DuplicateUserNameException(String message){
        super(message);
    }
}
