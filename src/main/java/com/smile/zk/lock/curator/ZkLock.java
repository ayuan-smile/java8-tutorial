package com.smile.zk.lock.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZkLock {
    CuratorFramework client;
    InterProcessMutex mutex;

    public void lock() throws Exception{
        //创建zookeeper的客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        String zkAddress = "172.21.11.43:2181,172.21.11.44:2181,172.21.11.45:2181";
        //172.21.12.89:2181,172.21.12.90:2181,172.21.12.91:2181
        //127.0.0.1:55101,127.0.0.1:55201,127.0.0.1:55301
        client = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);
        client.start();

        //创建分布式锁, 锁空间的根节点路径为/curator/lock
        InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");
        mutex.acquire();
        //获得了锁, 进行业务流程
        System.out.println("Enter mutex");

    }

    public void unlock() throws Exception{
        if (mutex !=null){
            //完成业务流程, 释放锁
            mutex.release();
        }
        //关闭客户端
        client.close();
    }

}
