package com.yeji.couponservice.domain.enums;

import lombok.Getter;

@Getter
public enum CouponType {

    ALL, // 전체
    MEMBERSHIP, // 멤버십 회원
    FIRST, // 첫 구매 회원
    VIP; // vip
}
