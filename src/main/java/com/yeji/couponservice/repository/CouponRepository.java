package com.yeji.couponservice.repository;

import com.yeji.couponservice.repository.entity.Coupon;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, UUID> {

    Optional<Coupon> findByCouponName(String couponName);

}
