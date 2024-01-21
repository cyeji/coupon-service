package com.yeji.couponservice.application.port.in;

import com.yeji.couponservice.domain.Coupon;
import com.yeji.couponservice.domain.enums.CouponType;
import com.yeji.couponservice.domain.enums.DiscountType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponCommand {

    /** 쿠폰 명 */
    @NotNull
    private String couponName;

    /** 쿠폰 코드 */
    @NotNull
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
    @NotNull
    private DiscountType discountType;

    /** 비용 */
    @NotNull
    private int cost;

    /** 쿠폰 발급 매수 */
    @NotNull
    private int numberOfCoupons;

    /** 쿠폰 종류 */
    @NotNull
    private CouponType couponType;


    public Coupon convertToCoupon() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Coupon coupon = Coupon.builder()
                              .couponName(this.couponName)
                              .couponCode(this.couponCode)
                              .cost(this.cost)
                              .numberOfCoupons(this.numberOfCoupons)
                              .discountType(this.discountType)
                              .couponType(this.couponType)
                              .downloadStartDate(LocalDate.parse(this.downloadStartDate, dateTimeFormatter))
                              .downloadEndDate(LocalDate.parse(this.downloadEndDate, dateTimeFormatter))
                              .availableStartDate(LocalDate.parse(this.availableStartDate, dateTimeFormatter))
                              .availableEndDate(LocalDate.parse(this.availableEndDate, dateTimeFormatter))
                              .build();

        return coupon;
    }
}
