package com.yeji.couponservice.application.service;

import com.yeji.couponservice.application.port.in.CouponResponse;
import com.yeji.couponservice.application.port.in.CreateCouponWithRedisUseCase;
import com.yeji.couponservice.application.port.out.CreateCouponPort;
import com.yeji.couponservice.application.port.out.RedisLockProperties;
import com.yeji.couponservice.domain.Coupon;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponRedissonLockService implements CreateCouponWithRedisUseCase {

    private final RedissonClient redissonClient;

    private final RedisLockProperties redisLockProperties;

    private final CreateCouponPort couponPort;

    @Override
    public CouponResponse decrease(String couponId) throws InterruptedException {
        RLock lock = redissonClient.getLock(couponId);

        try {
            boolean isLocked = lock.tryLock(redisLockProperties.getWaitTime(), redisLockProperties.getLeaseTime(), TimeUnit.SECONDS);

            if (!isLocked) {
                System.out.println("락 획득 실패");
                throw new IllegalThreadStateException("락 획득에 실패하였습니다.");
            }

            Coupon coupon = couponPort.issuanceCoupon(couponId);
            return CouponResponse.from(coupon);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }
}
