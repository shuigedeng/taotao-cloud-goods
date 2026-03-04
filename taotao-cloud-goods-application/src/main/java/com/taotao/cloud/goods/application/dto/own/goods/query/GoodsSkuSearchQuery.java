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

package com.taotao.cloud.goods.application.dto.own.goods.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taotao.boot.common.model.ddd.types.Query;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;

import java.io.Serial;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

/**
 * @since 2022/6/13
 */
@RecordBuilder
public record GoodsSkuSearchQuery(String id, String createBy, Date createTime, String updateBy, Date updateTime,
								  Boolean deleteFlag, @Schema(description = "商品id") String goodsId,
								  @Schema(description = "规格信息json", hidden = true) @JsonIgnore String specs,
								  @Schema(description = "规格信息") String simpleSpecs,
								  @Schema(description = "配送模版id") String freightTemplateId,
								  @Schema(description = "是否是促销商品") Boolean promotionFlag,
								  @Schema(description = "促销价") Double promotionPrice,
								  @Schema(description = "商品名称") String goodsName,
								  @Schema(description = "商品编号") @Length(max = 30, message = "商品规格编号太长，不能超过30个字符") String sn,
								  @Schema(description = "品牌id") String brandId,
								  @Schema(description = "分类path") String categoryPath,
								  @Schema(description = "计量单位") String goodsUnit,
								  @Schema(description = "卖点") String sellingPoint,
								  @Schema(description = "重量") @Max(value = 99999999, message = "重量不能超过99999999") Double weight,
								  @Schema(description = "上架状态") String marketEnable,
								  @Schema(description = "商品详情") String intro,
								  @Schema(description = "商品价格") @Max(value = 99999999, message = "价格不能超过99999999") Double price,
								  @Schema(description = "成本价格") @Max(value = 99999999, message = "成本价格99999999") Double cost,
								  @Schema(description = "浏览数量") Integer viewCount,
								  @Schema(description = "购买数量") Integer buyCount,
								  @Schema(description = "库存") @Max(value = 99999999, message = "库存不能超过99999999") Integer quantity,
								  @Schema(description = "商品好评率") Double grade,
								  @Schema(description = "缩略图路径") String thumbnail,
								  @Schema(description = "大图路径") String big,
								  @Schema(description = "小图路径") String small,
								  @Schema(description = "原图路径") String original,
								  @Schema(description = "店铺分类id") String storeCategoryPath,
								  @Schema(description = "评论数量") Integer commentNum,
								  @Schema(description = "卖家id") String storeId,
								  @Schema(description = "卖家名字") String storeName,
								  @Schema(description = "运费模板id") String templateId,
								  @Schema(description = "审核状态") String authFlag,
								  @Schema(description = "审核信息") String authMessage,
								  @Schema(description = "下架原因") String underMessage,
								  @Schema(description = "是否自营") Boolean selfOperated,
								  @Schema(description = "商品移动端详情") String mobileIntro,
								  @Schema(description = "商品视频") String goodsVideo,
								  @Schema(description = "是否为推荐商品") Boolean recommend,
								  @Schema(description = "销售模式") String salesModel,
								  @Schema(description = "商品类型") String goodsType,
								  @Schema(description = "商品参数json") String params) implements Query {

	@Serial
	private  static final long serialVersionUID = 6600436187015048097L;

}
