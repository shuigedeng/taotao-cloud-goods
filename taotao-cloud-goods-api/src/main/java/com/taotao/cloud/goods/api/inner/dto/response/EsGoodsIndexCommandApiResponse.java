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

package com.taotao.cloud.goods.api.inner.dto.response;

import com.taotao.boot.common.model.ddd.types.MarkerResponse;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品索引
 *
 * @param goodsId 商品id
 * @param goodsName 商品名称
 * @param sn 商品编号
 * @param storeId 卖家id
 * @param storeName 卖家名称
 * @param buyCount 销量
 * @param small 小图
 * @param thumbnail 缩略图
 * @param brandId 品牌id
 * @param brandName 品牌名称
 * @param brandUrl 品牌图片地址
 * @param categoryPath 分类path
 * @param categoryNamePath 分类名称path
 * @param storeCategoryPath 店铺分类id
 * @param storeCategoryNamePath 店铺分类名称
 * @param price 商品价格
 * @param promotionPrice 促销价
 * @param point 如果是积分商品需要使用的积分
 * @param commentNum 评价数量
 * @param highPraiseNum 好评数量
 * @param grade 好评率
 * @param intro 详情
 * @param mobileIntro 商品移动端详情
 * @param selfOperated 是否自营
 * @param recommend 是否为推荐商品
 * @param salesModel 销售模式
 * @param authFlag 审核状态
 * @param sellingPoint 卖点
 * @param marketEnable 上架状态
 * @param goodsVideo 商品视频
 * @param goodsType 商品类型
 * @param skuSource 商品sku基础分数
 * @param attrList 商品属性（参数和规格）
 * @param promotionMapJson 商品促销活动集合JSON，key 为 促销活动类型，value 为 促销活动实体信息
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:18:08
 */
@RecordBuilder
public record EsGoodsIndexCommandApiResponse(Long id, Long goodsId, String goodsName, String sn, Long storeId,
											 String storeName, Integer buyCount, String small, String thumbnail,
											 Long brandId, String brandName, String brandUrl, String categoryPath,
											 String categoryNamePath, String storeCategoryPath,
											 String storeCategoryNamePath, BigDecimal price, BigDecimal promotionPrice,
											 Integer point, Integer commentNum, Integer highPraiseNum, BigDecimal grade,
											 String intro, String mobileIntro, Boolean selfOperated, Boolean recommend,
											 String salesModel, String authFlag, String sellingPoint,
											 String marketEnable, String goodsVideo, LocalDateTime releaseTime,
											 String goodsType, Integer skuSource,
											 List<EsGoodsAttributeCommandApiResponse> attrList,
											 String promotionMapJson) implements MarkerResponse {

	@Serial
	private  static final long serialVersionUID = -6856471777036048874L;

}
