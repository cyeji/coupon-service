package com.yeji.couponservice.repository;

import com.yeji.couponservice.repository.entity.Coupon;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;


public interface CouponRepository extends JpaRepository<Coupon, UUID> {

    Optional<Coupon> findByCouponName(String couponName);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Coupon c where c.id = :id")
    Optional<Coupon> findByIdWithPessimisticLock(UUID id);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select c from Coupon c where c.id = :id")
    Optional<Coupon> findByIdWithOptimisticLock(UUID id);

}
