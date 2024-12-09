package com.learning.kafka_schema_registry.producer;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.learning.kafka_schema_registry.dto.Employee;

@Service
public class KafkaAvroProducer {

	@Autowired
	KafkaTemplate<String, Employee> template;

	@Value("${topic.name}")
	private String topicName;

	public void send(Employee employee) {
		CompletableFuture<SendResult<String, Employee>> future = template.send(topicName, UUID.randomUUID().toString(),
				employee);
		future.whenComplete((res, ex) -> {
			if (ex == null) {
				System.out.println(
						"Sent message=[" + employee + "] with offset=[" + res.getRecordMetadata().offset() + "]");
			} else {
				System.out.println("Unable to send message due to: " + ex.getMessage());
			}
		});
	}

}
