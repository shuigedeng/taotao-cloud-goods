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

import com.taotao.cloud.goods.domain.valobj.CategoryDesc;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

/**
 * CategoryDesc 值对象单元测试
 */
class CategoryDescTest {

	@Test
	void shouldCreateWhenValueIsValid() {
		CategoryDesc desc = CategoryDesc.of("这是一个商品分类描述");
		assertThat(desc.value()).isEqualTo("这是一个商品分类描述");
	}

	@Test
	void shouldRejectWhenValueExceedsMaxLength() {
		String longDesc = "a".repeat(513);
		assertThatThrownBy(() -> CategoryDesc.of(longDesc))
			.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void sameValueAsShouldReturnTrueWhenEqual() {
		CategoryDesc desc1 = CategoryDesc.of("描述");
		CategoryDesc desc2 = CategoryDesc.of("描述");
		assertThat(desc1.sameValueAs(desc2)).isTrue();
	}

	@Test
	void sameValueAsShouldReturnFalseWhenDifferent() {
		CategoryDesc desc1 = CategoryDesc.of("描述A");
		CategoryDesc desc2 = CategoryDesc.of("描述B");
		assertThat(desc1.sameValueAs(desc2)).isFalse();
	}
}
