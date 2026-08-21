/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.goods.infrastructure.event.listener.spring;

import com.taotao.cloud.goods.application.dto.goods.command.GoodsCreatedHandleCommand;
import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import com.taotao.cloud.goods.domain.event.GoodsCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * AuthChangeEventListener
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@AllArgsConstructor
@Slf4j
@Component
public class AuthChangeSpringEventListener {

	private final GoodsCommandService goodsCommandService;

	@EventListener(GoodsCreatedEvent.class)
	public void handleGoodsCreatedEvent( GoodsCreatedEvent goodsCreatedEvent ){
		log.info("接受到GoodsCreatedEvent:{}", goodsCreatedEvent);
		goodsCommandService.handleGoodsCreatedEvent(GoodsCreatedHandleCommand.builder()
			.goodsCreatedEvent(goodsCreatedEvent).build());
	}
}
