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

import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.boot.ddd.model.val.Price;
import com.taotao.cloud.goods.domain.aggregate.GoodsAgg;
import com.taotao.cloud.goods.domain.entity.Category;
import com.taotao.cloud.goods.domain.entity.Tag;
import com.taotao.cloud.goods.domain.event.FreightTemplateChangedEvent;
import com.taotao.cloud.goods.domain.valobj.CategoryDesc;
import com.taotao.cloud.goods.domain.valobj.CategoryName;
import com.taotao.cloud.goods.domain.valobj.GoodsName;
import com.taotao.cloud.goods.domain.valobj.GoodsSpec;
import com.taotao.cloud.goods.domain.valobj.GoodsStatus;
import com.taotao.cloud.goods.domain.valobj.GoodsWeight;
import com.taotao.cloud.goods.domain.valobj.WeightUnit;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * GoodsAgg 聚合根领域行为单元测试
 *
 * <p>领域层纯 POJO 测试，无需 Spring 上下文。
 */
class GoodsAggTest {

	private Category createCategory() {
		return new Category(
			BizId.newBizId(),
			BizId.newBizId(),
			CategoryName.of("测试分类"),
			CategoryDesc.of("测试分类描述"));
	}

	private Tag createTag(String name) {
		return new Tag(BizId.newBizId(), name, name + "描述");
	}

	private GoodsName createGoodsName() {
		return GoodsName.of("测试商品");
	}

	private GoodsSpec createGoodsSpec() {
		return new GoodsSpec(
			LocalDate.now(),
			LocalDate.now().plusDays(365),
			GoodsWeight.of(BigDecimal.valueOf(1.0), WeightUnit.KG),
			"测试商品介绍");
	}

	private Price createPrice() {
		return Price.of(BigDecimal.valueOf(99.99));
	}

	@Nested
	class CreateGoods {

		@Test
		void shouldCreateGoodsWithUnshelvedStatus() {
			Category category = createCategory();
			GoodsName goodsName = createGoodsName();
			GoodsSpec goodsSpec = createGoodsSpec();
			Price price = createPrice();
			List<Tag> tags = List.of(createTag("标签1"));

			GoodsAgg goods = GoodsAgg.createGoods(category, goodsName, goodsSpec, price, tags);

			assertThat(goods.getGoodsStatus()).isEqualTo(GoodsStatus.UNSHELVED);
			assertThat(goods.getCategory()).isEqualTo(category);
			assertThat(goods.getGoodsName()).isEqualTo(goodsName);
			assertThat(goods.getGoodsSpec()).isEqualTo(goodsSpec);
			assertThat(goods.getGoodsPrice()).isEqualTo(price);
			assertThat(goods.getTags()).hasSize(1);
			assertThat(goods.getCreateTime()).isNotNull();
			assertThat(goods.getUpdateTime()).isNotNull();
		}

		@Test
		void shouldCreateGoodsWithSpecifiedStatus() {
			Category category = createCategory();
			BizId id = BizId.newBizId();

			GoodsAgg goods = GoodsAgg.create(
				id,
				category,
				createGoodsName(),
				createGoodsSpec(),
				createPrice(),
				GoodsStatus.SHELVED,
				List.of(createTag("标签1")));

			assertThat(goods.getId()).isEqualTo(id);
			assertThat(goods.getGoodsStatus()).isEqualTo(GoodsStatus.SHELVED);
		}
	}

	@Nested
	class ShelveGoods {

		@Test
		void shouldChangeStatusToShelved() {
			GoodsAgg goods = createUnshelvedGoods();

			goods.shelve();

			assertThat(goods.getGoodsStatus()).isEqualTo(GoodsStatus.SHELVED);
		}
	}

	@Nested
	class UnshelveGoods {

		@Test
		void shouldChangeStatusToUnshelved() {
			GoodsAgg goods = createShelvedGoods();

			goods.unshelve();

			assertThat(goods.getGoodsStatus()).isEqualTo(GoodsStatus.UNSHELVED);
		}
	}

	@Nested
	class ModifyBasicInfo {

