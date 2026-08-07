package com.taotao.cloud.goods.infrastructure.event.listener.redis;

import org.springframework.data.redis.annotation.RedisListener;
import org.springframework.data.redis.connection.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RedisNotificationListener {

	// 监听 "orders" 频道，自动将 JSON 消息反序列化为 Order 对象
//	@RedisListener(topics = "orders", consumes = "application/json")
//	public void handleOrder(Order order) {
//		System.out.println("收到新订单: " + order.id());
//		// 处理订单业务逻辑
//	}

//	@RedisListener(topic = "user-events")
//	public void handleUserEvent(@Payload UserEvent event,
//		@Header("topic") String topic,
//		@Header("pattern") String pattern) {
//		System.out.printf("从频道 %s 接收到用户事件: %s%n", topic, event);
//	}

    @RedisListener(topic = "notifications:user-events")
    public void handleUserEvent(String message) {
        // 不需要手动创建 MessageListenerContainer
        // 框架自动装配并管理生命周期
//        UserEvent event = objectMapper.readValue(message, UserEvent.class);
//        log.info("Received user event: {}", event);
    }

    @RedisListener(topic = "notifications:system-alerts")
    public void handleSystemAlert( Message message) {
        // 也可以通过 RedisSerializer 自定义序列化
    }
}
