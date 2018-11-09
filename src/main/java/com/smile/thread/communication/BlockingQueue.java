package com.smile.thread.communication;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

//http://xujin.org/bf/bf-thread-singal/#%E6%9C%80%E5%8E%9F%E5%A7%8B%E7%BA%BF%E7%A8%8B%E9%97%B4%E9%80%9A%E4%BF%A1%E4%BB%A3%E7%A0%81
public class BlockingQueue {
    private volatile LinkedList<Object> list = new LinkedList<>();
    private AtomicInteger count = new AtomicInteger(0);
    private Object lock = new Object();

    // 制定元素的上限和下限
    private final int maxSize;
    private final int minSize = 0;

    public BlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    private void put(Object obj) {
        synchronized (lock) {
            try {
                //如果size为5，则释放锁
                if (list.size() == maxSize) {
                    System.out.println("queue is full,wait");
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + " add " + obj.toString());
                list.add(obj);
                count.getAndDecrement();
                lock.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Object take() {
        Object obj = null;
        synchronized (lock) {
            try {
                //如果size为0，则释放锁
                if (list.size() == minSize) {
                    System.out.println("queue is full,wait");
                    lock.wait();
                }
                obj = list.removeFirst();
                System.out.println(Thread.currentThread().getName() + " consume " + obj.toString());
                count.getAndDecrement();
                lock.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    private int size() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new BlockingQueue(5);
        queue.put("q1");
        queue.put("q2");
        queue.put("q3");
        queue.put("q4");
        queue.put("q5");
        System.out.println("list size:" + queue.size());

        Thread t1 = new Thread(() -> {
            queue.put("q6");
            queue.put("q7");
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                Object obj1 = queue.take();
                System.out.println("take " + obj1);
                Thread.sleep(1000);
                Object obj2 = queue.take();
                System.out.println("take " + obj2);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "t2");

        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
	/*
	main add q1
	main add q2
	main add q3
	main add q4
	main add q5
	list size:-5
	queue is full,wait
	t2 consume q1
	t1 add q6
	take q1
	queue is full,wait
	t2 consume q2
	take q2
	t1 add q7
	*/
}
