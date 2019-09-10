package com.smile.designpattern.templatemethod;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 15:58
 */
public class Client {
    public static void main(String[] args) {
        // 执行类A
        ConcreteClassA classA = new ConcreteClassA();
        classA.templateMethod();

        System.out.println("============");
        // 执行类b
        ConcreteClassB classB  = new ConcreteClassB();
        classB.templateMethod();
    }
}
