package vn.quang.kafka;

import java.util.Random;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import vn.quang.entity.TheNumber;

/**
 * @author Quang N. Vien {@literal <vienngocquang@gmail.com>}
 */
public class NumberProducer implements Runnable {

	@Value("${topic}")
	private String topic;
	
	@Value("${db.offset.limit}")
	private String offsetLimit;
	
	@Autowired
	private KafkaTemplate<String, TheNumber> kafkaTransporter;

	private static final Logger logger = LogManager.getLogger(NumberProducer.class);
	
	private void sendKafka(TheNumber theNumber) {
		ProducerRecord<String, TheNumber> record = new ProducerRecord<>(topic, theNumber.getId(), theNumber);
		ListenableFuture<SendResult<String, TheNumber>> future = kafkaTransporter.send(record);
		future.addCallback(new ListenableFutureCallback<SendResult<String, TheNumber>>() {
			@Override
			public void onSuccess(SendResult<String, TheNumber> result) {
				logger.info("Key: {}, Value: {}", result.getProducerRecord().key(), result.getProducerRecord().value().getValue());
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("Failed to send to Kafka bus.", ex);
			}
		});
	}
	
	@Override
	public void run() {
		for (int i=1; i<=1000; i++) {
			sendKafka(new TheNumber(String.valueOf(i), Integer.valueOf(new Random().nextInt(1000))));
		}
	}
}