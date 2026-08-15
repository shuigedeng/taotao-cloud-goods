package com.taotao.cloud.goods.infrastructure.event.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.boot.ddd.model.event.BaseEvent;
import com.taotao.boot.ddd.model.event.EventPublisher;
import com.taotao.boot.ddd.model.event.EventPublisherType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

//@Component
@Slf4j
public class KafkaEventPublisher implements EventPublisher {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;
	private final String topic = "domain-events";

	public KafkaEventPublisher( KafkaTemplate<String, String> kafkaTemplate,
		ObjectMapper objectMapper ) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}


	@Override
	public <ID, EVENT extends BaseEvent<ID>> void publish( EVENT event ) {
		publish(event, EventPublisherType.KAFKA);
	}

	@Override
	public <ID, EVENT extends BaseEvent<ID>> void publish( EVENT event, EventPublisherType type ) {
		try {
			String eventJson = objectMapper.writeValueAsString(event);
			String key = event.getEventId();

			log.debug("Publishing event {} to Kafka topic: {}", event.getClass().getSimpleName(), topic);
			kafkaTemplate.send(topic, key, eventJson);
		} catch (Exception e) {
			log.error("Failed to publish event to Kafka", e);
			throw new RuntimeException("Kafka publish failed", e);
		}
	}

	@Override
	public <ID, EVENT extends BaseEvent<ID>> void publishAndSave( EVENT event ) {

	}

	@Override
	public boolean supports( EventPublisherType type ) {
		return true;
	}

	@Override
	public EventPublisherType getType() {
		return EventPublisherType.KAFKA;
	}
}
