package com.smile.designpattern.adapter.implement;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-09-02 14:01
 */
public class Adapter extends Adaptee implements Target{
    @Override
    public void operation() {
        super.specialOperation();
    }
}
