package com.yeji.couponservice.common.exceptions;

public class TimeoutException extends RuntimeException {

    public TimeoutException() {
        super();
    }

    public TimeoutException(String message) {
        super(message);
    }
}
