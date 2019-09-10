package com.smile.designpattern.decorator;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-09-02 11:26
 */
public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();

        // 增强原有的方法，并制定方法增强顺序
        Decorator decorator = new ConcreteDecorator1(new ConcreteDecorator2(component));
        decorator.operation();

        // 声明为父类
        Component decorator1 = new ConcreteDecorator2(new ConcreteDecorator1(component));
        decorator1.operation();

        // 增加新的方法,方法在子类，需要使用子类。或者，也可以将方法添加到父类中，这样子类就必须实现父类的所有方法，不太灵活
        ConcreteDecorator1 concreteDecorator1 = new ConcreteDecorator1(component);
        concreteDecorator1.operation1();
    }
}
