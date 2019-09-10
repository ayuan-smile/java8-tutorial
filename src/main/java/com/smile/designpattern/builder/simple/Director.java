package com.smile.designpattern.builder.simple;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 15:02
 */
public class Director {
    public void build(Builder builder){
        builder.buildPartA();
        builder.buildPartB();
    }
}
