package com.smile.designpattern.adapter.implement;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-09-02 14:03
 */
public class ConcreteTarget implements Target{
    @Override
    public void operation() {
        System.out.println("normal concrete class,have normal operation");
    }
}
