package com.sitech.thread.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //当barrier的数量达到设置值后，首先执行runnable对象
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,() -> {
            System.out.println("all start ");
        });
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
            //System.out.println(cyclicBarrier.getNumberWaiting());
            try {
                //通知barrier 已经完成
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            //可以分别执行，也可以执行在声明CyclicBarrier时指定单独一个任务
            System.out.println("start self");
        };
        for (int i = 0; i <5 ; i++) {
            Thread t1 = new Thread(task);
            t1.start();
        }
    }
}