package com.smile.designpattern.builder.complex;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 15:07
 */
public class ConcreteBuilderA extends Builder {
    private Product product = new Product();
    @Override
    public void buildPartA() {
        product.add("part-a1");
    }

    @Override
    public void buildPartB() {
        product.add("part-b1");
    }

    @Override
    public Product getResult() {
        return product;
    }
}
