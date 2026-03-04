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

package com.taotao.cloud.goods.application.dto.own.draft.result;

import com.taotao.boot.common.model.ddd.types.MarkerResult;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.math.BigDecimal;

/**
 * 草稿商品基础CO
 *
 * @param goodsName 商品名称
 * @param price 商品价格
 * @param brandId 品牌id
 * @param categoryPath 分类path
 * @param goodsUnit 计量单位
 * @param sellingPoint 卖点
 * @param marketEnable 上架状态
 * @param intro 详情
 * @param mobileIntro 商品移动端详情
 * @param buyCount 购买数量
 * @param quantity 库存
 * @param enableQuantity 可用库存
 * @param grade 商品好评率
 * @param thumbnail 缩略图路径
 * @param big 大图路径
 * @param small 小图路径
 * @param original 原图路径
 * @param storeCategoryPath 店铺分类路径
 * @param commentNum 评论数量
 * @param storeId 卖家id
 * @param storeName 卖家名字
 * @param templateId 运费模板id
 * @param selfOperated 是否自营
 * @param goodsVideo 商品视频
 * @param recommend 是否为推荐商品
 * @param salesModel 销售模式
 * @param saveType 草稿商品保存类型
 * @param categoryNameJson 分类名称JSON
 * @param goodsParamsListJson 商品参数JSON
 * @param goodsGalleryListJson 商品图片JSON
 * @param skuListJson sku列表JSON
 * @param goodsType 商品类型
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-14 22:10:24
 */
@RecordBuilder
@Schema(description = "分类品牌CO")
public record DraftGoodsResult(Long id, String goodsName, BigDecimal price, Long brandId, String categoryPath,
							   String goodsUnit, String sellingPoint, String marketEnable, String intro,
							   String mobileIntro, Integer buyCount, Integer quantity, Integer enableQuantity,
							   BigDecimal grade, String thumbnail, String big, String small, String original,
							   String storeCategoryPath, Integer commentNum, Long storeId, String storeName,
							   Long templateId, Boolean selfOperated, String goodsVideo, Boolean recommend,
							   String salesModel, String saveType, String categoryNameJson, String goodsParamsListJson,
							   String goodsGalleryListJson, String skuListJson, String goodsType) implements
	MarkerResult {

	@Serial
	private static final long serialVersionUID = 1450550797436233753L;

}
