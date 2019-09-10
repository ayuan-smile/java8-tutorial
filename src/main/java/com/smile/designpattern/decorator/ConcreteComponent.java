package com.smile.designpattern.decorator;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-09-02 11:13
 */
public class ConcreteComponent implements Component{
    @Override
    public void operation() {
        System.out.println("concrete component operation ");
    }
}
