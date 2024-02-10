package com.yeji.couponservice.application.service;

import com.yeji.couponservice.application.port.in.CouponResponse;
import com.yeji.couponservice.application.port.in.CreateCouponWithRedisUseCase;
import com.yeji.couponservice.application.port.out.CouponRedisLockPort;
import com.yeji.couponservice.application.port.out.CreateCouponPort;
import com.yeji.couponservice.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponRedisLockService implements CreateCouponWithRedisUseCase {

    private final CouponRedisLockPort couponRedisLockPort;
    private final CreateCouponPort couponPort;

    @Override
    public CouponResponse decrease(String couponId) throws InterruptedException {

        while (!couponRedisLockPort.lock(couponId)) {
            Thread.sleep(2000);
        }

        try {

            Coupon coupon = couponPort.issuanceCoupon(couponId);
            System.out.println("coupon : " + coupon.getNumberOfCoupons());
            return CouponResponse.from(coupon);

        } finally {
            couponRedisLockPort.unLock(couponId);
        }

    }
}
