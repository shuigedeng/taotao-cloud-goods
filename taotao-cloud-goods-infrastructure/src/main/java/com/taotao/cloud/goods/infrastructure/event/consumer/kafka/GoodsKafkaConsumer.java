package com.taotao.cloud.goods.infrastructure.event.consumer.kafka;

import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.mq.common.base.MqConsumerBase;
import com.taotao.cloud.goods.application.dto.goods.command.NotifyGoodsCommand;
import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GoodsKafkaConsumer extends MqConsumerBase {

	private final GoodsCommandService goodsCommandService;

	@KafkaListener(
		topics = "GoodsTopic",
		groupId = "taotao-cloud-GoodsTopic",
		concurrency = "3"
//		errorHandler = "kafkaErrorHandler",
//		containerFactory = "manualKafkaContainerFactory"
	)

	/**
	 * 列表查询
	 *
	 * @param RECEIVED_TOPIC received_topic
	 * @since 2022.03
	 */
	public void listenMsg(
//		@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
//		@Header(KafkaHeaders.RECEIVED_KEY) String key,
//		@Payload String msg,
		ConsumerRecord<String, String> record,
		Acknowledgment ack ) {

		try {
			String msg = record.value();
			NotifyGoodsCommand notifyGoodsCommand = from(msg, NotifyGoodsCommand.class);

			goodsCommandService.handleKafkaNotify(notifyGoodsCommand);

			//手动确认
			ack.acknowledge();
		} catch (Exception e) {
			//不确认 会自动重试
			LogUtils.info("asfd");
		}
	}

//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, String> manualKafkaContainerFactory(
//		ConsumerFactory<String, String> consumerFactory ) {
//		ConcurrentKafkaListenerContainerFactory<String, String> container = new ConcurrentKafkaListenerContainerFactory<>();
//		container.setConsumerFactory(consumerFactory);
//		container.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
//		return container;
//	}

//	@Bean
//	public ConsumerAwareListenerErrorHandler kafkaErrorHandler() {
//		return ( message, exception, consumer ) -> {
//			//可以选择重试 进入死信队列
//			return null;
//		};
//	}

}
