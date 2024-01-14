package com.yeji.couponservice.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExecutorTest {


    @Test
    @DisplayName("executorService 테스트")
    void test_case_1() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {
            System.out.println("Thread : " + Thread.currentThread()
                                                   .getName());
            ;
        });
        executor.shutdown();
    }


    @Test
    @DisplayName("threadPool 테스트")
    void test_case_2() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(getRunnable("Hello "));
        executorService.submit(getRunnable("Yeji "));
        executorService.submit(getRunnable("The "));
        executorService.submit(getRunnable("Java "));
        executorService.submit(getRunnable("Thread "));

        executorService.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread()
                                                        .getName());
    }
}
