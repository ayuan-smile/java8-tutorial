package com.smile.index;

import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-29 19:45
 */
public class StringStorage {
    public static void main(String[] args) throws InterruptedException {
        String s1 = "abc";
        String s2 = "xyz";
        String s3 = "123";
        String s4 = "A";
        String s5 = new String("abc");
        char[] c = {'J','A','V','A'};
        String s6 = new String(c);
        String s7 = new String(new StringBuffer());
        System.out.println(s1 == s5);
        //TimeUnit.SECONDS.sleep(200);

        String str1 = "a";
        String str2 = "b";
        String str3 = "ab";
        String str4 = str1 + str2;
        String str5 = new String("ab");

        System.out.println(str5.equals(str3));
        System.out.println(str5 == str3);
        System.out.println(str5.intern() == str3);
        System.out.println(str5.intern() == str4);

    }
}
