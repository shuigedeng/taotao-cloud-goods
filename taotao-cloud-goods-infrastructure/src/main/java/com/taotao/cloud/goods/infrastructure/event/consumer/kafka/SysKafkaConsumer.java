package com.taotao.cloud.goods.infrastructure.event.consumer.kafka;

import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.mq.common.consumer.Acknowledgement;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

//@Component
public class SysKafkaConsumer {

	@KafkaListener(
		topics = "xxx",
		groupId = "xx-xxx-xx",
		concurrency = "3",
		errorHandler = "kafkaErrorHandler",
		containerFactory = "manualKafkaContainerFactory"
	)
	public void listenMsg( String msg,
		@Header(KafkaHeaders.RECEIVED_KEY) String key,
		Acknowledgement ack ) {

		try {
			//手动确认
			ack.acknowledge();
		} catch (Exception e) {
			//不确认 会自动重试
			LogUtils.info("asfd");
		}
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> manualKafkaContainerFactory(
		ConsumerFactory<String, String> consumerFactory ) {
		ConcurrentKafkaListenerContainerFactory<String, String> container = new ConcurrentKafkaListenerContainerFactory<>();
		container.setConsumerFactory(consumerFactory);
		container.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
		return container;
	}

	@Bean
	public ConsumerAwareListenerErrorHandler kafkaErrorHandler() {
		return ( message, exception, consumer ) -> {
			//可以选择重试 进入死信队列
			return null;
		};
	}
}
