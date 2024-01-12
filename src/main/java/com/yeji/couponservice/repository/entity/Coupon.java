package com.yeji.couponservice.repository.entity;

import com.yeji.couponservice.common.exceptions.TimeoutException;
import com.yeji.couponservice.controller.form.CouponRequestForm;
import com.yeji.couponservice.repository.entity.converter.CouponTypeConverter;
import com.yeji.couponservice.repository.entity.converter.DiscountTypeConverter;
import com.yeji.couponservice.repository.entity.enums.CouponType;
import com.yeji.couponservice.repository.entity.enums.DiscountType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 쿠폰 엔티티
 *
 * @author yeji.jo
 * @since 2024.01.10
 */
@Getter
@Entity
@NoArgsConstructor
public class Coupon extends UpdatedEntity {

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
    @Convert(converter = DiscountTypeConverter.class)
    private DiscountType discountType;

    /** 비용 */
    private int cost;

    /** 쿠폰 발급 매수 */
    private int numberOfCoupons;

    /** 쿠폰 종류 */
    @Convert(converter = CouponTypeConverter.class)
    private CouponType couponType;

    public Coupon(CouponRequestForm couponRequest) {
        this.couponName = couponRequest.getCouponName();
        this.couponCode = couponRequest.getCouponCode();
        this.cost = couponRequest.getCost();
        this.numberOfCoupons = couponRequest.getNumberOfCoupons();
        this.downloadStartDate = convertToDate(couponRequest.getDownloadStartDate());
        this.downloadEndDate = convertToDate(couponRequest.getDownloadEndDate());
        this.availableStartDate = convertToDate(couponRequest.getAvailableStartDate());
        this.availableEndDate = convertToDate(couponRequest.getAvailableEndDate());

        LocalDate now = LocalDate.now();
        if (now.isBefore(this.downloadStartDate) || now.isAfter(this.downloadEndDate)) {
            throw new TimeoutException("쿠폰 다운로드 기간이 아닙니다.");
        }
    }

    private LocalDate convertToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static Coupon from(CouponRequestForm couponRequest) {
        return new Coupon(couponRequest);
    }

    public void minusCouponCount() {
        if (this.numberOfCoupons < 0) {
            throw new IllegalArgumentException("쿠폰 다운이 만료되었습니다.");
        }
        this.numberOfCoupons--;
    }
}
