package com.taotao.cloud.goods.infrastructure.event.consumer.roketmq;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.mq.common.base.MqConsumerBase;
import com.taotao.cloud.goods.application.dto.goods.command.NotifyGoodsCommand;
import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQListener;

import java.nio.charset.StandardCharsets;

//@Component
//@RocketMQMessageListener(
//	topic = "${taotao.data.rocketmq.member-topic}",
//	selectorExpression = " res || xx",
//	consumerGroup = "${taotao.data.rocketmq.member-group}",
//	consumeMode = ConsumeMode.ORDERLY,
//	messageModel = MessageModel.BROADCASTING
//)
@AllArgsConstructor
public class GoodsRocketmqConsumer extends MqConsumerBase implements RocketMQListener<MessageExt> {
	private final GoodsCommandService goodsCommandService;
	//没有抛异常 自动确认
	//抛异常
	@Override
	public void onMessage( MessageExt message ) {
		try {
			String msg = new String(message.getBody(), StandardCharsets.UTF_8);

			NotifyGoodsCommand notifyGoodsCommand = from(msg, NotifyGoodsCommand.class);

			goodsCommandService.handleKafkaNotify(notifyGoodsCommand);
			//手动确认
		} catch (Exception e) {

		}
	}

}
