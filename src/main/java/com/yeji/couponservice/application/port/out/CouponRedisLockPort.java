package com.yeji.couponservice.application.port.out;

public interface CouponRedisLockPort {

    Boolean lock(String key);

    Boolean unLock(String key);

}
