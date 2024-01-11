package com.yeji.couponservice.service;

import com.yeji.couponservice.common.exceptions.AlreadyExistsCouponException;
import com.yeji.couponservice.controller.form.CouponRequestForm;
import com.yeji.couponservice.controller.response.CouponResponse;
import com.yeji.couponservice.repository.CouponRepository;
import com.yeji.couponservice.repository.entity.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponService {

    private final CouponRepository couponRepository;

    /**
     * 쿠폰 생성
     *
     * @param couponRequest 쿠폰 요청 객체
     * @return 응답값
     */
    public CouponResponse createCoupon(CouponRequestForm couponRequest) {

        couponRepository.findByCouponName(couponRequest.getCouponName())
                        .ifPresent(coupon -> {
                            throw new AlreadyExistsCouponException("coupon already exists");
                        });

        Coupon coupon = Coupon.from(couponRequest);
        couponRepository.save(coupon);
        return CouponResponse.from(coupon);
    }
}
