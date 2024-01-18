package com.yeji.couponservice.service;

import com.yeji.couponservice.controller.form.CouponRequestForm;
import com.yeji.couponservice.controller.response.CouponResponse;
import java.util.List;

public interface CouponService {

    CouponResponse createCoupon(CouponRequestForm couponRequest);

    List<CouponResponse> getCoupons();

    CouponResponse issuanceCoupon(String couponId);
}
