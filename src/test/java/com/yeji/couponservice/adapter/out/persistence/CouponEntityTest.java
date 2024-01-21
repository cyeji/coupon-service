package com.yeji.couponservice.adapter.out.persistence;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CouponEntityTest {

    private LocalDate convertToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }


    @Test
    @DisplayName("날짜 변환 테스트")
    void test_case_1() throws Exception {
        // given
        String date = "20240111";
        // when
        LocalDate localDate = convertToDate(date);
        // then
        System.out.println(localDate);
    }
}