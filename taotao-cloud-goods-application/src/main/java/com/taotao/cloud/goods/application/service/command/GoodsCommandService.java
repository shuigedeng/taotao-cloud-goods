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
import com.taotao.cloud.goods.common.enums.GoodsAuthEnum;
import com.taotao.cloud.goods.common.enums.GoodsStatusEnum;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsResult;

import java.util.List;

/**
 * 商品业务层
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
     * @return {@link boolean }
     * @since 2022-04-27 17:00:15
     */
    boolean underStoreGoods( StoreIdCommand storeIdCommand);

    /**
     * 更新商品参数
     *
     * @param updateStoreParamsCommand 商品id
     * @return {@link boolean }
     * @since 2022-04-27 17:00:15
     */
    boolean updateGoodsParams( UpdateStoreParamsCommand updateStoreParamsCommand);

    /**
     * 添加商品
     *
     * @param goodsAddCmd 商品查询条件
     * @return {@link boolean }
     * @since 2022-04-27 17:00:15
     */
    boolean addGoods( SaveGoodsCommand goodsAddCmd);

    /**
     * 修改商品
     *
     * @param goodsAddCmd 商品查询条件
     * @return {@link boolean }
     * @since 2022-04-27 17:00:15
     */
    boolean editGoods( SaveGoodsCommand goodsAddCmd);

    /**
     * 批量审核商品
     *
     * @param auditGoodsCommand      商品id列表
     * @return {@link boolean }
     * @since 2022-04-27 17:00:16
     */
    boolean auditGoods( AuditGoodsCommand auditGoodsCommand );

    /**
     * 更新商品上架状态状态
     *
     * @param marketAbleGoodsCommand 更新的商品状态
     * @return {@link boolean }
     * @since 2022-04-27 17:00:16
     */
    boolean updateGoodsMarketAble( MarketAbleGoodsCommand marketAbleGoodsCommand );

    /**
     * 更新商品上架状态状态
     *
     * @param marketAbleGoodsCommand        商品ID集合
     * @return {@link boolean }
     * @since 2022-04-27 17:00:16
     */
    boolean managerUpdateGoodsMarketAble(MarketAbleGoodsCommand marketAbleGoodsCommand );

    /**
     * 删除商品
     *
     * @param goodsIdsCommand 商品ID
     * @return {@link boolean }
     * @since 2022-04-27 17:00:16
     */
    boolean deleteGoods(GoodsIdsCommand goodsIdsCommand);

    /**
     * 设置商品运费模板
     *
     * @param freightGoodsCommand   商品列表
     * @return {@link boolean }
     * @since 2022-04-27 17:00:16
     */
    boolean freight(FreightGoodsCommand freightGoodsCommand);

    /**
     * 修改商品库存数量
     *
     * @param updateStockGoodsCommand  商品ID
     * @return {@link boolean }
     * @since 2022-04-27 17:00:16
     */
    boolean updateStock(UpdateStockGoodsCommand updateStockGoodsCommand);

    /**
     * 更新商品评价数量
     *
     * @param goodsIdCommand 商品ID
     * @return {@link boolean }
     * @since 2022-04-27 17:00:16
     */
    boolean updateGoodsCommentNum(GoodsIdCommand goodsIdCommand);

    /**
     * 更新商品的购买数量
     *
     * @param updateGoodsBuyCountCommand  商品ID
     * @return {@link boolean }
     * @since 2022-04-27 17:00:16
     */
    boolean UpdateGoodsBuyCountCommand(UpdateGoodsBuyCountCommand updateGoodsBuyCountCommand);

    GoodsResult createGoods( CreateGoodsCommand goodsCreateCommand);

    void handleKafkaNotify( NotifyGoodsCommand notifyGoodsCommand );

	void scheduleAutoCreateGoods( ScheduleAutoCreateGoodsCommand scheduleAutoCreateGoodsCommand );


	/**
     * 批量更新商品的店铺信息
     *
     * @param store
     */
    // boolean updateStoreDetail(Store store);

}
