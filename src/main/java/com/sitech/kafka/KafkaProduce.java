package com.sitech.kafka;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;

public class KafkaProduce {

	private static Properties properties;
	
	static{
		String path = KafkaProducer.class.getResource("/").getFile().toString()+"kafka.properties";
		try {
			FileInputStream fis = new FileInputStream(path);
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
