package com.smile.thread.communication;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 线程状态测试
 * @author: ayuan
 * @create: 2019-03-11 14:55
 */
public class ThreadStateDemo {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        // wait and notify demo
        //waitAndNotify();

        //join demo
        joinDemo();
        // yield demo
        //yield();
    }

    /**
     *  执行效果：
     * 启动了5个线程，打印出5个启动信息，然后在lock对象上执行wait，依次进入waiting状态。
     * 然后执行notify方法，唤醒第一个线程，打印end语句。
     * 然后执行notify方法，唤醒剩余的线程。
     * @throws InterruptedException
     */
    public static void waitAndNotify() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(()->{
                synchronized (lock){
                    try {
                        System.out.println("start thread "+Thread.currentThread().getName());
                        lock.wait();
                        System.out.println("end thread "+Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }

        TimeUnit.SECONDS.sleep(2);
        System.out.println("notify first thread");
        synchronized (lock) {
            lock.notify();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println("notify all thread");
        // 必须加锁访问lock,否则会报IllegalMonitorStateException异常
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    /**
     * 执行效果：
     * 启动了5个线程，如果不将子线程join到主线程，则主线程的最后一句end execute不一定回事最后执行的，。
     * 执行join过后，先执行完子线程的，在执行主线程
     *
     * @throws InterruptedException
     */
    public static void joinDemo() throws InterruptedException {
        List<Thread> tList = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(()->{
                System.out.println("start thread "+Thread.currentThread().getName());
            });
            tList.add(t);
        }
        System.out.println("start execute main thread");
        for (Thread t : tList) {
            /**
             * 此处start紧跟着join，可以实现线程顺序执行的效果。
             * 如果将start放到tList.add后面，则不能保证线程顺序执行。同理，去掉join也不能保证线程顺序执行。
             */
            t.start();
            t.join();
        }
        System.out.println("end execute main thread");
    }

    /**
     * 执行效果：
     * 执行效果同joinDemo，两种不同的实现方式，join是thread实例方法，需要将所有的线程都join到本线程，而yield只需要在本线程执行即可。
     */
    public static void yield(){
        List<Thread> tList = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(()->{
                System.out.println("start thread "+Thread.currentThread().getName());
            });
            tList.add(t);
            t.start();
        }
        System.out.println("start execute main thread");
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println("end execute main thread");
    }

}
