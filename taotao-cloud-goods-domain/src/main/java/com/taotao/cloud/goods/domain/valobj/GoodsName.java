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
import org.hibernate.validator.constraints.Length;

/**
 * 商品名称
 *
 * @param value 商品名称
 */
@RecordBuilder
public record GoodsName(	@NotBlank
	@Length(min = 1, max = 120)
	String value)
	implements ValueObject<GoodsName> {

	/**
	 * of 方法
	 *
	 * @param value 值
	 * @return 商品名称
	 * @since 2022.03
	 */
	public static GoodsName of( String value ) {
		return new GoodsName(value).validateThis();
	}

	@Override
	public boolean sameValueAs( GoodsName other ) {
		return this.equals(other);
	}

	@Override
	public String value() {
		return value;
	}
}
