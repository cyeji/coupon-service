package com.yeji.couponservice.controller;

import com.yeji.couponservice.common.ApiUtil;
import com.yeji.couponservice.controller.form.CouponRequestForm;
import com.yeji.couponservice.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 쿠폰 처리 컨트롤러
 *
 * @author yeji.jo
 * @since 2024.01.10
 */
@RequiredArgsConstructor
@RestController("/api/coupon")
public class CouponController {

    private final CouponService couponService;

    /**
     * 쿠폰 생성
     *
     * @param couponRequest 쿠폰 생성 객체
     * @return
     */
    @PostMapping
    public ApiUtil.ApiResponse<CouponRequestForm> createCoupon(@Valid CouponRequestForm couponRequest) {
        return couponService.createCoupon(couponRequest);
    }

}
