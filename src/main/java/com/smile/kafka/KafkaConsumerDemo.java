package com.smile.kafka;

public class KafkaConsumerDemo {
	public static void main(String[] args) {
		SampleConsumer consumerThread = new SampleConsumer("test");
        consumerThread.start();
	}
}
