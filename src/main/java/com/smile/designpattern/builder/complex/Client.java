package com.smile.designpattern.builder.complex;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 15:18
 */
public class Client {
    public static void main(String[] args) {
        //建造1类型
        Builder builder1 = new ConcreteBuilderA();
        // 还需要别人告诉builder建造
        builder1.build(builder1);
        builder1.getResult().show();

        // 建造2类型
        Builder builder2 = new ConcreteBuilderB();
        builder2.build(builder2);
        builder2.getResult().show();
    }
}
