package com.smile.designpattern.templatemethod;

import java.lang.invoke.MethodHandle;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 15:43
 */
public abstract class AbstractTemplate {
    public void templateMethod(){
        System.out.println("开始执行各种方法");
        method1();
        method2();
        System.out.println("结束执行各种方法");
    }

    public abstract void method1();
    public abstract void method2();
}
