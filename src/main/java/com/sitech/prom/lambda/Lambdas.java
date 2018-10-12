package com.sitech.prom.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.sitech.prom.func.Log;
import com.sitech.prom.func.LogTI;

public class Lambdas {
	
	public Apple newApple(int weight, String color){
		return new Apple(weight, color);
	}
	
	public static void main(String[] args) {
		Runnable r = ()-> System.out.println("22");
		r.run();
		//直接调用报错：No enclosing instance of type E is accessible. Must qualify the allocation with an enclosing
		//http://blog.csdn.net/sunny2038/article/details/6926079
		//List<Apple> asList = Arrays.asList(new Apple(10, "red"));
		Lambdas lambda = new Lambdas();
		List<Apple> appList = Arrays.asList(lambda.newApple(10, "red"),lambda.newApple(12, "red"),lambda.newApple(8, "blue"));
		List<Apple> filter = filter(appList,a->a.getWeight()>10);
		filter.forEach(System.out::println);
		filter(appList,a->a.getColor().equals("red")).forEach(System.out::println);;
		List<Apple> apps = filter(appList,a->a.getColor().equals("red"));
		//apps.stream().map(Apple::toString).forEach(Log::file);
		System.out.println("\t");
		apps.forEach(Log::console);
		System.out.println("\t");
		//apps.forEach(LogTI::console);
		
		/*
		appList.sort((a1,a2)->a1.getWeight()-a2.getWeight());
		appList.forEach(System.out::println);
		
		//方法引用倒叙
		appList.sort(Comparator.comparing(Apple::getWeight).reversed());
		appList.forEach(System.out::println);
		*/
	}
	
	public static List<Apple> filter(List<Apple> list,Predicate<Apple> pre){
		List<Apple> newApples = new ArrayList<>();
		for (Apple apple : list) {
			if(pre.test(apple)){
				newApples.add(apple);
			}
		}
		return newApples;
	}
	
	class Apple{
		private int weight = 0;
		private String color = "";

		public Apple(int weight, String color){
			this.weight = weight;
			this.color = color;
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String toString() {
			return "Apple{" +
					"color='" + color + '\'' +
					", weight=" + weight +
					'}';
		}
	}
	
	interface PredicateApple{
		public boolean test(Apple ap);
	}
}
