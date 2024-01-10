package com.yeji.couponservice.service;

import com.yeji.couponservice.common.ApiUtil.ApiResponse;
import com.yeji.couponservice.controller.form.CouponRequestForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponService {

    public ApiResponse<CouponRequestForm> createCoupon(CouponRequestForm couponRequest) {
        return null;
    }
}
