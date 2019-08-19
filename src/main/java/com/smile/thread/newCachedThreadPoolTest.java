package com.smile.thread;

import java.util.concurrent.*;

/**
 * 测试newCachedThreadPool，验证性能
 *
 * @author: ayuan
 * @create: 2019-03-04 11:37
 */

public class newCachedThreadPoolTest {

    public static void main(String[] args) {

        /**
         * newCachedThreadPool有oom隐患
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i < 1000000; i++){
            executorService.submit(new task());
        }

        /**
         * 毕玄所提问题，验证ThreadPoolExecutor的参数使用顺序
         * 基本数量，最大数量，最大等待时间，任务存储队列
         */
        /*
        ExecutorService service1 = new ThreadPoolExecutor(10, 100, 10, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(10));
        *//**
         * i 设置为10,20,110 都有不同
         *//*
        for (int i = 1; i <= 20; i++){
            service1.submit(new task());
        }
        */
    }

}

class task implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}