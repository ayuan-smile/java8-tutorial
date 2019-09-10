package com.smile.designpattern.builder.complex;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 14:17
 */
public abstract class Builder {
    public void build(Builder builder){
        builder.buildPartA();
        builder.buildPartB();
    }

    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract Product getResult();
}
