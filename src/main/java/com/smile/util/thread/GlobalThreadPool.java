package com.smile.util.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalThreadPool {
	private static final Logger logger = LoggerFactory.getLogger(GlobalThreadPool.class);
	private static final Object LOCK = new Object();
	private static Executor executor;
	
	public static void execute(Runnable command) {
		if(null != command) {
			GlobalThreadPool.getExecutor().execute(command);
		}
	}
	
	public static Executor getExecutor() {
		if (null == executor) {
			synchronized (LOCK) {
				if (null == executor) {
					executor = GlobalThreadPool.getExecutor("GlobalThreadPool");
				}
			}
		}
		return executor;
	}
	
	private static Executor getExecutor(String name) {
		int corePoolSize = 1;
		int maximumPoolSize = Integer.MAX_VALUE;
		long keepAliveTime = 60L;
		TimeUnit timeUnit = TimeUnit.SECONDS;
		BlockingQueue<Runnable> queue = new SynchronousQueue<Runnable>();
		ThreadFactory threadFactory = new NamedThreadFactory(name+"_thread", true);
		
		ReporterThreadPoolExecutor pool = new ReporterThreadPoolExecutor(name, corePoolSize,
				maximumPoolSize, 
				keepAliveTime, 
				timeUnit, 
				queue, 
				threadFactory);
		pool.setBeforeExecuteReporter(new ReporterThreadPoolExecutor.Reporter() {
			@Override
			public void doReport(String message) {
				logger.info(message);
			}
		});
		
		//Executor pool1 = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, queue);
		
		return pool;
	}
}
