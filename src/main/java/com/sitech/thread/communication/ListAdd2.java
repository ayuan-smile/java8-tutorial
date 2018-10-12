package com.sitech.thread.communication;

import java.util.ArrayList;
import java.util.List;

public class ListAdd2 {
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
        ListAdd2 list2 = new ListAdd2();
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("t1 start");
                    for (int i = 0; i < 7; i++) {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "添加一个元素");
                        list2.add();

                        //如果size为5，则释放锁
                        if (list2.size() == 5) {
                            System.out.println("t1 发布通知");
                            lock.notify();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("t2 start");
                    //如果size为不为5，则将锁给t1
                    if (list2.size() <= 5) {
                        System.out.println("t2 wait");
                        lock.wait();
                    }
                    System.out.println("t2 continue");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //指定t2先行
        t2.start();
        Thread.sleep(1000);
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
