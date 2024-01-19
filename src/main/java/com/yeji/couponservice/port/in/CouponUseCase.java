package com.yeji.couponservice.port.in;

import java.util.List;

public interface CouponUseCase {

    CouponResponse createCoupon(CouponCommand couponCommand);

    List<CouponResponse> getCoupons();

    CouponResponse issuanceCoupon(String id);

}
