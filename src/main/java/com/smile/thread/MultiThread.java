package com.smile.thread;

public class MultiThread {
    public static void main(String[] args) {
        /*
        RunnableDemo test = new RunnableDemo();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();
        */
        new Thread(new RunnableDemo()).start();
        new Thread(new RunnableDemo()).start();
        new Thread(new RunnableDemo()).start();
        new Thread(new RunnableDemo()).start();
        /*
        new ThreadTest().start();
        new ThreadTest().start();
        new ThreadTest().start();
        new ThreadTest().start();
        */
    }
}

class RunnableDemo implements Runnable {
    private int count = 10;

    public void run() {
        while (count > 0) {
            System.out.println(Thread.currentThread().getName() + "   " + count--);
        }
    }
}

class ThreadTest extends Thread {
    private int count = 10;

    public void run() {
        while (count > 0) {
            System.out.println(Thread.currentThread().getName() + "   " + count--);
        }
    }
}

