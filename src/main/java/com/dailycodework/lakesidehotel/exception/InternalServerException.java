package com.dailycodework.lakesidehotel.exception;

/**
 * @author Simpson Alfred
 */

public class InternalServerException extends RuntimeException {

    public InternalServerException(String message) {
        super(message);
    }
    //extends Throwable 하면 에러
}
