package com.smile.thread;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.math.IntMath;
import com.smile.tutorial.entity.ClassMate;
import org.omg.CORBA.TIMEOUT;

import java.io.Serializable;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Test implements Serializable {
    public static void main(String[] args) {
        System.out.println();
        LinkedList<Object> list = new LinkedList<>();
        list.add(1);

        Random r = new Random(111);

        AtomicUse use = new AtomicUse();
        use.multiAdd();

        ClassMate mate = new ClassMate();
        mate.getName();

        System.out.println(IntMath.divide(1, 2, RoundingMode.UP));

        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("172.21.1.13","222");
        hashMap.put("172.21.1.12","172.21.1.12");
        System.out.println(hashMap.get("172.21.1.12"));

        ArrayList<ClassMate> mateList = Lists.newArrayList();
        mateList.add(new ClassMate("id1","name1"));
        mateList.add(new ClassMate("id1",null));
        mateList.forEach(m->m.setName("nnnn"));
        mateList.forEach(System.out::println);

        for (ClassMate m : mateList) {
            m.setName("mmmm");
        }
        mateList.forEach(System.out::println);

        Map<String, List<ClassMate>> runnningListMap = mateList.stream()
            .collect(Collectors.groupingBy(task -> {
                return task.getName();
            }));
        Iterator<Map.Entry<String, List<ClassMate>>> runnings = runnningListMap.entrySet().iterator();
        while (runnings.hasNext()){
            Map.Entry<String, List<ClassMate>> next = runnings.next();
            hashMap.put(next.getKey(), next.getValue());
        }

        /*
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                int i = 10/0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(()->{
            try {
                System.out.println(11);
                TimeUnit.SECONDS.sleep(10);
                System.out.println(11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        */

    }
}