package com.yeji.couponservice.controller.form;


import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 쿠폰 생성 요청 폼
 *
 * @author yeji.jo
 * @since 2024.01.10
 */
public class CouponRequestForm {

    /** 쿠폰 명 */
    @NotNull
    private String couponName;

    /** 쿠폰 코드 */
    @NotNull
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
    @NotNull
    private String discountType;

    /** 비용 */
    @NotNull
    private int cost;

    /** 쿠폰 발급 매수 */
    @NotNull
    private int numberOfCoupons;

    /** 쿠폰 종류 */
    @NotNull
    private String couponType;

}
