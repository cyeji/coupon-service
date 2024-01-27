package com.yeji.couponservice.application.service;

import com.yeji.couponservice.application.port.in.CouponCommand;
import com.yeji.couponservice.application.port.in.CouponResponse;
import com.yeji.couponservice.application.port.in.CreateCouponUseCase;
import com.yeji.couponservice.application.port.out.CreateCouponPort;
import com.yeji.couponservice.domain.Coupon;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CouponLockService implements CreateCouponUseCase {

    private final CreateCouponPort couponPort;

    @Override
    public CouponResponse createCoupon(CouponCommand couponCommand) {
        couponPort.findByCouponName(couponCommand.getCouponName())
                  .ifPresent(c -> {
                      throw new IllegalStateException("쿠폰 이름이 이미 있습니다.");
                  });

        Coupon coupon = couponCommand.convertToCoupon();

        return CouponResponse.from(couponPort.save(coupon)
                                             .get());
    }

    /**
     * 쿠폰 전체 조회
     *
     * @return 응답값
     */
    @Override
    public List<CouponResponse> getCoupons() {
        List<Coupon> coupons = couponPort.getCoupons();

        return coupons.stream()
                      .map(CouponResponse::from)
                      .toList();
    }

    /**
     * 쿠폰 발급
     *
     * @param couponId 쿠폰 아이디
     */
    @Transactional
    @Override
    public CouponResponse issuanceCoupon(String couponId) {
        Coupon issuanceCoupon = couponPort.issuanceCoupon(couponId);

        return CouponResponse.from(issuanceCoupon);
    }

    /**
     * 쿠폰 발급 동시성 처리
     */
    @Transactional
    @Override
    public CouponResponse issuanceCouponWithConcurrent(String id) {
        Coupon coupon = couponPort.issuanceCouponWithDatabaseLock(id);
        return CouponResponse.from(coupon);
    }


}
