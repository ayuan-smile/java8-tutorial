package com.smile.thread.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ListAdd3 {
    private volatile List<String> list = new ArrayList<>();
    //声明对象锁
    //private static Object lock = new Object();

    private void add() {
        list.add("test1");
    }

    private int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        ListAdd3 list3 = new ListAdd3();
        /**
         * CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1。
         * 当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
         * http://www.importnew.com/15731.html
         */
        CountDownLatch latch = new CountDownLatch(1);
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 start");
                for (int i = 0; i < 7; i++) {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "添加一个元素");
                    list3.add();

                    //如果size为5，则释放锁
                    if (list3.size() == 5) {
                        System.out.println("t1 发布通知");
                        latch.countDown();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("t2 start");
                //如果size为不为5，则将锁给t1
                if (list3.size() <= 5) {
                    System.out.println("t2 wait");
                    latch.await();
                }
                System.out.println("t2 continue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //指定t2先行
        t2.start();
        //Thread.sleep(1000);
        t1.start();

    }
	/*
	t2 start
	Thread-0添加一个元素
	Thread-0添加一个元素
	Thread-0添加一个元素
	Thread-0添加一个元素
	Thread-0添加一个元素
	t1 发布通知
	t2 continue
	 */
}
