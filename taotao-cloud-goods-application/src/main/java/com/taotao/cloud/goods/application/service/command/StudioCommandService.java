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

package com.taotao.cloud.goods.application.service.command;

import com.taotao.boot.ddd.model.application.service.CommandService;
import com.taotao.cloud.stream.framework.trigger.message.BroadcastMessage;

/**
 * 直播间业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:01:11
 */
public interface StudioCommandService extends CommandService {

	/**
	 * 创建直播间 直播间默认手机直播 默认开启：点赞、商品货架、评论、回放
	 *
	 * @param studioPO 直播间
	 * @return {@link boolean }
	 * @since 2022-04-27 17:01:11
	 */
//    boolean create(StudioPO studioPO);

	/**
	 * 修改直播间 直播间默认手机直播
	 *
	 * @param studioPO 直播间
	 * @return {@link boolean }
	 * @since 2022-04-27 17:01:11
	 */
//    boolean edit(StudioPO studioPO);


	/**
	 * 推送商品
	 *
	 * @param roomId  房间ID
	 * @param goodsId 商品ID
	 * @param storeId 店铺ID
	 * @return {@link boolean }
	 * @since 2022-04-27 17:01:11
	 */
	boolean push(Integer roomId, Long goodsId, Long storeId);

	/**
	 * 删除商品
	 *
	 * @param roomId  店铺ID
	 * @param goodsId 商品ID
	 * @param storeId
	 * @return {@link boolean }
	 * @since 2022-04-27 17:01:11
	 */
	boolean goodsDeleteInRoom(Integer roomId, Long goodsId, Long storeId);

	/**
	 * 修改直播间状态
	 *
	 * @param broadcastMessage 直播间消息
	 * @return {@link boolean }
	 * @since 2022-04-27 17:01:12
	 */
	boolean updateStudioStatus(BroadcastMessage broadcastMessage);
}
