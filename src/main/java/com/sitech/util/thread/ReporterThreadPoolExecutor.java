package com.sitech.util.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ReporterThreadPoolExecutor extends ThreadPoolExecutor {
	public static interface Reporter {
		void doReport(String message);
	}
		
	private static final Map<String, Integer> nameCache = new HashMap<>();

	private static synchronized void setName(ReporterThreadPoolExecutor self, String name) {
		int index = 0;
		if (null == name || (name = name.trim()).isEmpty()) {
			name = "Reporter-Thread-Pool";
		}
		if(nameCache.containsKey(name)) {
			Integer num = nameCache.get(name);
			if(null != num) {
				index = num + 1;
			}
		}
		self.name = name + "-" + index;
	}
	
	private String name;
	
	private Reporter beforeExecuteReporter = null;
	
	private Reporter afterExecuteReporter = null;

	public ReporterThreadPoolExecutor(String name, int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		setName(this, name);
		
	}

	public ReporterThreadPoolExecutor(String name, int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				handler);
		setName(this, name);
	}

	public ReporterThreadPoolExecutor(String name, int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory);
		setName(this, name);
	}

	public ReporterThreadPoolExecutor(String name, int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
			RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory, handler);
		setName(this, name);
	}
	
	public String getReporterMessage() {
		String message = String.format("Thread pool [%s] report: "
				+ "Pool Size: %d (active: %d, core: %d, max: %d, largest: %d, queue: %d), "
				+ "Task: %d (completed: %d),"
				+ "Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s) ",
				this.name,
				super.getPoolSize(),
				
				super.getActiveCount(),
				super.getCorePoolSize(),
				super.getMaximumPoolSize(),
				super.getLargestPoolSize(),
				super.getQueue().size(),
				
				super.getTaskCount(),
				super.getCompletedTaskCount(),
				super.isShutdown(),
				super.isTerminated(),
				super.isTerminating());
		return message;
	}
	
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		this.doReport(this.beforeExecuteReporter);
	}
	
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		this.doReport(this.afterExecuteReporter);
	}
	
	protected void terminated() { }
	
	private void doReport(Reporter reporter) {
		if (null == reporter) {
			return;
		}
		String message = this.getReporterMessage();
		try {
			reporter.doReport(message);
		} catch (Exception e) {
			
		}
		
	}

	public void setBeforeExecuteReporter(Reporter beforeExecuteReporter) {
		this.beforeExecuteReporter = beforeExecuteReporter;
	}

	public void setAfterExecuteReporter(Reporter afterExecuteReporter) {
		this.afterExecuteReporter = afterExecuteReporter;
	}
}