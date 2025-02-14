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

package com.taotao.cloud.goods.application.service;

import com.taotao.boot.ddd.model.application.service.CommandService;
import com.taotao.boot.webagg.service.BaseSuperService;
import com.taotao.cloud.goods.application.dto.goods.cmmond.GoodsSkuStockUpdateCmd;

import java.util.List;

/**
 * 商品sku业务层
 *
 * @author shuigedeng
 * @version 2023.07
 * @see BaseSuperService
 * @since 2023-08-18 16:00:58
 */
public interface GoodsSkuCommandService extends CommandService {
	///**
	// * 获取商品SKU缓存ID
	// *
	// * @param id SkuId
	// * @return {@link String }
	// * @since 2023-08-18 16:00:58
	// */
	//default String getCacheKeys(Long id) {
	//	return CachePrefix.GOODS_SKU.getPrefix() + id;
	//}
	//
	///**
	// * 获取商品SKU库存缓存ID
	// *
	// * @param id SkuId
	// * @return {@link String }
	// * @since 2023-08-18 16:00:58
	// */
	//default String getStockCacheKey(Long id) {
	//	return CachePrefix.SKU_STOCK.getPrefix() + id;
	//}

	/**
	 * 添加商品sku
	 *
	 * @param goods             商品信息
	 * @param goodsAddCmd 商品操作信息
	 * @since 2023-08-18 16:00:59
	 */
//	boolean add(GoodsPO goods, GoodsAddCmd goodsAddCmd);
//
//	/**
//	 * 更新商品sku
//	 *
//	 * @param goods             商品信息
//	 * @param goodsAddCmd 商品操作信息
//	 * @since 2023-08-18 16:00:59
//	 */
//	boolean update(GoodsPO goods, GoodsAddCmd goodsAddCmd);
//
//	/**
//	 * 更新商品sku
//	 *
//	 * @param goodsSkuPO sku信息
//	 * @since 2023-08-18 16:00:59
//	 */
//	boolean update(GoodsSkuPO goodsSkuPO);

	/**
	 * 清除sku缓存
	 *
	 * @param skuId skuid
	 * @since 2023-08-18 16:00:59
	 */
	boolean clearCache(Long skuId);


	/**
	 * 更新商品sku状态
	 *
	 * @param goods 商品信息(Id,MarketEnable/AuthFlag)
	 * @since 2023-08-18 16:00:59
	 */
//	boolean updateGoodsSkuStatus(GoodsPO goods);

	/**
	 * 更新商品sku状态根据店铺id
	 *
	 * @param storeId      店铺id
	 * @param marketEnable 市场启用状态
	 * @param authFlag     审核状态
	 * @since 2023-08-18 16:00:59
	 */
	boolean updateGoodsSkuStatusByStoreId(Long storeId, String marketEnable, String authFlag);

	/**
	 * 更新SKU库存
	 *
	 * @param goodsSkuStockUpdateCmds sku库存修改实体
	 * @since 2023-08-18 16:00:59
	 */
	boolean updateStocks(List<GoodsSkuStockUpdateCmd> goodsSkuStockUpdateCmds);

	/**
	 * 更新SKU库存
	 *
	 * @param skuId    SKUId
	 * @param quantity 设置的库存数量
	 * @since 2023-08-18 16:00:59
	 */
	boolean updateStock(Long skuId, Integer quantity);

	/**
	 * 修改商品库存字段
	 *
	 * @param goodsSkusPOS
	 */
//	boolean updateGoodsStuck(List<GoodsSkuPO> goodsSkusPOS);

	/**
	 * 更新SKU评价数量
	 *
	 * @param skuId SKUId
	 */
	boolean updateGoodsSkuCommentNum(Long skuId);


	/**
	 * 删除并且新增sku，即覆盖之前信息
	 *
	 * @param goodsSkusPOS 商品sku集合
	 */
//	boolean deleteAndInsertGoodsSkus(List<GoodsSkuPO> goodsSkusPOS);

	/**
	 * 批量渲染商品sku
	 *
	 * @param goodsSkuPOList SKU基础数据列表
	 * @param goodsAddCmd 商品操作信息
	 */
//	void renderGoodsSkuList(List<GoodsSkuPO> goodsSkuPOList, GoodsAddCmd goodsAddCmd);
}
