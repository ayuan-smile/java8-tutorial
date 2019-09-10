package com.smile.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-09-06 14:42
 */
public class Client{
    public static void main(String[] args) {
        InvocationHandler invocationHandler = new SubjectLogProxy(new ConcreteSubject());
        ClassLoader classLoader = Subject.class.getClassLoader();
        Subject instance = (Subject)Proxy.newProxyInstance(classLoader, new Class[]{Subject.class}, invocationHandler);
        instance.print();
        System.out.println(instance.toString());
        instance.getClass().getDeclaredFields();
        instance.getClass().getMethods();
    }
}
