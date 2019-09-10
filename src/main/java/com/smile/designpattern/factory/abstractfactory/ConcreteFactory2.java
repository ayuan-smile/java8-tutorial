package com.smile.designpattern.factory.abstractfactory;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 20:17
 */
public class ConcreteFactory2 implements Factory {
    @Override
    public ProductA produceA() {
        return new ProductA2();
    }

    @Override
    public ProductB produceB() {
        return new ProductB2();
    }
}
