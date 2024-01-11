package com.yeji.couponservice.controller;

import com.yeji.couponservice.common.ApiUtil;
import com.yeji.couponservice.controller.form.CouponRequestForm;
import com.yeji.couponservice.controller.response.CouponResponse;
import com.yeji.couponservice.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 쿠폰 처리 컨트롤러
 *
 * @author yeji.jo
 * @since 2024.01.10
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    private final CouponService couponService;

    /**
     * 쿠폰 생성
     *
     * @param couponRequest 쿠폰 생성 객체
     * @return
     */
    @PostMapping
    public ApiUtil.ApiResponse<CouponResponse> createCoupon(@Valid @RequestBody CouponRequestForm couponRequest) {
        return ApiUtil.success(couponService.createCoupon(couponRequest), "처리되었습니다");
    }

}
