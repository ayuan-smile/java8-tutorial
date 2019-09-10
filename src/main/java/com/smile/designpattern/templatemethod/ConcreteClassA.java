package com.smile.designpattern.templatemethod;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 15:54
 */
public class ConcreteClassA extends AbstractTemplate{
    @Override
    public void method1() {
        System.out.println("execute method1 in concrete class a");
    }

    @Override
    public void method2() {
        System.out.println("execute method2 in concrete class a");
    }
}
