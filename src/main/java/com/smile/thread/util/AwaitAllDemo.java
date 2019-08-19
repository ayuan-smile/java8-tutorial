package com.smile.thread.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-03-04 15:25
 */
public class AwaitAllDemo {
    private static final ReentrantLock lock = new ReentrantLock();
    private final Condition trip = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        AwaitAllDemo demo = new AwaitAllDemo();
        List<Thread> tList = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(()->{
                lock.lock();
                try {
                    demo.trip.await();
                    System.out.println(Thread.currentThread().getName()+" end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
            tList.add(thread);
        }
        System.out.println("start all");
        lock.lock();
        demo.trip.signalAll();
        lock.unlock();


    }
}
