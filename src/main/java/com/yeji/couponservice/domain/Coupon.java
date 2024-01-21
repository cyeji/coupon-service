package com.yeji.couponservice.domain;

import com.yeji.couponservice.domain.enums.CouponType;
import com.yeji.couponservice.domain.enums.DiscountType;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class Coupon {

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

    private DiscountType discountType;

    private int cost;

    private int numberOfCoupons;

    private CouponType couponType;
}
