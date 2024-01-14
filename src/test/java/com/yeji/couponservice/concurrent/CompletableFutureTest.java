package com.yeji.couponservice.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CompletableFutureTest {


    @Test
    @DisplayName("completableFutureTest")
    void test_case_1() throws Exception {
        // ForkJoinPool : Dequeue를 사용
        // 작업 단위를 쪼개서 subTask
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("keesun");

        CompletableFuture.runAsync(() -> System.out.println("Hello " + Thread.currentThread()
                                                                             .getName()), executorService);

        System.out.println(future.get());
    }


    @Test
    @DisplayName("completableFuture 콜백 작업 테스트")
    void test_case_2() throws Exception {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread()
                                                .getName());
            return "hello";
        });

        CompletableFuture<String> future = hello.thenCompose(CompletableFutureTest::getWorld);
        
        System.out.println(future);
    }

    @NotNull
    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread()
                                                .getName());
            return message + "world";
        });
    }
}
