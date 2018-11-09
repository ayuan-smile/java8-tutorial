package com.smile.thread;

import java.util.concurrent.TimeUnit;

public class RunnableTest {
    public static void main(String[] args) throws InterruptedException {
		/*
		Runnable task = () -> {
		    String threadName = Thread.currentThread().getName();
		    System.out.println("Hello " + threadName);
		};
		//顺序执行，立即执行
		task.run();

		Thread thread = new Thread(task);
		thread.start();
		//通过主线程延迟，先执行子线程
		//TimeUnit.SECONDS.sleep(1);
		//Thread.sleep(1);
		System.out.println("Done!");
		*/


        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Foo " + name);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Done!");

    }
}
