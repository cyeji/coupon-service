package com.yeji.couponservice.application.port.in;

public interface CreateCouponWithRedisUseCase {

    CouponResponse decrease(String couponId) throws InterruptedException;
}
