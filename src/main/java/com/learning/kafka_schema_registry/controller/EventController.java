package com.learning.kafka_schema_registry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.kafka_schema_registry.dto.Employee;
import com.learning.kafka_schema_registry.producer.KafkaAvroProducer;

@RestController
public class EventController {

	@Autowired
	KafkaAvroProducer producer;

	@PostMapping("/events")
	public String sendMessage(@RequestBody Employee employee) {
		producer.send(employee);
		return "Message published";
	}

}
