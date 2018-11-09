package com.smile.thread.synchronize;

public class SynchronizedTest1 {
    public synchronized void method1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加上synchronized修饰method2
     */
    public void method2() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        //final 类声明为final，则获取的为对象锁。否则为方法锁。
        SynchronizedTest1 mo = new SynchronizedTest1();
        /**
         * 分析：如果 t1线程先持有TestObject对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronized修饰的方法
         * 如果t1线程先持有TestObject对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，
         * 也就是同步。
         * 如果method2不加锁，则t1执行method1先获取锁，先执行。同时，异步执行method2.输出为t1,t2，等待2s结束
         * 如果method2加锁，则t1与t2 执行时会竞争锁，如果t2先竞争到则输出t2,t1等待，如果t1先竞争到，则t2需要等待t1执行完后再执行，执行结果为t1,等待,t2
         */
        Thread t1 = new Thread(() -> mo.method1(), "t1");
        Thread t2 = new Thread(() -> mo.method2(), "t2");

        t1.start();
        t2.start();
    }
}