package com.smile.designpattern.adapter.association;

import com.smile.designpattern.adapter.implement.Adaptee;
import com.smile.designpattern.adapter.implement.Target;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-09-02 14:01
 */
public class Adapter implements Target{
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void operation() {
        adaptee.specialOperation();
    }
}
