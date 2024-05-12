package com.dailycodework.lakesidehotel.exception;

<<<<<<< HEAD
/**
 * @author Simpson Alfred
 */

public class InternalServerException extends RuntimeException {
=======
public class InternalServerException extends RuntimeException{
    
    //extends Throwable 하면 에러
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    public InternalServerException(String message) {
        super(message);
    }
}
