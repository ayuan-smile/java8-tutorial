package com.smile.designpattern.builder.simple;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 15:18
 */
public class Client {
    public static void main(String[] args) {
        // 声明指挥者
        Director director = new Director();

        //建造1类型
        Builder builder1 = new ConcreteBuilderA();
        // 还需要别人告诉builder建造
        director.build(builder1);
        Product p1 = builder1.getResult();
        p1.show();

        // 建造2类型
        Builder builder2 = new ConcreteBuilderB();
        director.build(builder2);
        builder2.getResult().show();
    }
}
