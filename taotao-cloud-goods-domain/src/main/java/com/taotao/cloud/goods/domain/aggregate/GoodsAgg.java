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

package com.taotao.cloud.goods.domain.aggregate;

import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.common.utils.lang.StringUtils;
import com.taotao.boot.ddd.model.domain.AggregateRoot;
import com.taotao.boot.ddd.model.domain.event.DomainEvent;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.boot.ddd.model.val.Price;
import com.taotao.cloud.goods.domain.assembler.GoodsDomainAssembler;
import com.taotao.cloud.goods.domain.entity.Category;
import com.taotao.cloud.goods.domain.entity.Tag;
import com.taotao.cloud.goods.domain.event.FreightTemplateChangedEvent;
import com.taotao.cloud.goods.domain.event.GoodsAggSnapshot;
import com.taotao.cloud.goods.domain.event.GoodsCreateEvent;
import com.taotao.cloud.goods.domain.valobj.GoodsName;
import com.taotao.cloud.goods.domain.valobj.GoodsSpec;
import com.taotao.cloud.goods.domain.valobj.GoodsStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * GoodsAgg
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
public class GoodsAgg extends AggregateRoot<BizId> {

	/**
	 * 商品ID
	 */
	@NotNull
	private BizId id;

	/**
	 * 商品标签集合
	 */
	private List<Tag> tags;

	/**
	 * 商品所属分类
	 */
	private @NotNull Category category;

	/**
	 * 商品名称
	 */
	@NotNull
	private GoodsName goodsName;

	/**
	 * 商品描述
	 */
	@NotNull
	private GoodsSpec goodsSpec;

	/**
	 * 商品价格
	 */
	@NotNull
	private Price goodsPrice;

	/**
	 * 商品状态
	 */
	@NotNull
	private GoodsStatus goodsStatus;

	private String templateId;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;
	private String goodsNo;
	private Boolean recommend;

	private GoodsAgg() {
	}

	private static GoodsAggBuilder initBuilder() {
		return new GoodsAggBuilder()
			.id(BizId.newBizId())
			.tags(new ArrayList<>())
			.createTime(LocalDateTime.now())
			.updateTime(LocalDateTime.now());
	}

	public static GoodsAgg init() {
		return initBuilder().build();
	}

	/**
	 * 初始创建商品信息
	 *
	 * @param id 商品ID
	 * @param category 商品所属分类ID
	 * @param goodsName 商品名称
	 * @param goodsSpec 商品描述
	 * @param goodsPrice 商品价格
	 * @param goodsStatus 商品状态
	 */
	public static GoodsAgg create(
		BizId id,
		@NotNull Category category,
		GoodsName goodsName,
		GoodsSpec goodsSpec,
		Price goodsPrice,
		GoodsStatus goodsStatus,
		List<Tag> tagIds ) {
		GoodsAgg goodsAgg = initBuilder()
			.id(id)
			.category(category)
			.goodsName(goodsName)
			.goodsSpec(goodsSpec)
			.goodsPrice(goodsPrice)
			.goodsStatus(goodsStatus)
			.tags(tagIds)
			.build();
		goodsAgg.validateSelf();
		return goodsAgg;
	}

	public static GoodsAgg create(
		BizId id,
		List<Tag> tagIds,
		@NotNull Category category,
		GoodsName goodsName,
		GoodsSpec goodsSpec,
		Price goodsPrice,
		GoodsStatus goodsStatus,
		LocalDateTime createTime,
		LocalDateTime updateTime,
		String goodsNo,
		Boolean recommend ) {
		GoodsAgg goodsAgg = initBuilder()
			.id(id)
			.tags(tagIds)
			.category(category)
			.goodsName(goodsName)
			.goodsSpec(goodsSpec)
			.goodsPrice(goodsPrice)
			.goodsStatus(goodsStatus)
			.createTime(createTime)
			.updateTime(updateTime)
			.goodsNo(goodsNo)
			.recommend(recommend)
			.build();
		goodsAgg.validateSelf();
		return goodsAgg;
	}

