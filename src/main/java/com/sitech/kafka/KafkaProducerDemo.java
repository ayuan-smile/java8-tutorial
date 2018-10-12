package com.sitech.kafka;

public class KafkaProducerDemo {
	private static final String TOPIC = "test";
	
	public static void main(String[] args) {
		SampleProducer producerThread = new SampleProducer(TOPIC, true);
		producerThread.start();
	}
}
