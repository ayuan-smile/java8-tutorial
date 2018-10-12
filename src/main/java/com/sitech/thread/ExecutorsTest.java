package com.sitech.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExecutorsTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //testShutdown();
        //testFuture();
        //testInvokeAll();
        testScheduledRate();
    }

    public static void testShutdown() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //单线程，thread名称相同。main方法结束会一直监听
        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        try {
            //executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (!executorService.isTerminated()) {
                System.err.println("cancel non-finished tasks");
                executorService.shutdownNow();
            }
            System.out.println("shutdown finished");
        }
    }

    public static void testFuture() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            Future<Integer> future = executorService.submit(() -> {
                Thread.sleep(1 * 1000);
                return 123;
            });
            System.out.println("future done? " + future.isDone());
            //get方法会一直阻塞主线程
            //Integer result = future.get();
            //设置超时时间
            Integer result = future.get(3, TimeUnit.SECONDS);

            System.out.println("future done? " + future.isDone());
            System.out.println("result:" + result);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void testInvokeAll() throws InterruptedException, ExecutionException {
        //多个call,work-stealing算法 线程池。使用cpu核数作为并行因子数来创建线程池
        ExecutorService stealingPool = Executors.newWorkStealingPool();
        List<Callable<String>> tasks = Arrays.asList(() -> "task1", () -> "task2", () -> "task3");
        List<Future<String>> invokeAll = stealingPool.invokeAll(tasks);
        invokeAll.stream().map((f) -> {
            try {
                return f.get();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }).forEach(System.out::println);

        List<Callable<String>> tasks2 = Arrays.asList(
                callable("task1", 2),
                callable("task2", 1),
                callable("task3", 3));
        String invokeAny = stealingPool.invokeAny(tasks2);
        System.out.println(invokeAny);

    }

    public static void testScheduled() throws InterruptedException, ExecutionException {
        //支持任务调度，多次执行，延迟执行
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        //定义延迟5s执行
        ScheduledFuture<?> future = executorService.schedule(task, 5, TimeUnit.SECONDS);
        //sleep 3s
        TimeUnit.SECONDS.sleep(3);
        //剩余延迟时间
        long remainingDelay = future.getDelay(TimeUnit.SECONDS);
        System.out.println("Remaining Delay: " + remainingDelay);
    }

    public static void testScheduledRate() throws InterruptedException, ExecutionException {
        //支持任务调度，多次执行，延迟执行
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Scheduling: " + System.nanoTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        //初始化0s延迟，执行1s
        long initialDelay = 0L, delay = 1L;
        //固定频率执行.每间隔delay执行一次，如果任务执行时间超过delay，则按照任务执行时间
        ScheduledFuture<?> fixedRate = executorService.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

        //固定延迟执行，任务之间的固定延迟,不确定执行时间使用
        //executorService.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
    }

    static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }
}
