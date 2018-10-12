package com.sitech.zk.lock.curator;

public class DistributedLockDemo {

    public static void main(String[] args) throws Exception{

        Runnable task = ()->{
            ZkLock lock = new ZkLock();
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+" is running");
                Thread.sleep(3000);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(task);
            t.start();
        }

    }
}
