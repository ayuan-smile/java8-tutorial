package com.sitech.thread.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Person {
    public int count = 0;
    public AtomicInteger acount = new AtomicInteger(0);

    public void work() throws Exception {
        Thread.sleep(5l);
        count += 1;
        acount.incrementAndGet();
        //System.out.println(String.format("%s-%s", Thread.currentThread().getName(),count));
        System.out.println(String.format("%s-%s", Thread.currentThread().getName(), acount));
    }

    public synchronized void workSyn() throws Exception {
        Thread.sleep(5l);
        count += 1;
        System.out.println(String.format("%s-%s", Thread.currentThread().getName(), count));
    }
}
