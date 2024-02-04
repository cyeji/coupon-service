package com.yeji.couponservice.adapter.out.persistence;

import jakarta.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface CouponRepository extends JpaRepository<CouponEntity, UUID> {

    Optional<CouponEntity> findByCouponName(String couponName);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from CouponEntity c where c.id = :id")
    Optional<CouponEntity> findByIdWithPessimisticLock(UUID id);

    @Lock(value = LockModeType.OPTIMISTIC)
    @Query("select c from CouponEntity c where c.couponId = :id")
    Optional<CouponEntity> findByIdWithOptimisticLock(UUID id);

}
