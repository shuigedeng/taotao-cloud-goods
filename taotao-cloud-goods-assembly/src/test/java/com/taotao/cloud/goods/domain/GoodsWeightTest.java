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

import com.taotao.cloud.goods.domain.valobj.GoodsWeight;
import com.taotao.cloud.goods.domain.valobj.WeightUnitEnum;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

/**
 * GoodsWeight 值对象单元测试
 */
class GoodsWeightTest {

	@Test
	void sameValueAsShouldReturnTrueWhenAllFieldsEqual() {
		GoodsWeight w1 = GoodsWeight.of(BigDecimal.TEN, WeightUnitEnum.KG);
		GoodsWeight w2 = GoodsWeight.of(BigDecimal.TEN, WeightUnitEnum.KG);
		assertThat(w1.sameValueAs(w2)).isTrue();
	}

	@Test
	void sameValueAsShouldReturnFalseWhenWeightDiffers() {
		GoodsWeight w1 = GoodsWeight.of(BigDecimal.ONE, WeightUnitEnum.KG);
		GoodsWeight w2 = GoodsWeight.of(BigDecimal.TEN, WeightUnitEnum.KG);
		assertThat(w1.sameValueAs(w2)).isFalse();
	}

	@Test
	void sameValueAsShouldReturnFalseWhenUnitDiffers() {
		GoodsWeight w1 = GoodsWeight.of(BigDecimal.ONE, WeightUnitEnum.KG);
		GoodsWeight w2 = GoodsWeight.of(BigDecimal.ONE, WeightUnitEnum.G);
		assertThat(w1.sameValueAs(w2)).isFalse();
	}
}
