package com.yeji.couponservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.yeji.couponservice.adapter.in.web.CouponRequestForm;
import com.yeji.couponservice.repository.CouponRepository;
import com.yeji.couponservice.repository.entity.Coupon;
import com.yeji.couponservice.repository.entity.enums.CouponType;
import com.yeji.couponservice.repository.entity.enums.DiscountType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IssuanceCouponTestByLock {

    @Autowired
    private CouponRepository couponRepository;

    @Qualifier("couponLockService")
    @Autowired
    private CouponService couponService;

    @BeforeEach
    public void setUp() throws Exception {
        CouponRequestForm requestForm = new CouponRequestForm();
        requestForm.setCost(10);
        requestForm.setCouponType(CouponType.ALL.name());
        requestForm.setCouponCode("네고왕");
        requestForm.setCouponName("선착순 100명 10프로 할인");
        requestForm.setNumberOfCoupons(10000);
        requestForm.setDownloadStartDate("20240111");
        requestForm.setDownloadEndDate("20240120");
        requestForm.setAvailableStartDate("20240111");
        requestForm.setAvailableEndDate("20240120");
        requestForm.setDiscountType(DiscountType.PERCENT.name());

        Coupon coupon = Coupon.from(requestForm);
        couponRepository.save(coupon);
    }

    @Test
    @DisplayName("쿠폰 발급 동시성 테스트")
    void test_case_1() throws Exception {
        // given
        List<Coupon> couponList = couponRepository.findAll();
        if (couponList.isEmpty()) {
            throw new IllegalStateException();
        }
        int threadCount = 100;
        UUID couponId = couponList.get(0)
                                  .getCouponId();
        // when
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        // then
        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    couponService.issuanceCoupon(couponId.toString());
                } finally {
                    countDownLatch.countDown();
                }

            });
        }
        countDownLatch.await();

        Optional<Coupon> optionalCompareCoupon = couponRepository.findById(couponId);
        if (optionalCompareCoupon.isEmpty()) {
            throw new IllegalStateException();
        }

        Coupon compareCoupon = optionalCompareCoupon.get();
        assertEquals(9900, compareCoupon.getNumberOfCoupons());
    }
}
