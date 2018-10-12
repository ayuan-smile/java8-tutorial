package com.sitech.thread.util;

import java.util.concurrent.*;

public class CyclicBarrierWalk {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3);
        ExecutorService service = Executors.newCachedThreadPool();
        Runnable task = ()->{
            try {
                //TimeUnit.SECONDS.sleep((long)Math.random()*10000);
                Thread.sleep((long)(Math.random()*10000));
                System.out.println(String.format("线程%s到达地点1，当前已有%s个已经到达，",Thread.currentThread().getName(),cb.getNumberWaiting()+1)
                        +(cb.getNumberWaiting()==2?"都到了继续走":"等一等"));
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            try {
                //TimeUnit.SECONDS.sleep((long)Math.random()*10000);
                Thread.sleep((long)(Math.random()*10000));
                System.out.println(String.format("线程%s到达地点2，当前已有%s个已经到达，",Thread.currentThread().getName(),cb.getNumberWaiting()+1)
                        +(cb.getNumberWaiting()==2?"都到了继续走":"等一等"));
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            try {
                //TimeUnit.SECONDS.sleep((long)Math.random()*10000);
                Thread.sleep((long)(Math.random()*10000));
                System.out.println(String.format("线程%s到达地点3，当前已有%s个已经到达，",Thread.currentThread().getName(),cb.getNumberWaiting()+1)
                        +(cb.getNumberWaiting()==2?"都到了继续走":"等一等"));
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 3; i++) {
            service.submit(task);
        }
        service.shutdown();
    }

}