	/**
	 * 创建初始商品
	 *
	 * @param category 商品分类ID
	 * @param goodsName 商品名称
	 * @param goodsSpec 商品规格
	 * @param goodsPrice 商品价格
	 * @param tagIds 商品标签ID集合
	 * @return 初始商品
	 */
	public static GoodsAgg createGoods(
		@NotNull Category category,
		GoodsName goodsName,
		GoodsSpec goodsSpec,
		Price goodsPrice,
		List<Tag> tagIds ) {
		GoodsAgg goodsAgg = initBuilder()
			.tags(tagIds)
			.category(category)
			.goodsName(goodsName)
			.goodsSpec(goodsSpec)
			.goodsPrice(goodsPrice)
			.goodsStatus(GoodsStatus.UNSHELVED)
			.build();
		goodsAgg.validateSelf();
		return goodsAgg;
	}

	/**
	 * 修改基础信息
	 *
	 * @param categoryId 分类ID
	 * @param goodsName 商品名称
	 * @param goodsSpec 商品规格
	 * @param goodsPrice 商品价格
	 */
	public void modifyBasicInfo(
		@NotNull Category categoryId,
		GoodsName goodsName,
		GoodsSpec goodsSpec,
		Price goodsPrice,
		List<Tag> tagIds ) {
		this.category = categoryId;
		this.goodsName = goodsName;
		this.goodsSpec = goodsSpec;
		this.goodsPrice = goodsPrice;
		this.tags = tagIds;
		this.validateSelf();
	}

	public void create() {
		GoodsAgg agg = new GoodsAgg();
		agg.id = this.id;
		GoodsCreateEvent goodsCreateEvent = new GoodsCreateEvent();
		goodsCreateEvent.setName("");
		registerEvent(goodsCreateEvent);
	}

	/**
	 * 上架商品
	 */
	public void shelve() {
		this.goodsStatus = GoodsStatus.SHELVED;
	}

	/**
	 * 下架商品
	 */
	public void unshelve() {
		this.goodsStatus = GoodsStatus.UNSHELVED;
	}

