package com.smile.thread.entity;

public class ThreadP extends Thread {
    private Person count;

    public ThreadP(Person count) {
        this.count = count;
    }

    public void run() {
        try {
            count.workSyn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
