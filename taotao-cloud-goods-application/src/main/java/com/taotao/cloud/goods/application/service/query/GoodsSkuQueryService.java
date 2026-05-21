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

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.ddd.model.application.service.QueryService;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsSkuResult;
import com.taotao.cloud.goods.application.dto.goods.query.GoodsSkuSearchQuery;
import java.util.List;
import java.util.Map;

/**
 * 商品sku业务层
 *
 * @author shuigedeng
 * @version 2023.07
 * @see BaseSuperService
 * @since 2023-08-18 16:00:58
 */
public interface GoodsSkuQueryService extends QueryService {
    /// **
    // * 获取商品SKU缓存ID
    // *
    // * @param id SkuId
    // * @return {@link String }
    // * @since 2023-08-18 16:00:58
    // */
    // default String queryCacheKeys(Long id) {
    //	return CachePrefix.GOODS_SKU.queryPrefix() + id;
    // }
    //
    /// **
    // * 获取商品SKU库存缓存ID
    // *
    // * @param id SkuId
    // * @return {@link String }
    // * @since 2023-08-18 16:00:58
    // */
    // default String queryStockCacheKey(Long id) {
    //	return CachePrefix.SKU_STOCK.queryPrefix() + id;
    // }
    //

    /**
     * 从redis缓存中获取商品SKU信息
     *
     * @param skuId SkuId
     * @return {@link GoodsSkuResult }
     * @since 2023-08-18 16:00:59
     */
    //	GoodsSkuResult queryGoodsSkuByIdFromCache(Long skuId);

    /**
     * 从缓存中获取可参与促销商品
     *
     * @param skuId skuid
     * @return {@link GoodsSkuResult }
     * @since 2023-08-18 16:00:59
     */
    //	GoodsSkuResult queryCanPromotionGoodsSkuByIdFromCache(Long skuId);

    /**
     * 获取商品sku详情
     *
     * @param goodsId 商品ID
     * @param skuId   skuID
     * @return {@link Map }<{@link String }, {@link Object }>
     * @since 2023-08-18 16:00:59
     */
    Map<String, Object> queryGoodsSkuDetail(Long goodsId, Long skuId);

    /**
     * 批量从redis中获取商品SKU信息
     *
     * @param ids SkuId集合
     * @return {@link List }<{@link GoodsSkuResult }>
     * @since 2023-08-18 16:00:59
     */
    //	List<GoodsSkuResult> queryGoodsSkuByIdFromCache(List<Long> ids);

    /**
     * 获取goodsId下所有的goodsSku
     *
     * @param goodsId 商品id
     * @return {@link List }<{@link GoodsSkuResult }>
     * @since 2023-08-18 16:00:59
     */
    List<GoodsSkuResult> queryGoodsListByGoodsId(Long goodsId);

    /**
     * 获取goodsId下所有的goodsSku
     *
     * @param goodsId 商品id
     * @return {@link List }<{@link GoodsSkuResult }>
     * @since 2023-08-18 16:00:59
     */
    //	List<GoodsSkuResult> queryGoodsSkuListByGoodsId(Long goodsId);

    /**
     * 根据goodsSku组装goodsSkuCO
     *
     * @param list 商品id
     * @return {@link List }<{@link GoodsSkuResult }>
     * @since 2023-08-18 16:00:59
     */
    //	List<GoodsSkuCO> queryGoodsSkuVOList(List<GoodsSkuResult> list);

    /**
     * 根据goodsSku组装goodsSkuCO
     *
     * @param GoodsSkuResult 商品规格
     * @return {@link GoodsSkuResult }
     * @since 2023-08-18 16:00:59
     */
    //	GoodsSkuCO queryGoodsSkuVO(GoodsSkuResult GoodsSkuResult);

    /**
     * 分页查询商品sku信息
     *
     * @param searchParams 查询参数
     * @return {@link IPage }<{@link GoodsSkuResult }>
     * @since 2023-08-18 16:00:59
     */
    //	IPage<GoodsSkuResult> queryGoodsSkuByPage(GoodsPageQry searchParams);

    /**
     * 分页查询商品sku信息
     *
     * @param page         分页参数
     * @param queryWrapper 查询参数
     * @return {@link IPage }<{@link GoodsSkuSearchQuery }>
     * @since 2023-08-18 16:00:59
     */
    PageResult<GoodsSkuSearchQuery> queryGoodsSkuDTOByPage(
		Page<GoodsSkuSearchQuery> page, Wrapper<GoodsSkuSearchQuery> queryWrapper);

    /**
     * 列表查询商品sku信息
     *
     * @param searchParams 查询参数
     * @return {@link List }<{@link GoodsSkuResult }>
     * @since 2023-08-18 16:00:59
     */
    //	List<GoodsSkuResult> queryGoodsSkuByList(GoodsPageQry searchParams);

    /**
     * 获取商品sku库存
     *
     * @param skuId 商品skuId
     * @return {@link Integer }
     * @since 2023-08-18 16:00:59
     */
    Integer queryStock(Long skuId);

    /**
     * 根据商品id获取全部skuId的集合
     *
     * @param goodsId goodsId
     * @return 全部skuId的集合
     */
    List<String> querySkuIdsByGoodsId(Long goodsId);

    /**
     * 统计sku总数
     *
     * @param storeId 店铺id
     * @return sku总数
     */
    Long queryCountSkuNum(Long storeId);

}
