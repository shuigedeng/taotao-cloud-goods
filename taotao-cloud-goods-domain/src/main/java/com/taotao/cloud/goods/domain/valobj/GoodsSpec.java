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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

/**
 * 商品规格
 *
 * @param manufactureDate 生产日期
 * @param expirationDate 过期日期
 * @param goodsWeight 商品重量
 * @param goodsDesc 商品介绍
 */
@RecordBuilder
public record GoodsSpec(@NotNull @PastOrPresent LocalDate manufactureDate, @NotNull LocalDate expirationDate,
						@NotNull GoodsWeight goodsWeight,
						@NotBlank @Length(min = 1, max = 1024) String goodsDesc)
	implements ValueObject<GoodsSpec> {

	public GoodsSpec( LocalDate manufactureDate, LocalDate expirationDate, GoodsWeight goodsWeight, String goodsDesc ) {
		this.manufactureDate = manufactureDate;
		this.expirationDate = expirationDate;
		this.goodsWeight = goodsWeight;
		this.goodsDesc = goodsDesc;
	}

	@Override
	public boolean sameValueAs( GoodsSpec other ) {
		return false;
	}

}
