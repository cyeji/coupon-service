package com.yeji.couponservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yeji.couponservice.adapter.in.web.CouponRequestForm;
import com.yeji.couponservice.adapter.out.persistence.enums.CouponType;
import com.yeji.couponservice.adapter.out.persistence.enums.DiscountType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("쿠폰 생성 요청 - 200")
    void test_case_1() throws Exception {
        // given
        CouponRequestForm couponRequestForm = new CouponRequestForm();
        couponRequestForm.setCouponName("선착순 이벤트 쿠폰");
        couponRequestForm.setCouponCode("네고왕");
        couponRequestForm.setCost(10);
        couponRequestForm.setNumberOfCoupons(100);
        couponRequestForm.setDiscountType(DiscountType.PERCENT.name());
        couponRequestForm.setCouponType(CouponType.ALL.name());
        couponRequestForm.setDownloadStartDate("20240111");
        couponRequestForm.setDownloadEndDate("20240120");
        couponRequestForm.setAvailableStartDate("20240111");
        couponRequestForm.setAvailableEndDate("20240120");
        // when

        // then
        mockMvc.perform(post("/api/coupon").contentType(MediaType.APPLICATION_JSON)
                                           .content(mapper.writeValueAsString(couponRequestForm)))
               .andExpect(status().isOk());
    }
}