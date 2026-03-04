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

package com.taotao.cloud.goods.application.dto.own.specification.result;

import com.taotao.boot.common.model.ddd.types.MarkerResult;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.cloud.goods.application.dto.own.goods.result.GoodsSkuResult;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;

/**
 * 库存警告封装类
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-14 21:52:39
 */
@RecordBuilder
public record StockWarningResult(@Schema(description = "库存警告数量") Integer stockWarningNum,
								 @Schema(description = "商品SKU列表") PageResult<GoodsSkuResult> goodsSkuPage)  implements
	MarkerResult {

	@Serial
	private static final long serialVersionUID = -7605952923416404638L;

}
