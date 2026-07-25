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

package com.taotao.cloud.goods.application.service.query;

import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.ddd.model.application.service.QueryService;
import com.taotao.cloud.goods.application.dto.goods.query.GoodsPageQuery;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsResult;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsSkuParamsResult;

import java.util.List;

/**
 * 商品查询服务接口
 * <p>
 * 定义商品的查询操作，包括商品分页查询、按品牌查询、获取SKU参数等
 * </p>
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:00:15
 */
public interface GoodsQueryService extends QueryService {

	/**
	 * 根据品牌获取商品
	 *
	 * @param brandIds 品牌ids
	 * @return {@link List }<{@link GoodsResult }>
	 * @since 2022-04-27 17:00:15
	 */
	List<GoodsResult> queryByBrandIds( List<Long> brandIds );

	/**
	 * 查询商品CO
	 *
	 * @param goodsId 商品id
	 * @return {@link GoodsSkuParamsResult }
	 * @since 2022-04-27 17:00:16
	 */
	GoodsSkuParamsResult queryDetail( Long goodsId );

	/**
	 * 商品查询
	 *
	 * @param goodsPageQuery 查询参数
	 * @return {@link PageResult }<{@link GoodsResult }>
	 * @since 2022-04-27 17:00:16
	 */
	PageResult<GoodsResult> queryGoodsPage( GoodsPageQuery goodsPageQuery );

	/**
	 * 商品查询
	 *
	 * @param goodsPageQuery 查询参数
	 * @return {@link List }<{@link GoodsResult }>
	 * @since 2022-04-27 17:00:16
	 */
	List<GoodsResult> queryListByParams( GoodsPageQuery goodsPageQuery );

	/**
	 * 统计店铺的商品数量
	 *
	 * @param storeId 店铺id
	 * @return {@link Long }
	 * @since 2022-04-27 17:00:16
	 */
	Long queryCountStoreGoodsNum( Long storeId );
}
