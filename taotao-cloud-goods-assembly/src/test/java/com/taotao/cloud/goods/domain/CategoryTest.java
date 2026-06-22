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

import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.goods.domain.entity.Category;
import com.taotao.cloud.goods.domain.valobj.CategoryDesc;
import com.taotao.cloud.goods.domain.valobj.CategoryName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Category 实体单元测试
 */
class CategoryTest {

	@Nested
	class CreateCategory {

		@Test
		void shouldCreateWithAllFields() {
			BizId id = BizId.newBizId();
			BizId parentId = BizId.newBizId();
			CategoryName name = CategoryName.of("电子分类");
			CategoryDesc desc = CategoryDesc.of("电子产品分类描述");

			Category category = new Category(id, parentId, name, desc);

			assertThat(category.getId()).isEqualTo(id);
			assertThat(category.getParentCategoryId()).isEqualTo(parentId);
			assertThat(category.getCategoryName()).isEqualTo(name);
			assertThat(category.getCategoryDesc()).isEqualTo(desc);
			assertThat(category.getCreateTime()).isNotNull();
			assertThat(category.getUpdateTime()).isEqualTo(category.getCreateTime());
		}
	}

	@Nested
	class ModifyBasicInfo {

		@Test
		void shouldUpdateFields() {
			Category category = new Category(
				BizId.newBizId(), BizId.newBizId(),
				CategoryName.of("旧分类"), CategoryDesc.of("旧描述"));

			BizId newParentId = BizId.newBizId();
			CategoryName newName = CategoryName.of("新分类");
			CategoryDesc newDesc = CategoryDesc.of("新描述");

			category.modifyBasicInfo(newParentId, newName, newDesc);

			assertThat(category.getParentCategoryId()).isEqualTo(newParentId);
			assertThat(category.getCategoryName()).isEqualTo(newName);
			assertThat(category.getCategoryDesc()).isEqualTo(newDesc);
			assertThat(category.getUpdateTime()).isAfter(category.getCreateTime());
		}
	}

	@Nested
	class Equality {

		@Test
		void shouldBeEqualWhenSameId() {
			BizId id = BizId.newBizId();
			Category category1 = new Category(id, BizId.newBizId(),
				CategoryName.of("分类1"), CategoryDesc.of("描述1"));
			Category category2 = new Category(id, BizId.newBizId(),
				CategoryName.of("分类2"), CategoryDesc.of("描述2"));

			assertThat(category1).isEqualTo(category2);
			assertThat(category1.hashCode()).isEqualTo(category2.hashCode());
		}

		@Test
		void shouldNotBeEqualWhenDifferentId() {
			Category category1 = new Category(BizId.newBizId(), BizId.newBizId(),
				CategoryName.of("分类"), CategoryDesc.of("描述"));
			Category category2 = new Category(BizId.newBizId(), BizId.newBizId(),
				CategoryName.of("分类"), CategoryDesc.of("描述"));

			assertThat(category1).isNotEqualTo(category2);
		}
	}
}
