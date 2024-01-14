package com.yeji.couponservice.concurrent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ThreadTest {


    @Test
    @DisplayName("Thread 생성 방법 테스트 1 - Thread 상속")
    void test_case_1() throws Exception {
        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println("thread test 1");
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Thread : " + Thread.currentThread()
                                                   .getName());
        }
    }
    
    @Test
    @DisplayName("Thread 생성 방법 테스트 2 - runnable 클래스 구현 또는 람다")
    void test_case_2() throws Exception {
        Thread thread = new Thread(() -> System.out.println("Thread : " + Thread.currentThread()
                                                                                .getName()));
        thread.start();

        System.out.println("Hello : " + Thread.currentThread()
                                              .getName());
    }
}
