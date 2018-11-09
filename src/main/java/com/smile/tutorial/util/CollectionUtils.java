package com.smile.tutorial.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class CollectionUtils {

	public static boolean isNullOrEmpty(Collection<?> collect){
		return collect==null || collect.isEmpty();
	}
	
	public static boolean isNullOrEmpty(Map<?, ?> map){
		return null == map || map.isEmpty();
	}
	
	public static boolean isNullOrEmpty(Object[] objs){
		return null == objs || objs.length == 0;
	}
	
	public <E> void foreach(E[] arrs,BiConsumer<Integer,E> biFunc){
		if(isNullOrEmpty(arrs)){
			return;
		}
		for (int i = 0; i < arrs.length; i++) {
			biFunc.accept(i, arrs[i]);
		}
	}
	
	public static <T,K> T findByMatch(List<T> res,List<K> target,BiFunction<T,K,Boolean> compartor){
		for (T t : res) {
			for (K k : target) {
				if(compartor.apply(t, k)){
					return t;
				}
			}
		}
		return null;
	}
	
	public static <T,K> List<T> findByMatchList(List<T> res,List<K> target,BiFunction<T,K,Boolean> compartor){
		return res.stream().filter((a) -> target.stream().anyMatch((b) -> a.equals(b))).collect(Collectors.toList());
	}
	
	
	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("1","2","3");
		List<String> list2 = Arrays.asList("3","2","5");
		String findByMatch = CollectionUtils.findByMatch(list1, list2, (a,b)->a.equals(b));
		//System.out.println(findByMatch);
		/*
		list1.stream().filter(new Predicate<String>() {
			@Override
			public boolean test(String a) {
				return list2.stream().anyMatch((b)->a.equals(b));
			}
		}).findFirst().ifPresent(System.out::println);
		*/
		//list1.stream().filter((a) -> list2.stream().anyMatch((b) -> a.equals(b))).forEach(System.out::println);
		
		System.out.println(CollectionUtils.findByMatchList(list1, list2, (a,b)->a.equals(b)));
		
		ArrayList<String> list3 = new ArrayList<>(list1);
		org.apache.commons.collections.CollectionUtils.filter(list3, (a)->a.equals("1"));
		System.out.println(list3);
		
		Map map = new HashMap<>();
		System.out.println(CollectionUtils.isNullOrEmpty(map));
	}
}
