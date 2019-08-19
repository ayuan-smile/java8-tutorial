package com.smile.thread.cas;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-01-31 14:51
 */
public class UpdateBaseDemo {
    public int val = 1;

    public boolean compareAndSwap(int value){
        if(this.val == 1){
            val = value;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UpdateBaseDemo demo = new  UpdateBaseDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Boolean> future1 = executorService.submit(()->{
           return  demo.compareAndSwap(2);
        });

        UpdateBaseDemo demo2 = new  UpdateBaseDemo();
        Future<Boolean> future2 = executorService.submit(() -> {
            return demo2.compareAndSwap(4);
        });

        System.out.println(String.format("f1=%s,f2=%s,val=%s",future1.get(),future2.get(),demo.val));
    }
}

