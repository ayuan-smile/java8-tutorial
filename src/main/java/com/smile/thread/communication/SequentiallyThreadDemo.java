package com.smile.thread.communication;

import com.google.common.collect.Lists;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程顺序执行方法
 * @author: ayuan
 * @create: 2019-03-11 17:21
 */
public class SequentiallyThreadDemo {
    private static ReentrantLock lock = new ReentrantLock();
    /**
     * 使用join生成
     * @throws InterruptedException
     */
    public static void joinDemo() throws InterruptedException {
        ThreadStateDemo.joinDemo();
    }

    /**
     * 通过数量为1的线程池，顺序执行
     */
    public static void executorDemo() {
        List<Thread> tList = Lists.newArrayList();
        for (int i = 0; i < 4; i++) {
            final int index = i;
            Thread t = new Thread(()->{
                System.out.println("start thread " + index);
            });
            tList.add(t);
        }
        ExecutorService service = Executors.newFixedThreadPool(1);
        tList.forEach(t->{
            service.execute(t);
        });
        service.shutdown();
    }

    /**
     * 参考：https://blog.csdn.net/tingting256/article/details/52551687
     * 使用lock
     */
    public static void testLock(){
        List<Thread> tList = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            final int index = i;
            Thread t = new Thread(()->{
                lock.lock();
                System.out.println("start thread " + index);
                lock.unlock();
            });
            t.start();
            tList.add(t);
        }
//        tList.forEach(t->{
//            t.start();
//        });
    }

    /**
     * 使用condition
     */
    public static void blockQueue(){
        ArrayBlockingQueue queue = new ArrayBlockingQueue(5,true);
        for (int i = 0; i < 3; i++) {
            final int index = i;
            Thread t = new Thread(()->{
                System.out.println("queue start thread " + index);
            });
            queue.add(t);
        }
        while (queue.size()>0){
            try {
                Object take = queue.take();
                ((Thread)take).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用Semaphore
     */

    public static void main(String[] args) throws InterruptedException {
        // 使用join完成
        //joinDemo();
        // 使用线程池完成
        //executorDemo();
        // testLock无法保证顺序执行
        // testLock();
        // blockQueue无法保证顺序执行
        // blockQueue();
        for (int i = 0; i < 10; i++) {
            executorDemo();
            TimeUnit.SECONDS.sleep(2);
            System.out.println();
        }
    }
}
