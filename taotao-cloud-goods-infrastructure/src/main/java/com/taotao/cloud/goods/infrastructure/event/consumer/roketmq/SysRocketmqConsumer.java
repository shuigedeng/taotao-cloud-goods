package com.taotao.cloud.goods.infrastructure.event.consumer.roketmq;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

//@Component
//@RocketMQMessageListener(
//	topic = "${taotao.data.rocketmq.member-topic}",
//	selectorExpression = " res || xx",
//	consumerGroup = "${taotao.data.rocketmq.member-group}",
//	consumeMode = ConsumeMode.ORDERLY,
//	messageModel = MessageModel.BROADCASTING
//)
public class SysRocketmqConsumer implements RocketMQListener<MessageExt> {

	//没有抛异常 自动确认
	//抛异常
	@Override
	public void onMessage( MessageExt message ) {
		try {
			String s = new String(message.getBody(), StandardCharsets.UTF_8);
			//手动确认
		} catch (Exception e) {

		}
	}


}
