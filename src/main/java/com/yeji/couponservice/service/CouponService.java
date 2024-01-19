package com.yeji.couponservice.service;

import com.yeji.couponservice.adapter.in.web.CouponRequestForm;
import com.yeji.couponservice.port.in.CouponResponse;
import java.util.List;

public interface CouponService {

    CouponResponse createCoupon(CouponRequestForm couponRequest);

    List<CouponResponse> getCoupons();

    CouponResponse issuanceCoupon(String couponId);
}
