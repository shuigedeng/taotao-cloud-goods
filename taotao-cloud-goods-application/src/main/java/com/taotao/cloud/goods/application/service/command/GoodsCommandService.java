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
import com.taotao.cloud.goods.application.dto.goods.command.*;
import com.taotao.cloud.goods.application.dto.store.command.StoreIdCommand;
import com.taotao.cloud.goods.application.dto.store.command.UpdateStoreParamsCommand;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsResult;

/**
 * 商品命令服务接口
 * <p>
 * 定义商品的写操作，包括商品的创建、修改、上架、下架、删除等业务逻辑
 * </p>
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:00:15
 */
public interface GoodsCommandService extends CommandService {

    /**
     * 下架所有商家商品
     *
     * @param storeIdCommand 店铺ID
     * @since 2022-04-27 17:00:15
     */
    void underStoreGoods( StoreIdCommand storeIdCommand);

    /**
     * 更新商品参数
     *
     * @param updateStoreParamsCommand 商品id
     * @since 2022-04-27 17:00:15
     */
    void updateGoodsParams( UpdateStoreParamsCommand updateStoreParamsCommand);

    /**
     * 添加商品
     *
     * @param goodsAddCmd 商品查询条件
     * @since 2022-04-27 17:00:15
     */
    void addGoods( SaveGoodsCommand goodsAddCmd);

    /**
     * 修改商品
     *
     * @param goodsAddCmd 商品查询条件
     * @since 2022-04-27 17:00:15
     */
    void editGoods( SaveGoodsCommand goodsAddCmd);

    /**
	 * 批量审核商品
	 *
	 * @param auditGoodsCommand 商品id列表
	 * @since 2022-04-27 17:00:16
	 */
    void auditGoods( AuditGoodsCommand auditGoodsCommand );

    /**
     * 更新商品上架状态状态
     *
     * @param marketAbleGoodsCommand 更新的商品状态
     * @since 2022-04-27 17:00:16
     */
    void updateGoodsMarketAble( MarketAbleGoodsCommand marketAbleGoodsCommand );
//
//    /**
//	 * 更新商品上架状态状态
//	 *
//	 * @param marketAbleGoodsCommand 商品ID集合
//	 * @since 2022-04-27 17:00:16
//	 */
//    void updateGoodsMarketAble(MarketAbleGoodsCommand marketAbleGoodsCommand );

    /**
     * 删除商品
     *
     * @param goodsIdsCommand 商品ID
     * @since 2022-04-27 17:00:16
     */
    void deleteGoods(GoodsIdsCommand goodsIdsCommand);

    /**
	 * 设置商品运费模板
	 *
	 * @param freightGoodsCommand 商品列表
	 * @since 2022-04-27 17:00:16
	 */
    void freight(FreightGoodsCommand freightGoodsCommand);

    /**
	 * 修改商品库存数量
	 *
	 * @param updateStockGoodsCommand 商品ID
	 * @since 2022-04-27 17:00:16
	 */

    void updateStock(UpdateStockGoodsCommand updateStockGoodsCommand);

    /**
     * 更新商品评价数量
     *
     * @param goodsIdCommand 商品ID
     * @since 2022-04-27 17:00:16
     */
    void updateGoodsCommentNum(GoodsIdCommand goodsIdCommand);

    /**
	 * 更新商品的购买数量
	 *
	 * @param updateGoodsBuyCountCommand 商品ID
	 * @since 2022-04-27 17:00:16
	 */
    void updateGoodsBuyCount(UpdateGoodsBuyCountCommand updateGoodsBuyCountCommand);

    GoodsResult createGoods( CreateGoodsCommand goodsCreateCommand);



    void handleKafkaNotify( NotifyGoodsCommand notifyGoodsCommand );

	void scheduleAutoCreateGoods( ScheduleAutoCreateGoodsCommand scheduleAutoCreateGoodsCommand );

	void handleGoodsCreatedEvent( GoodsCreatedHandleCommand build );

	/**
     * 批量更新商品的店铺信息
     *
     * @param store
     */
    // boolean updateStoreDetail(Store store);

}
