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

package com.taotao.cloud.goods.domain.valobj;

import com.taotao.boot.ddd.model.domain.ValueObject;
import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

/**
 * 商品重量
 *
 * @param weight 重量值
 * @param unit 重量单位
 */
@RecordBuilder
public record GoodsWeight(@NotNull @PositiveOrZero BigDecimal weight, @NotNull WeightUnit unit) implements
	ValueObject<GoodsWeight> {

	public GoodsWeight( BigDecimal weight, WeightUnit unit ) {
		this.weight = weight;
		this.unit = unit;
	}

	public static GoodsWeight of( BigDecimal weight, WeightUnit unit ) {
		return new GoodsWeight(weight, unit);
	}

	@Override
	public boolean sameValueAs( GoodsWeight other ) {
		return false;
	}

}
