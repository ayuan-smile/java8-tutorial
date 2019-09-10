package com.smile.designpattern.adapter;

import com.smile.designpattern.adapter.implement.Adaptee;
import com.smile.designpattern.adapter.implement.Adapter;
import com.smile.designpattern.adapter.implement.ConcreteTarget;
import com.smile.designpattern.adapter.implement.Target;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-09-02 13:45
 */
public class Client {
    public static void main(String[] args) {
        /**
         * 1、实现接口方式
         */
        // 正常接口
        System.out.println("implement type");
        Target target = new ConcreteTarget();
        target.operation();
        // 使用适配器，生成适配子类
        Target adapter = new Adapter();
        adapter.operation();

        System.out.println("\nassociation type");
        /**
         * 2、对象组合方式
         */
        Target adapter1 = new com.smile.designpattern.adapter.association.Adapter(new Adaptee());
        adapter1.operation();

    }
}
