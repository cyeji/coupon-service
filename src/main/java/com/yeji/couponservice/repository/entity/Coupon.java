package com.yeji.couponservice.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 쿠폰 엔티티
 *
 * @author yeji.jo
 * @since 2024.01.10
 */
@Entity
public class Coupon extends UpdatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID couponId;

    /** 쿠폰 명 */
    private String couponName;

    /** 쿠폰 코드 */
    private String couponCode;

    /** 쿠폰 다운로드 시작일 */
    private LocalDateTime downloadStartDate;

    /** 쿠폰 다운로드 종료일 */
    private LocalDateTime downloadEndDate;

    /** 쿠폰 사용가능 시작일 */
    private LocalDateTime availableStartDate;

    /** 쿠폰 사용가능 종료일 */
    private LocalDateTime availableEndDate;

    /** 할인 형태 */
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    /** 비용 */
    private int cost;

    /** 쿠폰 발급 매수 */
    private int numberOfCoupons;

    /** 쿠폰 종류 */
    private CouponType couponType;
}
