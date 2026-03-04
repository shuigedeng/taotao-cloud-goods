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
 * 分类名称
 *
 * @param value 分类名称
 * @author shuigedeng
 * @date 2023-01-04 13:21
 */
@RecordBuilder
public record CategoryName(@NotBlank @Length(min = 1, max = 64) String value) implements ValueObject<CategoryName> {

	public CategoryName {
		this.validateSelf();
	}

	public static CategoryName of( String categoryName ) {
		return new CategoryName(categoryName);
	}


	@Override
	public boolean sameValueAs( CategoryName other ) {
		return false;
	}

	@Override
	public String value() {
		return value;
	}
}
