package com.sitech.prom.generate;

import java.util.ArrayList;
import java.util.List;

public class GenMethod {
	public <T> void printType(T t){
		System.out.println(t.getClass().getName());
	}
	
	public static void main(String[] args) {
		GenMethod gen = new GenMethod();
		gen.printType("");
		gen.printType(12);
		gen.printType(12.2);
		
		List<?> list = new ArrayList<String>();
		
	}
}
