package com.taotao.cloud.goods.infrastructure.event.consumer.roketmq;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.mq.common.base.MqConsumerBase;
import com.taotao.cloud.goods.application.dto.goods.command.NotifyGoodsCommand;
import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.client.annotation.RocketMQMessageListener;
import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import org.apache.rocketmq.client.apis.message.MessageView;
import org.apache.rocketmq.client.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RocketMQMessageListener(
	consumerGroup = "springboot_consumer_group"
)
@AllArgsConstructor
public class GoodsRocketmqConsumer extends MqConsumerBase implements RocketMQListener {
	private final GoodsCommandService goodsCommandService;

	@Override
	public ConsumeResult consume( MessageView messageView ) {
		try {
			String msg = StandardCharsets.UTF_8.decode(messageView.getBody()).toString();

			NotifyGoodsCommand notifyGoodsCommand = from(msg, NotifyGoodsCommand.class);

			goodsCommandService.handleKafkaNotify(notifyGoodsCommand);
			//手动确认
		} catch (Exception e) {

		}
		return ConsumeResult.SUCCESS;
	}
}
