package com.smile.thread.volatiles;

/**
 * volatile只能保证可见性，不能保证原子性。因此对于num++对应多条机器指令的操作，无法做到原子操作，需要使用同步操作，添加synchronized才可以。
 * 而使用atomicInteger类则可以不使用同步关键字，直接累加
 *
 *
 * @author: ayuan
 * @create: 2019-03-08 11:00
 */
public class VolatileTest1 {
    private volatile int num = 0;
    private AtomicInteger atomicNum = new AtomicInteger();;

    private static final int THREADS_COUNT = 20;
    public synchronized void increase(){
        num ++;
    }

    public void increaseAtomic(){
        atomicNum.incrementAndGet();
    }

    public static void main(String[] args) {
        VolatileTest1 test1 = new  VolatileTest1();
        for (int i = 0; i < THREADS_COUNT; i++) {
            Thread t = new Thread(()->{
                for (int k = 0; k < 10000; k++) {
                    test1.increase();
                    test1.increaseAtomic();
                }
            });
            t.start();
        }
        while (Thread.activeCount()>2){
            // 让出本身的使用权，是自己一直处于可运行状态
            Thread.yield();
            // 也可以对所以启动的线程执行join，意思是将子线程join到调用者线程，并先执行子线程
            // t.join();
        }
        System.out.println(test1.num);
        System.out.println(test1.atomicNum.get());
    }
}
