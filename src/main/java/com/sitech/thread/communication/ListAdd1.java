package com.sitech.thread.communication;

import java.util.ArrayList;
import java.util.List;

public class ListAdd1 {
    /**
     * 添加volatile，则t2可以立即获取到最新的list size，继而停止.不添加volatile，t2无法获取t1最list的操作，t2无法停止
     */
    private volatile List<String> list = new ArrayList<>();

    private void add() {
        list.add("test1");
    }

    private int size() {
        return list.size();
    }

    public static void main(String[] args) {
        ListAdd1 list1 = new ListAdd1();
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "添加一个元素");
                    list1.add();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                if (list1.size() == 5) {
                    System.out.println(Thread.currentThread().getName() + "收到通知，list size=5,stop");
                    break;
                }
            }
        });

        t1.start();
        t2.start();
    }
}
