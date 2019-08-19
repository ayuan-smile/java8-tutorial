package com.smile.thread.volatiles;

/**
 * volatile 变量 自 增 运算 测试 *
 *
 * @author: ayuan
 * @create: 2019-03-07 17:41
 */

public class VolatileTest {
    public static volatile int race = 0;

    public static void increase() {
        race++;
    }
    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        //等待 所有 累加 线程 都 结束
        while (Thread.activeCount() > 2){
            /*
            ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
            int noThreads = threadGroup.activeCount();
            Thread[] lstThreads = new Thread[noThreads];
            threadGroup.enumerate(lstThreads);
            for (int i = 0; i < noThreads; i++){
                System.out.println("线程号：" + i + " = " + lstThreads[i].getName());
            }
            System.out.println("Thread.activeCount:" + Thread.activeCount());
            */
            Thread.yield();
        }
        System.out.println(race);
    }
}

