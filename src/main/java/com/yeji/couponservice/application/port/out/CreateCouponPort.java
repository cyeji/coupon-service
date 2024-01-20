package com.yeji.couponservice.application.port.out;

import com.yeji.couponservice.domain.Coupon;

public interface CreateCouponPort {

    Coupon findByCouponName(String name);

}
