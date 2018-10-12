package com.sitech.thread;

import java.util.concurrent.TimeUnit;


public class Volatile implements Runnable {
    //private static volatile boolean flag = true;
    private static boolean flag = true;
    private static int count = 1;

    @Override
    public void run() {
        while (flag) {
            System.out.println(Thread.currentThread().getName() + count++ + "正在运行。。。");
        }
        System.out.println(Thread.currentThread().getName() + "执行完毕");
    }

    public static void main(String[] args) throws InterruptedException {
        Volatile aVolatile = new Volatile();
        new Thread(aVolatile, "thread A").start();
        System.out.println("main 线程正在运行");
        TimeUnit.MILLISECONDS.sleep(100);
        aVolatile.stopThread();
    }

    private void stopThread() {
        flag = false;
    }
}
