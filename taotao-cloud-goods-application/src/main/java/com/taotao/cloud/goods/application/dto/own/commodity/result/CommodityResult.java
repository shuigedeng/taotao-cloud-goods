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

package com.taotao.cloud.goods.application.dto.own.commodity.result;

import com.taotao.boot.common.model.ddd.types.MarkerResult;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

/**
 * 小程序直播商品
 *
 * @param goodsImage 图片
 * @param name 商品名称
 * @param priceType 1：一口价（只需要传入price，price2不传）
 *
 * <p>2：价格区间（price字段为左边界，price2字段为右边界，price和price2必传）
 *
 * <p>3：显示折扣价（price字段为原价，price2字段为现价， price和price2必传
 * @param price 价格
 * @param price2 价格2
 * @param url 商品详情页的小程序路径
 * @param liveGoodsId 微信程序直播商品ID
 * @param auditId 审核单ID
 * @param auditStatus 审核状态
 * @param storeId 店铺ID
 * @param goodsId 商品ID
 * @param skuId skuId
 */
@RecordBuilder
@Schema(description = "小程序直播商品CO")
public record CommodityResult(String goodsImage, String name, Integer priceType, BigDecimal price, BigDecimal price2,
							  String url, Long liveGoodsId, Long auditId, String auditStatus, Long storeId,
							  Long goodsId, Long skuId) implements MarkerResult {

}
