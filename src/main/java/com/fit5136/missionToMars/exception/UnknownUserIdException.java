package com.fit5136.missionToMars.exception;

public class UnknownUserIdException extends RuntimeException {
    public UnknownUserIdException(String message){
        super(message);
    }
}
