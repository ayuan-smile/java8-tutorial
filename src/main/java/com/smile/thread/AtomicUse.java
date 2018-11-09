package com.smile.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicUse {
    private static AtomicInteger count = new AtomicInteger(0);

    // 多个addAndGet在一个方法内是非原子性的，需要加synchronized进行修饰，保证4个addAndGet整体原子性.顺序执行

    /**
     * synchronized
     */
    public synchronized int multiAdd() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.incrementAndGet();
        return count.incrementAndGet();
		/*count.addAndGet(1);
		count.addAndGet(2);
		count.addAndGet(3);
		count.addAndGet(4); // +10,执行到这里相当于加10->1+2+3+4=10，
		return count.get();*/
    }

    public static void main(String[] args) {
        final AtomicUse au = new AtomicUse();
        List<Thread> ts = new ArrayList<Thread>();
        for (int i = 0; i < 100; i++) {
            ts.add(new Thread(() -> System.out.println(au.multiAdd())));
        }
        for (Thread t : ts) {
            t.start();
        }
    }
}