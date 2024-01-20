package com.yeji.couponservice.application.service;

import com.yeji.couponservice.application.port.in.CouponCommand;
import com.yeji.couponservice.application.port.in.CouponResponse;
import com.yeji.couponservice.application.port.in.CreateCouponUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CouponLockService implements CreateCouponUseCase {


    @Override
    public CouponResponse createCoupon(CouponCommand couponCommand) {

        return null;
    }

    /**
     * 쿠폰 전체 조회
     *
     * @return 응답값
     */
    @Override
    public List<CouponResponse> getCoupons() {

        return null;
    }

    /**
     * 쿠폰 발급
     *
     * @param couponId 쿠폰 아이디
     */
    @Transactional
    @Override
    public CouponResponse issuanceCoupon(String couponId) {

        return null;
    }
}
