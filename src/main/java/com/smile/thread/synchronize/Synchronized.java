package com.smile.thread.synchronize;

public class Synchronized {
    private static Integer number = 1;
    private static Boolean inited = false;

    public void getNum() {
        number++;
        System.out.println("number:" + number);
    }

    public synchronized void getNumSyn() {
        number++;
        System.out.println("number syn:" + number);
    }

    public void getNumSyn1() {
        synchronized (number) {
            number++;
            System.out.println("number syn:" + number);
        }
    }
}
