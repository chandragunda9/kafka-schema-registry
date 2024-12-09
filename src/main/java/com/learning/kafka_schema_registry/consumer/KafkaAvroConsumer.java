package com.learning.kafka_schema_registry.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.learning.kafka_schema_registry.dto.Employee;

@Service
public class KafkaAvroConsumer {

	Logger logger = LoggerFactory.getLogger(getClass());

	@KafkaListener(topics = "${topic.name}"
//			,containerFactory = "containerFactory"
			)
	public void consume(ConsumerRecord<String, Employee> consumerRecord) {
		String key = consumerRecord.key();
		Employee emp = (Employee) consumerRecord.value();

		logger.info("Avro message received. key={} and value={}", key, emp.toString());
	}

}
