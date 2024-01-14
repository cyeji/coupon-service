package com.yeji.couponservice.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CountDownLatchTest {


    @Test
    @DisplayName("countDownLatch 테스트")
    void test_case_1() throws Exception {
        // given
        CountDownLatch countDownLatch = new CountDownLatch(100);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // when
        System.out.println("start CountDown");
        for (int i = 1; i <= 100; i++) {
            final int index = i;
            executor.execute(() -> {
                System.out.println(index);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        // then
        System.out.println("success CountDown");
    }
}
