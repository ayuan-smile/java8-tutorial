package com.sitech.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**   
 * @Title: SampleProducer
 * @Description: https://www.tutorialkart.com/apache-kafka/producer-example-in-apache-kafka/
 * @author 阿袁 yuanxm@si-tech.com.cn
 */
public class SampleProducer extends Thread{
	private KafkaProducer<Integer, String> producer ;
	private String topic;
	private Boolean isAsync;
	
	public static final String KAFKA_SERVER_URL = "172.21.11.43:9092,172.21.11.44:9092,172.21.11.45:9092";
	public static final String CLIENT_ID = "SampleProducer";
	
	public SampleProducer(String topic, Boolean isAsync) {
		Properties props = new Properties();
		props.put("bootstrap.servers", KAFKA_SERVER_URL);
		props.put("client.id", CLIENT_ID);
		props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);
		this.topic = topic;
		this.isAsync = isAsync;
	}
	
	@Override
	public void run() {
		int messageNo = 1;
		while(true){
			messageNo++;
			String messageStr = "Message_" + messageNo;
			long startTime = System.currentTimeMillis();
			if(isAsync){
				producer.send(new ProducerRecord<Integer, String>(topic, messageNo, messageStr),
						new DemoCallBack(startTime, messageNo, messageStr));
			}else{
				try {
					producer.send(new ProducerRecord<Integer, String>(topic, messageNo, messageStr)).get();
					System.out.println(String.format("send message(%s,%s)", messageNo,messageStr));
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class DemoCallBack implements Callback{
	private final long startTime;
	private final int key;
	private final String message;
	
	public DemoCallBack(long startTime, int key, String message) {
		super();
		this.startTime = startTime;
		this.key = key;
		this.message = message;
	}

	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		long elapsedTime = System.currentTimeMillis() - startTime;
		if(metadata !=null){
			System.out.println(String.format("message(%s,%s) send to partition(%s),offset(%s) in %s ms", key, message,
					metadata.partition(), metadata.offset(), elapsedTime));
		}else{
			exception.printStackTrace();
		}
	}
}


