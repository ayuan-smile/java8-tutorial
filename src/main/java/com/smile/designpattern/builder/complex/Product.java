package com.smile.designpattern.builder.complex;

import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-30 14:59
 */
public class Product {
    private List<String> parts = new ArrayList<>();

    public void add(String part){
        parts.add(part);
    }
    public void show(){
        parts.stream().forEach(System.out::println);
    }
}
