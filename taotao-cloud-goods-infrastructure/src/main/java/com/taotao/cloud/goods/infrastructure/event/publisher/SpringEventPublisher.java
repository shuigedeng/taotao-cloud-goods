package com.taotao.cloud.goods.infrastructure.event.publisher;

import com.taotao.boot.ddd.model.event.BaseEvent;
import com.taotao.boot.ddd.model.event.EventPublisher;
import com.taotao.boot.ddd.model.event.EventPublisherType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringEventPublisher implements EventPublisher {

	private final ApplicationEventPublisher applicationEventPublisher;

	public SpringEventPublisher( ApplicationEventPublisher applicationEventPublisher ) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public <ID, EVENT extends BaseEvent<ID>> void publish( EVENT event ) {
		log.debug("Publishing event {} to Spring ApplicationEvent", event.getClass().getSimpleName());
		applicationEventPublisher.publishEvent(event);
	}

	@Override
	public <ID, EVENT extends BaseEvent<ID>> void publish( EVENT event, EventPublisherType type ) {
		publish(event);
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
		return EventPublisherType.APPLICATION;
	}
}
