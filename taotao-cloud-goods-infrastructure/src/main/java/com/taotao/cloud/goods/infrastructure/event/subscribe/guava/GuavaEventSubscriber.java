package com.taotao.cloud.goods.infrastructure.event.subscribe.guava;

import com.google.common.eventbus.Subscribe;
import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.eventbus.model.EventModel;

public class GuavaEventSubscriber {
	@Subscribe

	/**
	 * onMessage 方法
	 *
	 * @param message 消息
	 * @return 无返回值
	 * @since 2022.03
	 */
	public void onMessage( EventModel<?> message) {
		LogUtils.info("收到消息：{}", message);
	}
}
