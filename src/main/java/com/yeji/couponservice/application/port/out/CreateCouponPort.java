package com.yeji.couponservice.application.port.out;

import com.yeji.couponservice.domain.Coupon;
import java.util.Optional;


public interface CreateCouponPort {

    Optional<Coupon> findByCouponName(String name);

    Optional<Coupon> save(Coupon coupon);
}
