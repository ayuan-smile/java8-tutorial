package com.smile.designpattern.factory.factorymethod;

import com.smile.designpattern.factory.simplefactory.Product;
import com.smile.designpattern.factory.simplefactory.ProductA;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 17:16
 */
public class ProductAFactory implements Factory {
    @Override
    public Product produce() {
        return new ProductA();
    }
}
