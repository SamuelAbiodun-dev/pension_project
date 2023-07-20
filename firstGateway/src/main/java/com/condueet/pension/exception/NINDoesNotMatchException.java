package com.condueet.pension.exception;

public class NINDoesNotMatchException extends Exception{
    public NINDoesNotMatchException(String message){
        super(message);
    }
}
