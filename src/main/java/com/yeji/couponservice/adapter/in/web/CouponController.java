package com.yeji.couponservice.adapter.in.web;

import com.yeji.couponservice.common.ApiUtil;
import com.yeji.couponservice.common.ApiUtil.ApiResponse;
import com.yeji.couponservice.port.in.CouponCommand;
import com.yeji.couponservice.port.in.CouponResponse;
import com.yeji.couponservice.port.in.CouponUseCase;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponController {


    private final CouponUseCase couponUseCase;


    /**
     * 쿠폰 생성
     *
     * @param couponRequest 쿠폰 생성 객체
     * @return
     */
    @PostMapping
    public ApiResponse<CouponResponse> createCoupon(@Valid @RequestBody CouponRequestForm couponRequest) {
        CouponCommand couponCommand = couponRequest.convert();
        return ApiUtil.success(couponUseCase.createCoupon(couponCommand), "처리되었습니다.");
    }

    /**
     * 쿠폰 전체 조회
     *
     * @return
     */
    @GetMapping
    public ApiResponse<List<CouponResponse>> getCoupons() {
        return ApiUtil.success(couponService.getCoupons(), "처리되었습니다");
    }

    @PostMapping("/{couponId}")
    public ApiResponse<CouponResponse> issuanceCoupon(@RequestParam String couponId) {
        return ApiUtil.success(couponService.issuanceCoupon(couponId), "처리되었습니다.");
    }
}
