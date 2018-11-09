package com.smile.thread.synchronize;

import com.smile.thread.entity.Person;
import com.smile.thread.entity.ThreadP;

public class SynchronizedTest {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
        for (int i = 0; i < 5; i++) {
			/*Thread t = new Thread(()->{
				try {
					p.work();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});*/
            Thread t = new ThreadP(p);
            t.start();
        }

        Thread.sleep(100l);
//		System.out.println(p.count);
        System.out.println(p.acount.get());
    }
}
