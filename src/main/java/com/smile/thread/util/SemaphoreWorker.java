package com.smile.thread.util;

import java.util.concurrent.Semaphore;

public class SemaphoreWorker {
    public static void main(String[] args) {
        //设置同时允许访问的线程个数
        Semaphore semaphore = new Semaphore(5);

        Runnable task = ()->{
            try {
                semaphore.acquire();
                System.out.println("线程"+Thread.currentThread().getName()+"获取资源");
                Thread.sleep(2000);
                System.out.println("线程"+Thread.currentThread().getName()+"释放资源");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(task);
            t.start();
        }
    }
}
