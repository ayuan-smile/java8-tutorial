package com.smile.tutorial.func;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-04-15 10:29
 */
public class FuncTest {
    public static void main(String[] args) {
        Runnable r = ()-> System.out.println("start run");
        r.run();
    }
}
