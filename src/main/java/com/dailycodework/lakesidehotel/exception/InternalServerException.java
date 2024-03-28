package com.dailycodework.lakesidehotel.exception;

public class InternalServerException extends RuntimeException{
    
    //extends Throwable 하면 에러
    public InternalServerException(String message) {
        super(message);
    }
}
