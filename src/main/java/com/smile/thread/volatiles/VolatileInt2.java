package com.smile.thread.volatiles;

import com.smile.thread.volatiles.AtomicInteger;

public class VolatileInt2 extends Thread {
    private volatile Integer count = 0; //使用 volatile 修饰基本数据内存不能保证原子性
    private AtomicInteger atomicCount = new AtomicInteger();

    public void run() {
        for (int i = 0; i < 1000; i++) {
            count++;
            atomicCount.incrementAndGet();
        }
        System.out.println(String.format("count=%s,atomicCount=%s",count,atomicCount));
        System.out.println(atomicCount+Integer.toHexString(atomicCount.hashCode()));
        System.out.println(count.toString());
    }

    public static void main(String[] args) throws InterruptedException {
//        VolatileInt2[] volatileIncs = new VolatileInt2[10];
//        VolatileInt2 volatileInt2 = new VolatileInt2();
//        for (int i = 0; i < 10; i++) {
//            volatileIncs[i] = new VolatileInt2();
//        }
//        for (int i = 0; i < 10; i++) {
//            volatileIncs[i].start();
//        }

        VolatileInt2 volatileInc = new VolatileInt2();
        Thread t1 = new Thread(new VolatileInt2(), "t1");
        Thread t2 = new Thread(new VolatileInt2(), "t2");
        t1.start();
        t2.start();
        Thread.sleep(1*1000);
    }

}
