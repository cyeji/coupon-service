package com.yeji.couponservice.adapter.in.web;


import static org.springframework.beans.BeanUtils.copyProperties;

import com.yeji.couponservice.application.port.in.CouponCommand;
import lombok.Getter;
import lombok.Setter;

/**
 * 쿠폰 생성 요청 폼
 *
 * @author yeji.jo
 * @since 2024.01.10
 */
@Getter
@Setter
public class CouponRequestForm {

    /** 쿠폰 명 */
    private String couponName;

    /** 쿠폰 코드 */
    private String couponCode;

    /** 쿠폰 다운로드 시작일 */
    private String downloadStartDate;

    /** 쿠폰 다운로드 종료일 */
    private String downloadEndDate;

    /** 쿠폰 사용가능 시작일 */
    private String availableStartDate;

    /** 쿠폰 사용가능 종료일 */
    private String availableEndDate;

    /** 할인 형태 */
    private String discountType;

    /** 비용 */
    private int cost;

    /** 쿠폰 발급 매수 */
    private int numberOfCoupons;

    /** 쿠폰 종류 */
    private String couponType;

    public CouponCommand convert() {
        CouponCommand couponCommand = new CouponCommand();
        copyProperties(this, couponCommand);
        return couponCommand;
    }
}
