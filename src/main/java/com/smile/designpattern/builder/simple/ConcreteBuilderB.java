package com.smile.designpattern.builder.simple;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 15:12
 */
public class ConcreteBuilderB extends Builder {
    private Product product = new Product();
    @Override
    public void buildPartA() {
        product.add("part-a2");
    }

    @Override
    public void buildPartB() {
        product.add("part-a2");
    }

    @Override
    public Product getResult() {
        return product;
    }
}
