package com.smile.thread;

import java.sql.Time;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-03-08 10:28
 */
public class HappenBeforeTest {
    private int value = 0;
    /**
     * 如果将flag设置为static,则是线程可见的
     */
    /*
    private static  boolean flag = true;
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            flag = false;
        });
        t1.start();
        Thread t2 = new Thread(()->{
            System.out.println(flag);
        });
        t2.start();
    }
    */

    /**
     * 此处不添加volatile，线程t1的修改也happens-before t2
     */
    private volatile boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        HappenBeforeTest test = new HappenBeforeTest();
        CyclicBarrier barrier = new CyclicBarrier(2);
        Thread t1 = new Thread(()->{
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            test.writer();
        });

        Thread t2 = new Thread(()->{
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            test.reader();
        });
        t2.start();
        t1.start();
    }

    public void writer(){
        value = 40;
        flag = false;
    }

    public void reader(){
        System.out.println(value+"--"+flag);
    }


}
