package com.taotao.cloud.goods.infrastructure.event.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.boot.ddd.model.event.BaseEvent;
import com.taotao.boot.ddd.model.event.EventPublisher;
import com.taotao.boot.ddd.model.event.EventPublisherType;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.core.RocketMQClientTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

@Slf4j
@Component
public class RocketMqEventPublisher implements EventPublisher {

	private final RocketMQClientTemplate rocketMQClientTemplate;
	private final JsonMapper jsonMapper;
	private final String topic = "OrderTopic";

	public RocketMqEventPublisher( RocketMQClientTemplate rocketMQClientTemplate,
		JsonMapper jsonMapper ) {
		this.rocketMQClientTemplate = rocketMQClientTemplate;
		this.jsonMapper = jsonMapper;
	}


	@Override
	public <ID, EVENT extends BaseEvent<ID>> void publish( EVENT event ) {
		publish(event, EventPublisherType.ROCKETMQ);
	}

	@Override
	public <ID, EVENT extends BaseEvent<ID>> void publish( EVENT event,
		EventPublisherType type ) {
		try {
			String eventJson = jsonMapper.writeValueAsString(event);

			log.debug("Publishing event {} to RocketMQ topic: {}",
				event.getClass().getSimpleName(), topic);

			rocketMQClientTemplate.send(topic,
				MessageBuilder.withPayload(eventJson)
					.setHeader("eventType", event.getClass().getName())
					.setHeader("eventId", event.getEventId())
					.build());
		} catch (Exception e) {
			log.error("Failed to publish event to RocketMQ", e);
			throw new RuntimeException("RocketMQ publish failed", e);
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
		return EventPublisherType.ROCKETMQ;
	}
}
