package com.smile.thread;

public class RunThread extends Thread {
    // isRunning没有被volatile关键字修饰
    private boolean isRunning = true;//①

    private void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        System.out.println("开始进入run方法..");
        while (isRunning == true) {
            // 一直循环........
        }
        System.out.println(12);
        System.out.println("while循环结束,线程停止");
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread rt = new RunThread();
        rt.start();
        Thread.sleep(3000);
        rt.setRunning(false);
        System.out.println("isRunning的值已经被设置了false");
        Thread.sleep(1000);
        System.out.println(rt.isRunning);
    }
}
