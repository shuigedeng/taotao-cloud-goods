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

import com.taotao.cloud.goods.domain.valobj.GoodsStatusEnum;
import org.junit.jupiter.api.Test;

/**
 * GoodsStatus 枚举值对象单元测试
 */
class GoodsStatusTest {

	@Test
	void shelvedShouldHaveValue10() {
		assertThat(GoodsStatusEnum.SHELVED.getValue()).isEqualTo(10);
	}

	@Test
	void unshelvedShouldHaveValue20() {
		assertThat(GoodsStatusEnum.UNSHELVED.getValue()).isEqualTo(20);
	}

	@Test
	void sameValueAsShouldReturnTrueWhenSameValue() {
		assertThat(GoodsStatusEnum.SHELVED.sameValueAs(10)).isTrue();
	}

	@Test
	void sameValueAsShouldReturnFalseWhenDifferentValue() {
		assertThat(GoodsStatusEnum.SHELVED.sameValueAs(20)).isFalse();
	}

	@Test
	void ofShouldReturnCorrectEnum() {
		assertThat(GoodsStatusEnum.of(10)).isEqualTo(GoodsStatusEnum.SHELVED);
		assertThat(GoodsStatusEnum.of(20)).isEqualTo(GoodsStatusEnum.UNSHELVED);
	}

	@Test
	void shelvedDescShouldBe已上架() {
		assertThat(GoodsStatusEnum.SHELVED.getDesc()).isEqualTo("已上架");
	}

	@Test
	void unshelvedDescShouldBe已下架() {
		assertThat(GoodsStatusEnum.UNSHELVED.getDesc()).isEqualTo("已下架");
	}
}
