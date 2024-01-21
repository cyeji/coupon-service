package com.yeji.couponservice.adapter.out.persistence;

import com.yeji.couponservice.application.port.out.CreateCouponPort;
import com.yeji.couponservice.domain.Coupon;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCouponPortAdapter implements CreateCouponPort {

    private final CouponRepository couponRepository;

    @Override
    public Optional<Coupon> findByCouponName(String name) {
        Optional<CouponEntity> optionalCouponEntity = couponRepository.findByCouponName(name);

        if (optionalCouponEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(optionalCouponEntity.get()
                                               .convertToCoupon());
    }

    @Override
    public Optional<Coupon> save(Coupon coupon) {
        CouponEntity couponEntity = CouponEntity.from(coupon);
        couponRepository.save(couponEntity);

        return Optional.of(couponEntity.convertToCoupon());
    }

    @Override
    public List<Coupon> getCoupons() {
        List<CouponEntity> couponEntityList = couponRepository.findAll();

        return couponEntityList.stream()
                               .map(CouponEntity::convertToCoupon)
                               .toList();
    }

    @Override
    public Optional<Coupon> findById(String couponId) {
        Optional<CouponEntity> optionalCouponEntity = couponRepository.findById(UUID.fromString(couponId));

        if (optionalCouponEntity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(optionalCouponEntity.get()
                                               .convertToCoupon());
    }

    @Override
    public Coupon issuanceCoupon(String couponId) {
        CouponEntity couponEntity = couponRepository.findById(UUID.fromString(couponId))
                                                    .orElseThrow(() -> new IllegalArgumentException("쿠폰 값을 찾을 수 없습니다."));

        couponEntity.issuanceCoupon();

        return couponEntity.convertToCoupon();
    }

    @Override
    public Coupon issuanceCouponWithOptimisticLock(String couponId) {
        CouponEntity couponEntity = couponRepository.findByIdWithOptimisticLock(UUID.fromString(couponId))
                                                    .orElseThrow(() -> new IllegalArgumentException("쿠폰 값을 찾을 수 없습니다."));

        couponEntity.issuanceCoupon();
        return couponEntity.convertToCoupon();
    }
}
