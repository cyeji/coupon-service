package com.yeji.couponservice.common.exceptions;

/**
 * 쿠폰이 이미 존재할 경우 Exception 처리
 */
public class AlreadyExistsCouponException extends RuntimeException {

    public AlreadyExistsCouponException(String message) {
        super(message);
    }


}