		@Test
		void shouldModifyBasicInfo() {
			GoodsAgg goods = createUnshelvedGoods();
			Category newCategory = new Category(
				BizId.newBizId(), BizId.newBizId(),
				CategoryName.of("新分类"), CategoryDesc.of("新描述"));
			GoodsName newName = GoodsName.of("新商品名");
			GoodsSpec newSpec = new GoodsSpec(
				LocalDate.now(), LocalDate.now().plusDays(180),
				GoodsWeight.of(BigDecimal.valueOf(2.5), WeightUnit.KG), "新介绍");
			Price newPrice = Price.of(BigDecimal.valueOf(199.99));
			List<Tag> newTags = List.of(createTag("新标签"));

			goods.modifyBasicInfo(newCategory, newName, newSpec, newPrice, newTags);

			assertThat(goods.getCategory()).isEqualTo(newCategory);
			assertThat(goods.getGoodsName()).isEqualTo(newName);
			assertThat(goods.getGoodsSpec()).isEqualTo(newSpec);
			assertThat(goods.getGoodsPrice()).isEqualTo(newPrice);
			assertThat(goods.getTags()).containsExactlyElementsOf(newTags);
		}
	}

	@Nested
	class ChangeFreightTemplate {

		@Test
		void shouldChangeFreightTemplateAndRegisterEvent() {
			GoodsAgg goods = createUnshelvedGoods();
			String newTemplateId = "template-002";

			goods.changeFreightTemplate(newTemplateId, "operator-001");

			assertThat(goods.getTemplateId()).isEqualTo(newTemplateId);
		}

		@Test
		void shouldThrowExceptionWhenTemplateIdIsNull() {
			GoodsAgg goods = createUnshelvedGoods();

			assertThatThrownBy(() -> goods.changeFreightTemplate(null, "operator-001"))
				.isInstanceOf(BusinessException.class)
				.hasMessageContaining("运费模板ID不能为空");
		}

		@Test
		void shouldThrowExceptionWhenTemplateIdIsEmpty() {
			GoodsAgg goods = createUnshelvedGoods();

			assertThatThrownBy(() -> goods.changeFreightTemplate("", "operator-001"))
				.isInstanceOf(BusinessException.class)
				.hasMessageContaining("运费模板ID不能为空");
		}
	}

	@Nested
	class Equality {

		@Test
		void shouldBeEqualWhenSameId() {
			BizId id = BizId.newBizId();
			Category category = createCategory();

			GoodsAgg goods1 = GoodsAgg.create(id, category, createGoodsName(), createGoodsSpec(),
				createPrice(), GoodsStatus.UNSHELVED, List.of(createTag("标签1")));
			GoodsAgg goods2 = GoodsAgg.create(id, category, createGoodsName(), createGoodsSpec(),
				createPrice(), GoodsStatus.UNSHELVED, List.of(createTag("标签1")));

			assertThat(goods1).isEqualTo(goods2);
			assertThat(goods1.hashCode()).isEqualTo(goods2.hashCode());
		}

		@Test
		void shouldNotBeEqualWhenDifferentId() {
			Category category = createCategory();

			GoodsAgg goods1 = GoodsAgg.create(BizId.newBizId(), category,
				createGoodsName(), createGoodsSpec(), createPrice(),
				GoodsStatus.UNSHELVED, List.of(createTag("标签1")));
			GoodsAgg goods2 = GoodsAgg.create(BizId.newBizId(), category,
				createGoodsName(), createGoodsSpec(), createPrice(),
				GoodsStatus.UNSHELVED, List.of(createTag("标签1")));

			assertThat(goods1).isNotEqualTo(goods2);
		}
	}

	// -- helper methods --

	private GoodsAgg createUnshelvedGoods() {
		return GoodsAgg.createGoods(
			createCategory(), createGoodsName(), createGoodsSpec(),
			createPrice(), List.of(createTag("默认标签")));
	}

	private GoodsAgg createShelvedGoods() {
		GoodsAgg goods = createUnshelvedGoods();
		goods.shelve();
		return goods;
	}
}
