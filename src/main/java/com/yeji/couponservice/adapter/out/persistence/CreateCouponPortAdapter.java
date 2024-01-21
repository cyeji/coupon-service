package com.yeji.couponservice.adapter.out.persistence;

import com.yeji.couponservice.application.port.out.CreateCouponPort;
import com.yeji.couponservice.domain.Coupon;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCouponPortAdapter implements CreateCouponPort {

    private final CouponRepository couponRepository;

    @Override
    public Optional<Coupon> findByCouponName(String name) {
        Optional<CouponEntity> optionalCouponEntity = couponRepository.findByCouponName(name);

        if (optionalCouponEntity.isEmpty()) {
            return Optional.empty();
        }

        CouponEntity couponEntity = optionalCouponEntity.get();

        return couponEntity.convertToCoupon();
    }

    @Override
    public Optional<Coupon> save(Coupon coupon) {
        CouponEntity couponEntity = CouponEntity.from(coupon);
        couponRepository.save(couponEntity);

        return couponEntity.convertToCoupon();
    }
}
