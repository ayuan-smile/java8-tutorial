package com.smile.designpattern.factory.abstractfactory;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 20:08
 */
public class ConcreteFactory1 implements Factory {
    @Override
    public ProductA produceA() {
        return new ProductA1();
    }

    @Override
    public ProductB produceB() {
        return new ProductB1();
    }
}
