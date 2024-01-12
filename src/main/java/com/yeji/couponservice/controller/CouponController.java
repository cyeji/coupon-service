package com.yeji.couponservice.controller;

import com.yeji.couponservice.common.ApiUtil;
import com.yeji.couponservice.controller.form.CouponRequestForm;
import com.yeji.couponservice.controller.response.CouponResponse;
import com.yeji.couponservice.service.CouponService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 쿠폰 전체 조회
     *
     * @return
     */
    @GetMapping
    public ApiUtil.ApiResponse<List<CouponResponse>> getCoupons() {
        return ApiUtil.success(couponService.getCoupons(), "처리되었습니다");
    }

    @PostMapping("/{couponId}")
    public ApiUtil.ApiResponse<CouponResponse> issuanceCoupon(@RequestParam String couponId) {
        return ApiUtil.success(couponService.issuanceCoupon(couponId), "처리되었습니다.");
    }

}
