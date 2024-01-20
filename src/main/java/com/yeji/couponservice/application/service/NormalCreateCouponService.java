package com.yeji.couponservice.application.service;

import com.yeji.couponservice.application.port.in.CouponCommand;
import com.yeji.couponservice.application.port.in.CouponResponse;
import com.yeji.couponservice.application.port.in.CreateCouponUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NormalCreateCouponService implements CreateCouponUseCase {


    /**
     * 쿠폰 생성
     *
     * @param couponCommand 쿠폰 요청 객체
     * @return 응답값
     */
    @Override
    public CouponResponse createCoupon(CouponCommand couponCommand) {

        return null;
    }

    @Override
    public List<CouponResponse> getCoupons() {
        return null;
    }

    @Override
    public CouponResponse issuanceCoupon(String id) {
        return null;
    }


}
