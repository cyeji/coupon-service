package com.yeji.couponservice.adapter.out.persistence;

import com.yeji.couponservice.adapter.out.persistence.converter.CouponTypeConverter;
import com.yeji.couponservice.adapter.out.persistence.converter.DiscountTypeConverter;
import com.yeji.couponservice.domain.Coupon;
import com.yeji.couponservice.domain.enums.CouponType;
import com.yeji.couponservice.domain.enums.DiscountType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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


    @Version
    private Long version;

    public CouponEntity(Coupon coupon) {
        this.couponName = coupon.getCouponName();
        this.couponCode = coupon.getCouponCode();
        this.couponType = coupon.getCouponType();
        this.discountType = coupon.getDiscountType();
        this.downloadStartDate = coupon.getDownloadStartDate();
        this.downloadEndDate = coupon.getDownloadEndDate();
        this.availableStartDate = coupon.getAvailableEndDate();
        this.availableEndDate = coupon.getAvailableEndDate();
        this.cost = coupon.getCost();
        this.numberOfCoupons = coupon.getNumberOfCoupons();
    }

    public static CouponEntity from(Coupon coupon) {
        return new CouponEntity(coupon);
    }


    public Coupon convertToCoupon() {
        Coupon coupon = Coupon.builder()
                              .couponId(this.couponId)
                              .couponName(this.couponName)
                              .couponCode(this.couponCode)
                              .cost(this.cost)
                              .numberOfCoupons(this.numberOfCoupons)
                              .discountType(this.discountType)
                              .couponType(this.couponType)
                              .downloadStartDate(this.downloadStartDate)
                              .downloadEndDate(this.downloadEndDate)
                              .availableStartDate(this.availableStartDate)
                              .availableEndDate(this.availableEndDate)
                              .build();

        return coupon;
    }

    public void issuanceCoupon() {
        if (this.numberOfCoupons < 0) {
            throw new IllegalArgumentException("쿠폰이 모두 소진되었습니다.");
        }
        this.numberOfCoupons--;
    }

}
