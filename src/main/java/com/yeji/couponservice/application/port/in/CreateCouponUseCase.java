package com.yeji.couponservice.application.port.in;

import java.util.List;

public interface CreateCouponUseCase {

    CouponResponse createCoupon(CouponCommand couponCommand);

    List<CouponResponse> getCoupons();

    CouponResponse issuanceCoupon(String id);

    CouponResponse issuanceCouponWithConcurrent(String id);
}
