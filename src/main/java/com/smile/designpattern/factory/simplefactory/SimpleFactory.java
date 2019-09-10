package com.smile.designpattern.factory.simplefactory;


/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 16:28
 */
public class SimpleFactory {
    public static Product produce(String type) throws Exception {
        Product product = null;
        switch (type) {
            case "A":{
                product =  new ProductA();
                break;
            }
            case "B":{
                product =  new ProductB();
                break;
            }
            case "C":{
                product =  new ProductC();
                break;
            }
            default:{
                throw new Exception("invalid type");
            }
        }
        return product;
    }
}
