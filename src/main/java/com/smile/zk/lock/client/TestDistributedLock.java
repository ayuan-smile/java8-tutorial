package com.smile.zk.lock.client;

import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

public class TestDistributedLock {

    public static void main(String[] args) {
        String zkAddress = "172.21.12.89:2181,172.21.12.90:2181,172.21.12.91:2181";
        //172.21.12.89:2181,172.21.12.90:2181,172.21.12.91:2181
        //127.0.0.1:55101,127.0.0.1:55201,127.0.0.1:55301
        final ZkClientExt zkClientExt1 = new ZkClientExt(zkAddress, 5000, 5000, new BytesPushThroughSerializer());
        final SimpleDistributedLockMutex mutex1 = new SimpleDistributedLockMutex(zkClientExt1, "/mutex");

        final ZkClientExt zkClientExt2 = new ZkClientExt(zkAddress, 5000, 5000, new BytesPushThroughSerializer());
        final SimpleDistributedLockMutex mutex2 = new SimpleDistributedLockMutex(zkClientExt2, "/mutex");

        try {
            mutex1.acquire();
            System.out.println("Client1 locked");
            Thread client2Thd = new Thread(new Runnable() {

                public void run() {
                    try {
                        mutex2.acquire();
                        System.out.println("Client2 locked");
                        mutex2.release();
                        System.out.println("Client2 released lock");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            client2Thd.start();
            Thread.sleep(5000);
            mutex1.release();
            System.out.println("Client1 released lock");

            client2Thd.join();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
