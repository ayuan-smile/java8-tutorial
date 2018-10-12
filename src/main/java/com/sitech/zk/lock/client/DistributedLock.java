package com.sitech.zk.lock.client;

import java.util.concurrent.TimeUnit;


/**
 * https://blog.csdn.net/sunfeizhi/article/details/51926396
 */
public interface DistributedLock {


    /**
     * 获取锁，没有则等待
     * @throws Exception
     */
    public void acquire () throws Exception;

    /**
     * 获取锁，直到设置的超时时间
     * @param time
     * @param timeUnit
     * @return
     * @throws Exception
     */
    public boolean acquire(long time, TimeUnit timeUnit) throws Exception;


    /**
     * 释放锁
     * @throws Exception
     */
    public void release() throws Exception;
}
