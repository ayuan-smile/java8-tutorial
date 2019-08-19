package com.smile.thread.communication;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-03-11 19:41
 */
public class ThreadTest3 {
    private static Lock lock=new ReentrantLock();
    private static int state = 0;

    static class ThreadA extends Thread {
        @Override
        public void run() {
            lock.lock();
            if (state % 3 == 0) {
                System.out.print("A=>");
                state++;
            }
            lock.unlock();
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            lock.lock();
            if (state % 3 == 1) {
                System.out.print("B=>");
                state++;
            }
            lock.unlock();
        }

    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            lock.lock();
            if (state % 3 == 2) {
                System.out.print("C=>");
                state++;
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            new ThreadA().start();
            new ThreadB().start();
            new ThreadC().start();
            TimeUnit.SECONDS.sleep(2);
            state = 0;
            System.out.println();
        }
    }

}