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
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

/**
 * 兑换CO
 */
public record ExchangeResult(@Schema(description = "是否允许积分兑换") Integer enableExchange,
							 @Schema(description = "兑换所需金额 ") BigDecimal exchangeMoney,
							 @Schema(description = "积分兑换所属分类 ") Integer categoryId,
							 @Schema(description = "积分兑换使用的积分 ") Integer exchangePoint) implements
	MarkerResult {

}
