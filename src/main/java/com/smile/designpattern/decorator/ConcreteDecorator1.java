package com.smile.designpattern.decorator;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-09-02 11:18
 */
public class ConcreteDecorator1 extends Decorator {
    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("yet another operation in concrete decorator1");
    }

    public void operation1() {
        System.out.println("yet another method operation1");
    }
}
