package com.smile.index;

import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-29 20:32
 */
public class StringOOM {
    static String base = "string";

    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }
}
