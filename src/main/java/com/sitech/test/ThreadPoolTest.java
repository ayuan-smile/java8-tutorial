package com.sitech.test;

import com.sitech.thread.synchronize.Synchronized;
import com.sitech.util.thread.GlobalThreadPool;

public class ThreadPoolTest {
	public static void main(String[] args) throws Exception{
		//GlobalThreadPool.execute(()->System.out.println("22"));
		
		GlobalThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("33");
			}
		});
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("t133");
			}
		});
		//t1.start();
		GlobalThreadPool.execute(t1);
		
		for (int i = 0; i < 3; i++) {
			GlobalThreadPool.execute(()->{
				Synchronized syn = new Synchronized();
				syn.getNum();
			});
		}
		
		Thread.sleep(1000*3);
		
		for (int i = 0; i < 3; i++) {
			GlobalThreadPool.execute(()->{
				Synchronized syn = new Synchronized();
				syn.getNumSyn1();
			});
		}
		Thread.sleep(1000*3);
		/*
		executor.execute(()->System.out.println("22"));
		
		ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		
		Runnable myRunnable = new Runnable() {
		    @Override
		    public void run() {
		        try {
		            Thread.sleep(2000);
		            System.out.println(Thread.currentThread().getName() + " run");
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }

		    }
		};
		
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		System.out.println("---先开三个---");
		System.out.println("核心线程数" + executor.getCorePoolSize());
		System.out.println("线程池数" + executor.getPoolSize());
		System.out.println("队列任务数" + executor.getQueue().size());
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		System.out.println("---再开三个---");
		System.out.println("核心线程数" + executor.getCorePoolSize());
		System.out.println("线程池数" + executor.getPoolSize());
		System.out.println("队列任务数" + executor.getQueue().size());
		Thread.sleep(8000);
		System.out.println("----8秒之后----");
		System.out.println("核心线程数" + executor.getCorePoolSize());
		System.out.println("线程池数" + executor.getPoolSize());
		System.out.println("队列任务数" + executor.getQueue().size());
		executor.shutdown();
		*/
	}
}
