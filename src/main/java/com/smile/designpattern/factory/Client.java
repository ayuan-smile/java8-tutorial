package com.smile.designpattern.factory;

import com.smile.designpattern.factory.abstractfactory.ConcreteFactory1;
import com.smile.designpattern.factory.factorymethod.Factory;
import com.smile.designpattern.factory.factorymethod.ProductAFactory;
import com.smile.designpattern.factory.simplefactory.SimpleFactory;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 16:38
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 简单工厂
        System.out.println(SimpleFactory.produce("A"));
        System.out.println(SimpleFactory.produce("B"));
        System.out.println(SimpleFactory.produce("C"));

        // 工厂方法
        Factory factory = new ProductAFactory();
        System.out.println(factory.produce());

        // 抽象工厂
        com.smile.designpattern.factory.abstractfactory.Factory factory1 = new ConcreteFactory1();
        factory1.produceA();
        factory1.produceB();

    }
}
