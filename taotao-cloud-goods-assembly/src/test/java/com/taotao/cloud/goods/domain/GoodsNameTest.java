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

import com.taotao.cloud.goods.domain.valobj.GoodsName;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

/**
 * GoodsName 值对象单元测试
 */
class GoodsNameTest {

	@Test
	void shouldCreateWhenValueIsValid() {
		GoodsName name = GoodsName.of("测试商品名称");
		assertThat(name.value()).isEqualTo("测试商品名称");
	}

	@Test
	void shouldRejectWhenValueIsBlank() {
		assertThatThrownBy(() -> GoodsName.of(""))
			.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void shouldRejectWhenValueExceedsMaxLength() {
		String longName = "a".repeat(121);
		assertThatThrownBy(() -> GoodsName.of(longName))
			.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void sameValueAsShouldReturnTrueWhenValuesAreEqual() {
		GoodsName name1 = GoodsName.of("商品");
		GoodsName name2 = GoodsName.of("商品");
		assertThat(name1.sameValueAs(name2)).isTrue();
	}

	@Test
	void sameValueAsShouldReturnFalseWhenValuesAreDifferent() {
		GoodsName name1 = GoodsName.of("商品A");
		GoodsName name2 = GoodsName.of("商品B");
		assertThat(name1.sameValueAs(name2)).isFalse();
	}

	@Test
	void equalsShouldWorkWhenSameInstance() {
		GoodsName name = GoodsName.of("商品");
		assertThat(name.equals(name)).isTrue();
	}

	@Test
	void equalsShouldWorkForNull() {
		GoodsName name = GoodsName.of("商品");
		assertThat(name.equals(null)).isFalse();
	}

	@Test
	void equalsShouldWorkForDifferentType() {
		GoodsName name = GoodsName.of("商品");
		assertThat(name.equals("商品")).isFalse();
	}
}
