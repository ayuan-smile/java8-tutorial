package com.smile.designpattern.decorator;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-09-02 11:23
 */
public class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("yet another operation in concrete decorator2");
    }

    public void operation2() {
        super.operation();
        System.out.println("yet another operation2");
    }
}
