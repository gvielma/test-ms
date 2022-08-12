package com.neoris.transactionservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * The Class TopicConfig.
 */
@Configuration
public class TopicConfig {

	/** The topic name. */
	@Value("${spring.kafka.template.default-topic}")
	String topicName;

	/**
	 * Deposit event.
	 *
	 * @return the new topic
	 */
	@Bean
	public NewTopic depositEvent() {

		return TopicBuilder.name(topicName).partitions(3).replicas(1).build();
	}
}
