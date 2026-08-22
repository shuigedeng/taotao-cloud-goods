package com.taotao.cloud.goods.infrastructure.event.subscribe.guava;

import com.google.common.eventbus.Subscribe;
import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.eventbus.model.EventModel;

public class GuavaEventSubscriber {

	/**
	 * onMessage 方法
	 *
	 * @param message 消息
	 * @since 2022.03
	 */
	@Subscribe
	public void onMessage( EventModel<?> message) {
		LogUtils.info("收到消息：{}", message);
	}
}

