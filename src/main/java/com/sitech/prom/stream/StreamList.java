package com.sitech.prom.stream;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.sitech.prom.func.Log;

public class StreamList {
	public static void main(String[] args) {
		
		Stream<String> stream = Stream.of("1","2","3");
		stream.filter(s->s.equals("2")).forEach(System.out::println);
		//stream.filter(s->s.equals("2")).forEach(s->System.out.println(s));
		
		Supplier<Stream<String>> streamSupplier = () -> Stream.of("A", "B", "C", "D");
		Optional<String> findAny = streamSupplier.get().findAny();
		findAny.ifPresent(System.out::println);
		
		streamSupplier.get().findFirst().ifPresent(Log::console);
		streamSupplier.get().findFirst().ifPresent(Log::file);
	}
}
