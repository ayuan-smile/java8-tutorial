package com.smile.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MapTest {
    public static void main(String[] args) {

        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("t1","11");
        linkedHashMap.put("t2","22");
        linkedHashMap.put("t3","33");
        linkedHashMap.put("t4","44");
        linkedHashMap.entrySet().forEach((s)->System.out.println(s));

        HashMap map = new HashMap();
        map.put("t1","11");
        map.put("t2","22");
        map.put("t3","33");
        map.put("t4","44");
        map.entrySet().forEach((s)->System.out.println(s));

        ArrayList<String> list = new ArrayList();
        list.add(0,"12");
        //list.remove(1);
        list.forEach((s)->System.out.println(s));

        System.out.println(3 >> 1);
    }
}
