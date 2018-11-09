package com.smile.thread;

import com.smile.tutorial.entity.ClassMate;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

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


    }
}