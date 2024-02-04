package com.yeji.couponservice.adapter.out.persistence;

import com.yeji.couponservice.application.port.out.CouponRedisLockPort;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponRedisLockPortAdapter implements CouponRedisLockPort {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public Boolean lock(String key) {
        return redisTemplate.opsForValue()
                            .setIfAbsent(key, "lock", Duration.ofMillis(3_000));
    }

    @Override
    public Boolean unLock(String key) {
        return redisTemplate.delete(key);
    }
}
