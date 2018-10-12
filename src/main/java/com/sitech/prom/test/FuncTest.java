package com.sitech.prom.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import com.sitech.prom.func.Log;

public class FuncTest<T> {
	public void forEachList(List<T> lt,Consumer<T> c){
		//lt.forEach(c);
		for (T t : lt) {
			c.accept(t);
		}
	}
	
	public static void main(String[] args) {
		FuncTest<String> fc = new FuncTest<>();
		fc.forEachList(Arrays.asList("1","2","3"), System.out::println);
		fc.forEachList(Arrays.asList("1","2","3"), Log::console);
		
		
	}
}
