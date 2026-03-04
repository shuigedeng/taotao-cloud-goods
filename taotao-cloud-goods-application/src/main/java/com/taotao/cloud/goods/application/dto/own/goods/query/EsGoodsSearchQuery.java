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

import com.taotao.boot.common.model.ddd.query.PageQuery;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.util.List;
import java.util.Map;

/**
 * es商品搜索查询
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:33:11
 */
@RecordBuilder
public record EsGoodsSearchQuery(@Schema(description = "关键字") String keyword, PageQuery page,
								 @Schema(description = "分类") String categoryId,
								 @Schema(description = "品牌,可以多选 品牌Id@品牌Id@品牌Id") String brandId,
								 @Schema(description = "是否为推荐商品") Boolean recommend,
								 @Schema(description = "价格", example = "10_30") String price,
								 @Schema(description = "属性:参数名_参数值@参数名_参数值", example = "屏幕类型_LED@屏幕尺寸_15英寸") String prop,
								 @Schema(description = "规格项列表") List<String> nameIds,
								 @Schema(description = "卖家id，搜索店铺商品的时候使用") String storeId,
								 @Schema(description = "商家分组id，搜索店铺商品的时候使用") String storeCatId,
								 @Schema(hidden = true) Map<String, List<String>> notShowCol) {

	@Serial
	private static final long serialVersionUID = -7605952923416404638L;

}
