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

package com.taotao.cloud.goods.application.dto.own.goods.result;

import com.taotao.boot.common.model.ddd.types.MarkerResult;
import com.taotao.cloud.goods.application.dto.own.specification.result.SpecValueResult;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

/**
 * 商品规格CO
 */
@RecordBuilder
public record GoodsSkuSpecResult(@Schema(description = "商品skuId") Long skuId,
								 @Schema(description = "商品sku所包含规格") List<SpecValueResult> specValues,
								 @Schema(description = "库存") Integer quantity) implements MarkerResult {

}