	public BizId getId() {
		return id;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public @NotNull Category getCategory() {
		return category;
	}

	public GoodsName getGoodsName() {
		return goodsName;
	}

	public GoodsSpec getGoodsSpec() {
		return goodsSpec;
	}

	public Price getGoodsPrice() {
		return goodsPrice;
	}

	public GoodsStatus getGoodsStatus() {
		return goodsStatus;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId( String templateId ) {
		this.templateId = templateId;
	}
	// 领域行为：修改运费模板
	public void changeFreightTemplate(String newTemplateId, String operatorId) {
		// 领域规则校验
		if (StringUtils.isEmpty(newTemplateId)) {
			throw new BusinessException("运费模板ID不能为空");
		}

		FreightTemplateChangedEvent freightTemplateChangedEvent = new FreightTemplateChangedEvent(
			this.id, this.templateId, newTemplateId, operatorId,
			GoodsDomainAssembler.INSTANCE.toAggSnapshot(this));
		registerEvent(freightTemplateChangedEvent);

		// 修改状态
		this.templateId = newTemplateId;
	}
	@Override
	public boolean equals( Object o ) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GoodsAgg goods = (GoodsAgg) o;
		return Objects.equals(id, goods.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Goods{"
			+ "id="
			+ id
			+ ", tags="
			+ tags
			+ ", categoryId="
			+ category
			+ ", goodsName="
			+ goodsName
			+ ", goodsSpec="
			+ goodsSpec
			+ ", goodsPrice="
			+ goodsPrice
			+ ", goodsStatus="
			+ goodsStatus
			+ ", createTime="
			+ createTime
			+ ", updateTime="
			+ updateTime
			+ '}';
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo( String goodsNo ) {
		this.goodsNo = goodsNo;
	}

	public Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend( Boolean recommend ) {
		this.recommend = recommend;
	}


	private static final class GoodsAggBuilder {

		private @NotNull BizId id;
		private List<Tag> tags;
		private @NotNull Category category;
		private @NotNull GoodsName goodsName;
		private @NotNull GoodsSpec goodsSpec;
		private @NotNull Price goodsPrice;
		private @NotNull GoodsStatus goodsStatus;
		private String goodsNo;
		private Boolean recommend;
		private String createUser;
		private String updateUser;
		private String tenantId;
		private LocalDateTime createTime;
		private LocalDateTime updateTime;
		private String sourceName;
		private String serviceId;

		public GoodsAggBuilder() {
		}

		public GoodsAggBuilder( GoodsAgg other ) {
			this.id = other.id;
			this.tags = other.tags;
			this.category = other.category;
			this.goodsName = other.goodsName;
			this.goodsSpec = other.goodsSpec;
			this.goodsPrice = other.goodsPrice;
			this.goodsStatus = other.goodsStatus;
			this.createTime = other.createTime;
			this.updateTime = other.updateTime;
			this.goodsNo = other.goodsNo;
			this.recommend = other.recommend;
			this.createUser = other.createUser;
			this.updateUser = other.updateUser;
			this.tenantId = other.tenantId;
			this.createTime = other.createTime;
			this.updateTime = other.updateTime;
			this.sourceName = other.sourceName;
			this.serviceId = other.serviceId;
		}

		public static GoodsAggBuilder aGoodsAgg() {
			return new GoodsAggBuilder();
		}

		public GoodsAggBuilder id( BizId id ) {
			this.id = id;
			return this;
		}

		public GoodsAggBuilder tags( List<Tag> tags ) {
			this.tags = tags;
			return this;
		}

		public GoodsAggBuilder category( Category category ) {
			this.category = category;
			return this;
		}

		public GoodsAggBuilder goodsName( GoodsName goodsName ) {
			this.goodsName = goodsName;
			return this;
		}

		public GoodsAggBuilder goodsSpec( GoodsSpec goodsSpec ) {
			this.goodsSpec = goodsSpec;
			return this;
		}

		public GoodsAggBuilder goodsPrice( Price goodsPrice ) {
			this.goodsPrice = goodsPrice;
			return this;
		}

		public GoodsAggBuilder goodsStatus( GoodsStatus goodsStatus ) {
			this.goodsStatus = goodsStatus;
			return this;
		}

		public GoodsAggBuilder createTime( LocalDateTime createTime ) {
			this.createTime = createTime;
			return this;
		}

		public GoodsAggBuilder updateTime( LocalDateTime updateTime ) {
			this.updateTime = updateTime;
			return this;
		}

		public GoodsAggBuilder goodsNo( String goodsNo ) {
			this.goodsNo = goodsNo;
			return this;
		}

		public GoodsAggBuilder recommend( Boolean recommend ) {
			this.recommend = recommend;
			return this;
		}

		public GoodsAggBuilder createUser( String createUser ) {
			this.createUser = createUser;
			return this;
		}

		public GoodsAggBuilder updateUser( String updateUser ) {
			this.updateUser = updateUser;
			return this;
		}

		public GoodsAggBuilder tenantId( String tenantId ) {
			this.tenantId = tenantId;
			return this;
		}


		public GoodsAggBuilder sourceName( String sourceName ) {
			this.sourceName = sourceName;
			return this;
		}

		public GoodsAggBuilder serviceId( String serviceId ) {
			this.serviceId = serviceId;
			return this;
		}

		public GoodsAggBuilder but() {
			return aGoodsAgg().id(id).tags(tags).category(category).goodsName(goodsName).goodsSpec(goodsSpec)
				.goodsPrice(goodsPrice).goodsStatus(goodsStatus).createTime(createTime).updateTime(updateTime)
				.goodsNo(goodsNo).recommend(recommend).createUser(createUser).updateUser(updateUser).tenantId(tenantId)
				.createTime(createTime).updateTime(updateTime).sourceName(sourceName).serviceId(serviceId)
				;
		}

		public GoodsAgg build() {
			GoodsAgg goodsAgg = new GoodsAgg();
			goodsAgg.setId(id);
			goodsAgg.setGoodsNo(goodsNo);
			goodsAgg.setRecommend(recommend);
			goodsAgg.setCreateUser(createUser);
			goodsAgg.setUpdateUser(updateUser);
			goodsAgg.setTenantId(tenantId);
			goodsAgg.setCreateTime(createTime);
			goodsAgg.setUpdateTime(updateTime);
			goodsAgg.setSourceName(sourceName);
			goodsAgg.setServiceId(serviceId);
			goodsAgg.setId(id);
			goodsAgg.goodsStatus = this.goodsStatus;
			goodsAgg.goodsPrice = this.goodsPrice;
			goodsAgg.goodsSpec = this.goodsSpec;
			goodsAgg.createTime = this.createTime;
			goodsAgg.updateTime = this.updateTime;
			goodsAgg.tags = this.tags;
			goodsAgg.goodsName = this.goodsName;
			goodsAgg.category = this.category;
			return goodsAgg;
		}
	}
}
