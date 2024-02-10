package com.yeji.couponservice.application.port.out;

public interface CouponRedisLockPort {

    Boolean lock(String key) throws InterruptedException;

    Boolean unLock(String key);

}
