package com.smile.jvm;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 测试堆内存溢出
 * VM参数：-Xms512m -Xmx512m -XX:+PrintGCDetails
 * @author: ayuan
 * @create: 2019-03-07 14:32
 */
public class Oom4Heap {
    public static void main(String[] args) throws InterruptedException {
        List l = new LinkedList();
        // Enter infinite loop which will add a String to the list: l on each
        // iteration.
        do {
            l.add(new String("Hello, World"));
            TimeUnit.SECONDS.sleep(3);
        } while (true);
    }
}
