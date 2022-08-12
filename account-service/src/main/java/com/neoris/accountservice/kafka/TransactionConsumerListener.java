package com.neoris.accountservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.accountservice.dto.request.TransactionRequest;
import com.neoris.accountservice.service.IAccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TransactionConsumerListener implements AcknowledgingMessageListener<Integer, String> {

	@Autowired
	private IAccountService accountService;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	@KafkaListener(topics = "${spring.kafka.template.default-topic}")
	public void onMessage(ConsumerRecord<Integer, String> data, Acknowledgment acknowledgment) {

		try {

			log.info("****************************************************************");
			log.info("Consumer Receives in Microservice Account");
			log.info("ConsumerRecord : {}", data.value());

			TransactionRequest transaction = objectMapper.readValue(data.value(), TransactionRequest.class);

			accountService.applyTransaction(transaction);

			/** Commit kafka */
			acknowledgment.acknowledge();

		} catch (Exception ex) {
			log.error("An error occurred while trying to asynchronously process the transaction: {}", ex.getMessage());
		}
	}
}
