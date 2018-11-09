package com.smile.thread.util;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
            latch.countDown();
        };
        System.out.println("start main");
        for (int i = 0; i < 5; i++) {
            Thread t1 = new Thread(task);
            t1.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("go on main");

    }
}
