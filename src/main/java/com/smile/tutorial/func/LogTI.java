package com.smile.tutorial.func;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

@FunctionalInterface
public interface LogTI<T> {
	void log(T t);
	
	default void file(T str){
		Path path = Paths.get("./test2.txt");
		try {
			if(!Files.exists(path)){
				Files.createFile(path);
			}
			Files.write(path, str.toString().getBytes(), StandardOpenOption.APPEND);
			Stream<String> lines = Files.lines(path);
			lines.forEach(System.out::println);
			lines.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
