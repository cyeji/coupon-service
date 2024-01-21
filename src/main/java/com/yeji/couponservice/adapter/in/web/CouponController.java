package com.yeji.couponservice.adapter.in.web;

import com.yeji.couponservice.application.port.in.CouponCommand;
import com.yeji.couponservice.application.port.in.CouponResponse;
import com.yeji.couponservice.application.port.in.CreateCouponUseCase;
import com.yeji.couponservice.common.ApiUtil;
import com.yeji.couponservice.common.ApiUtil.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
public class CouponController {


    private final CreateCouponUseCase createCouponUseCase;


    /**
     * 쿠폰 생성
     *
     * @param couponRequest 쿠폰 생성 객체
     * @return
     */
    @PostMapping
    public ApiResponse<CouponResponse> createCoupon(@RequestBody CouponRequestForm couponRequest) {
        CouponCommand couponCommand = couponRequest.convert();
        return ApiUtil.success(createCouponUseCase.createCoupon(couponCommand), "처리되었습니다.");
    }

    /**
     * 쿠폰 전체 조회
     *
     * @return
     */
    @GetMapping
    public ApiResponse<List<CouponResponse>> getCoupons() {
        return ApiUtil.success(createCouponUseCase.getCoupons(), "처리되었습니다");
    }

    /**
     * 쿠폰 감소
     *
     * @param couponId 쿠폰 아이디
     * @return
     */
    @PostMapping("/{couponId}")
    public ApiResponse<CouponResponse> issuanceCoupon(@RequestParam String couponId) {
        return ApiUtil.success(createCouponUseCase.issuanceCoupon(couponId), "처리되었습니다.");
    }
}
