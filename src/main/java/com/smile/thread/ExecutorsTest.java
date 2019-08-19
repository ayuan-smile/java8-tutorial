package com.smile.thread;

import com.smile.utils.Tuple2;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ExecutorsTest {
    public static void main(String[] args) throws Exception {
        //testShutdown();
        //testFuture();
        testMultiFuture();
        //testCallable();
        //testInvokeAll();
        //testScheduled();
        //testScheduledRate();
        //testMultiScheduled();
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
                TimeUnit.SECONDS.sleep(60);
                return 123;
            });
            System.out.println("future done? " + future.isDone());
            //get方法会一直阻塞主线程
            //Integer result = future.get();
            //设置超时时间
            Integer result = future.get(120, TimeUnit.SECONDS);

            System.out.println("future done? " + future.isDone());
            System.out.println("result:" + result);

            /**
             * 测试停止
             */
            /*
            Callable<String> callable = ()-> {
                TimeUnit.SECONDS.sleep(5);
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("fail");
                    return "fail";
                }
                return "success";
            };

            Future cancelFuture = executorService.submit(callable);
            TimeUnit.SECONDS.sleep(2);
            // 测试cancel
            cancelFuture.cancel(true);
            System.out.println(String.format("canceled=%s,done=%s",cancelFuture.isCancelled(),cancelFuture.isDone()));
            System.out.println(cancelFuture.get());
            */
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void testMultiFuture(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            List<Future<Integer>> futureList = new ArrayList();
            for(int i=0;i<10;i++){
                final int random = i;
                Future<Integer> future = executorService.submit(() -> {
                    System.out.println("start execute task "+ random);
                    TimeUnit.SECONDS.sleep(random * 3);
                    return random;
                });
                futureList.add(future);
            }
            // 验证同步非阻塞：0,3,6
            /*
            TimeUnit.SECONDS.sleep(10);
            for (Future<Integer> future : futureList) {
                if(future.isDone()){
                    Integer result = future.get(5, TimeUnit.SECONDS);
                    System.out.println("future done: " + future.isDone()+",result:" + result);
                }
            }
            */

            // 循环处理完成所有线程.
            // 注意 此处可以启动一个线程来注册一个listener，循环查询结果 并开始后续操作
            List<Tuple2<Future<Integer>, Boolean>> tuple2List = futureList
                .stream()
                .map(f -> new Tuple2<Future<Integer>, Boolean>(f, false))
                .collect(Collectors.toList());
            // 此处使用future.isDone判断，在最后一个future无法执行完成，改成使用中间变量则可以执行完成。
            //while (tuple2List.stream().anyMatch(f->!f._1().isDone())){
            while (tuple2List.stream().anyMatch(f->!f._2())){
                // 循环所有future，只判断未完成的。并且只执行一次。
                for (int k = 0; k < tuple2List.size(); k++) {
                    Tuple2<Future<Integer>, Boolean> tuple2 = tuple2List.get(k);
                    if(!tuple2._2()){
                        Future<Integer> future = tuple2._1();
                        if(future.isDone()){
                            Integer result = future.get(5, TimeUnit.SECONDS);
                            System.out.println(String.format("future %s done: %s,result:%s",k,future.isDone(),result));
                            tuple2.setT2(true);
                        }else {
                            System.out.println(String.format("future %s done: %s",k,future.isDone()));
                        }
                    }
                }
                TimeUnit.SECONDS.sleep(5);
                System.out.println("#########################");
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        /**
         * 执行结果：
         * 执行结果与主线程sleep时间有关，如果设置sleep 10s，则只能执行3个线程，因为每个线程等待执行时间为3的倍数，依次为0,3,6,9.。。
         * 并且此处只生成了一个线程，只有一个worker，则task会依次加入到blockingQueue中，阻塞执行。
         * 此处维护了一个线程池，也维护了一个任务队列，新来的任务则会依次增加到任务队列，线程执行完某个任务后再执行任务队列中的其他任务。
         * 但是此处，线程执行完任务后，并不能通知调用者，因此是一种同步方式。由于采用多线程执行，此处为非阻塞，综上这种方式为同步非阻塞的方式。
         */
    }

    public static void testCallable() throws Exception {
        Callable<String> callable = callable("test1",5);
        System.out.println("call start");
        // 此处也会阻塞调用
        System.out.println(callable.call());
        System.out.println("call end");
    }

    public static void testInvokeAll() throws InterruptedException, ExecutionException {
        //多个call,work-stealing算法 线程池。使用cpu核数作为并行因子数来创建线程池
        /**
         * ForkJoin的核心思想就是：将一个大任务切割成若干个小任务同时进行，最后等所有任务完成，合并任务结果。
         * 工作窃取算法是其的一种优化，比如有个线程执行完了其任务，另一个线程还有N个任务，这样等待就比较耗时，
         * 所以空闲的线程会从忙碌的线程处理的任务链尾端拿任务进行执行。
         */
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
        ScheduledFuture<?> future = executorService.schedule(task, 20, TimeUnit.SECONDS);
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

    public static void testMultiScheduled() throws InterruptedException, ExecutionException {
        //支持任务调度，多次执行，延迟执行
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            System.out.println("Scheduling: " + System.nanoTime());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        //定义延迟5s执行
        executorService.schedule(task, 5, TimeUnit.SECONDS);
        executorService.schedule(task, 5, TimeUnit.SECONDS);
        executorService.schedule(task, 5, TimeUnit.SECONDS);
    }
}
