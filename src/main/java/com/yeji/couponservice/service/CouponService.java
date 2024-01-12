package com.yeji.couponservice.service;

import com.yeji.couponservice.common.exceptions.AlreadyExistsCouponException;
import com.yeji.couponservice.controller.form.CouponRequestForm;
import com.yeji.couponservice.controller.response.CouponResponse;
import com.yeji.couponservice.repository.CouponRepository;
import com.yeji.couponservice.repository.entity.Coupon;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
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

    /**
     * 쿠폰 전체 조회
     *
     * @return 응답값
     */
    public List<CouponResponse> getCoupons() {
        List<Coupon> couponList = couponRepository.findAll();

        return couponList.stream()
                         .map(coupon -> CouponResponse.from(coupon))
                         .toList();
    }

    /**
     * 쿠폰 발급
     *
     * @param couponId 쿠폰 아이디
     */
    @Transactional
    public CouponResponse issuanceCoupon(String couponId) {
        Coupon coupon = couponRepository.findById(UUID.fromString(couponId))
                                        .orElseThrow(() -> new NoSuchFieldError("쿠폰을 찾을 수 없습니다."));

        coupon.minusCouponCount();

        return CouponResponse.from(coupon);
    }
}
