package com.sitech.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileInt extends Thread {
    private static volatile Integer count = 0; //使用 volatile 修饰基本数据内存不能保证原子性
    private static AtomicInteger atomicCount = new AtomicInteger();

    public void run() {
		/*synchronized (count){
		}*/

        for (int i = 0; i < 1000; i++) {
            count++;
            atomicCount.incrementAndGet();
        }
        System.out.println(count);
        System.out.println(atomicCount);
    }

    public static void main(String[] args) throws InterruptedException {
		/*
		VolatileInt volatileInc = new VolatileInt();
		Thread t1 = new Thread(volatileInc, "t1");
		Thread t2 = new Thread(volatileInc, "t2");
		t1.start();
		t2.start();
		Thread.sleep(1*1000);
		*/
        VolatileInt[] volatileIncs = new VolatileInt[100];
        for (int i = 0; i < 10; i++) {
            volatileIncs[i] = new VolatileInt();
        }
        for (int i = 0; i < 10; i++) {
            volatileIncs[i].start();
        }
    }

}
