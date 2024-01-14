package com.yeji.couponservice.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CallableandFutureTest {


    @Test
    @DisplayName("Callable & Future")
    void test_case_1() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Future<String> submit = executor.submit(hello);
        System.out.println(submit.isDone());
        System.out.println("Started!");

        submit.cancel(false);

        System.out.println(submit.isDone());
        System.out.println("End!!");
        executor.shutdown();
    }
}
