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
import com.taotao.cloud.goods.domain.entity.Tag;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Tag 实体单元测试
 */
class TagTest {

	@Nested
	class CreateTag {

		@Test
		void shouldCreateWithAllFields() {
			BizId id = BizId.newBizId();
			Tag tag = new Tag(id, "热门标签", "热门商品标签");

			assertThat(tag.getId()).isEqualTo(id);
			assertThat(tag.getTagName()).isEqualTo("热门标签");
			assertThat(tag.getTagDesc()).isEqualTo("热门商品标签");
			assertThat(tag.getCreateTime()).isNotNull();
			assertThat(tag.getUpdateTime()).isEqualTo(tag.getCreateTime());
		}

		@Test
		void shouldCreateWithIdOnly() {
			BizId id = BizId.newBizId();
			Tag tag = new Tag(id);

			assertThat(tag.getId()).isEqualTo(id);
		}
	}

	@Nested
	class ModifyBasicInfo {

		@Test
		void shouldUpdateFields() {
			Tag tag = new Tag(BizId.newBizId(), "旧标签", "旧描述");

			tag.modifyBasicInfo("新标签", "新描述");

			assertThat(tag.getTagName()).isEqualTo("新标签");
			assertThat(tag.getTagDesc()).isEqualTo("新描述");
			assertThat(tag.getUpdateTime()).isAfter(tag.getCreateTime());
		}
	}

	@Nested
	class Equality {

		@Test
		void shouldBeEqualWhenSameId() {
			BizId id = BizId.newBizId();
			Tag tag1 = new Tag(id, "标签1", "描述1");
			Tag tag2 = new Tag(id, "标签2", "描述2");

			assertThat(tag1).isEqualTo(tag2);
			assertThat(tag1.hashCode()).isEqualTo(tag2.hashCode());
		}

		@Test
		void shouldNotBeEqualWhenDifferentId() {
			Tag tag1 = new Tag(BizId.newBizId(), "标签", "描述");
			Tag tag2 = new Tag(BizId.newBizId(), "标签", "描述");

			assertThat(tag1).isNotEqualTo(tag2);
		}
	}

	@Nested
	class BizIdFactory {

		@Test
		void shouldCreateTagsFromBizIds() {
			var ids = java.util.Set.of(BizId.newBizId(), BizId.newBizId());
			var tags = Tag.bizIds(ids);
			assertThat(tags).hasSize(2);
			assertThat(tags).allMatch(t -> t.getTagName() == null);
		}
	}
}
