package com.yeji.couponservice.adapter.out.persistence;

import com.yeji.couponservice.adapter.out.persistence.converter.CouponTypeConverter;
import com.yeji.couponservice.adapter.out.persistence.converter.DiscountTypeConverter;
import com.yeji.couponservice.adapter.out.persistence.enums.CouponType;
import com.yeji.couponservice.adapter.out.persistence.enums.DiscountType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Table(name = "coupon")
@Entity
public class CouponEntity extends UpdatedEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID couponId;

    /** 쿠폰 명 */
    private String couponName;

    /** 쿠폰 코드 */
    private String couponCode;

    /** 쿠폰 다운로드 시작일 */
    private LocalDate downloadStartDate;

    /** 쿠폰 다운로드 종료일 */
    private LocalDate downloadEndDate;

    /** 쿠폰 사용가능 시작일 */
    private LocalDate availableStartDate;

    /** 쿠폰 사용가능 종료일 */
    private LocalDate availableEndDate;

    /** 할인 형태 */
    @Enumerated(EnumType.STRING)
    @Convert(converter = DiscountTypeConverter.class)
    private DiscountType discountType;

    /** 비용 */
    private int cost;

    /** 쿠폰 발급 매수 */
    private int numberOfCoupons;

    /** 쿠폰 종류 */
    @Enumerated(EnumType.STRING)
    @Convert(converter = CouponTypeConverter.class)
    private CouponType couponType;

}
