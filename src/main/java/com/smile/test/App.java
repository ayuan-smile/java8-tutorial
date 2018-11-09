package com.smile.test;

import java.util.HashMap;
import java.util.Map;

import com.smile.prm.cxf.param.Test1Exception;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		com.smile.prm.cxf.param.Test1Exception t1 = new com.smile.prm.cxf.param.Test1Exception();
		if(t1 instanceof Test1Exception){
			System.out.println(true);
		}
		System.out.println(2 << 1);
		System.out.println(2 << 2);
		System.out.println(3 << 3);
		System.out.println(1 >> 4);
		System.out.println(256 >> 4);
		
		Map<String,String> hash = new HashMap<>();
		hash.put("t1", "t101");
		hash.put("t2", "t201");
		hash.put("t3", "t301");
		System.out.println(hash.get("t1"));
	}
}

