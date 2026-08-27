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

import com.taotao.cloud.goods.domain.valobj.WeightUnitEnum;
import org.junit.jupiter.api.Test;

/**
 * WeightUnit 枚举值对象单元测试
 */
class WeightUnitEnumTest {

	@Test
	void tShouldHaveValueT() {
		assertThat(WeightUnitEnum.T.getValue()).isEqualTo("t");
	}

	@Test
	void kgShouldHaveValueKg() {
		assertThat(WeightUnitEnum.KG.getValue()).isEqualTo("kg");
	}

	@Test
	void gShouldHaveValueG() {
		assertThat(WeightUnitEnum.G.getValue()).isEqualTo("g");
	}

	@Test
	void sameValueAsShouldReturnTrueWhenSameValue() {
		assertThat(WeightUnitEnum.KG.sameValueAs("kg")).isTrue();
	}

	@Test
	void sameValueAsShouldReturnFalseWhenDifferentValue() {
		assertThat(WeightUnitEnum.KG.sameValueAs("t")).isFalse();
	}

	@Test
	void ofShouldReturnCorrectEnum() {
		assertThat(WeightUnitEnum.of("t")).isEqualTo(WeightUnitEnum.T);
		assertThat(WeightUnitEnum.of("kg")).isEqualTo(WeightUnitEnum.KG);
		assertThat(WeightUnitEnum.of("g")).isEqualTo(WeightUnitEnum.G);
	}
}
