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

package com.taotao.cloud.goods.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.taotao.cloud.goods.domain.valobj.CategoryName;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

/**
 * CategoryName 值对象单元测试
 */
class CategoryNameTest {

	@Test
	void shouldCreateWhenValueIsValid() {
		CategoryName name = CategoryName.of("电子分类");
		assertThat(name.value()).isEqualTo("电子分类");
	}

	@Test
	void shouldRejectWhenValueIsBlank() {
		assertThatThrownBy(() -> CategoryName.of(""))
			.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void shouldRejectWhenValueExceedsMaxLength() {
		String longName = "a".repeat(65);
		assertThatThrownBy(() -> CategoryName.of(longName))
			.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void sameValueAsShouldReturnTrueWhenEqual() {
		CategoryName name1 = CategoryName.of("分类");
		CategoryName name2 = CategoryName.of("分类");
		assertThat(name1.sameValueAs(name2)).isTrue();
	}

	@Test
	void sameValueAsShouldReturnFalseWhenDifferent() {
		CategoryName name1 = CategoryName.of("分类A");
		CategoryName name2 = CategoryName.of("分类B");
		assertThat(name1.sameValueAs(name2)).isFalse();
	}
}
