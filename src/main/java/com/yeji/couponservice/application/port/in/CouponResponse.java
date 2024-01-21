package com.yeji.couponservice.application.port.in;


import com.yeji.couponservice.domain.Coupon;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponResponse {

    /** 쿠폰 명 */
    @NotNull
    private String couponName;

    /** 쿠폰 코드 */
    @NotNull
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

    public CouponResponse(Coupon coupon) {
        this.couponName = coupon.getCouponName();
        this.couponCode = coupon.getCouponCode();
        this.downloadStartDate = coupon.getDownloadStartDate();
        this.downloadEndDate = coupon.getDownloadEndDate();
        this.availableStartDate = coupon.getAvailableStartDate();
        this.availableEndDate = coupon.getAvailableEndDate();
        this.discountType = String.valueOf(coupon.getDiscountType());
        this.cost = coupon.getCost();
        this.numberOfCoupons = coupon.getNumberOfCoupons();
        this.couponType = String.valueOf(coupon.getCouponType());
    }


    public static CouponResponse from(Coupon coupon) {
        return new CouponResponse(coupon);
    }
}
