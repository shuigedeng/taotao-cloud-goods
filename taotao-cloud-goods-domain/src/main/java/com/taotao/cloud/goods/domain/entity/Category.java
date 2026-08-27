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

package com.taotao.cloud.goods.domain.entity;

import com.taotao.boot.ddd.model.domain.Entity;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.goods.domain.valobj.CategoryDesc;
import com.taotao.cloud.goods.domain.valobj.CategoryName;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 商品分类
 *
 * @author shuigedeng
 * @since 2023-01-04 13:20
 */
public class Category implements Entity {

	/**
	 * 分类ID
	 */
	@NotNull
	private BizId id;

	/**
	 * 上级分类ID
	 */
	private BizId parentCategoryId;

	/**
	 * 分类名称
	 */
	@NotNull
	private CategoryName categoryName;

	/**
	 * 分类描述
	 */
	private CategoryDesc categoryDesc;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;

	public Category() {
	}

	/**
	 * 创建分类信息
	 *
	 * @param id 分类ID
	 * @param parentCategoryId 上级分类ID
	 * @param categoryName 分类名称
	 * @param categoryDesc 分类描述
	 */
	public Category(
		BizId id,
		BizId parentCategoryId,
		CategoryName categoryName,
		CategoryDesc categoryDesc ) {
		this.id = id;
		this.parentCategoryId = parentCategoryId;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.createTime = LocalDateTime.now();
		this.updateTime = this.createTime;
		this.validateSelf();
	}

	public Category( BizId id ) {
		this.id = id;
	}

	/**
	 * bizId 方法
	 *
	 * @param bizId bizId
	 * @return 分类
	 * @since 2022.03
	 */
	public static Category bizId( BizId bizId ) {
		return new Category(bizId);
	}

	public Category(
		BizId id,
		BizId parentCategoryId,
		CategoryName categoryName,
		CategoryDesc categoryDesc,
		LocalDateTime createTime,
		LocalDateTime updateTime ) {
		this.id = id;
		this.parentCategoryId = parentCategoryId;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.validateSelf();
	}

	/**
	 * 修改基础信息
	 *
	 * @param parentCategoryId 上级分类ID
	 * @param categoryName 分类名称
	 * @param categoryDesc 分类描述
	 */
	public void modifyBasicInfo(
		BizId parentCategoryId, CategoryName categoryName, CategoryDesc categoryDesc ) {
		this.parentCategoryId = parentCategoryId;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.updateTime = LocalDateTime.now();
		this.validateSelf();
	}

    /**
	 * 获取
	 *
	 * @return BizId
	 * @since 2022.03
	 */
	public BizId getId() {
		return id;
	}

    /**
	 * 获取
	 *
	 * @return BizId
	 * @since 2022.03
	 */
	public BizId getParentCategoryId() {
		return parentCategoryId;
	}

	/**
	 * 父级分类ID
	 *
	 * @return 结果数量
	 * @since 2022.03
	 */
	public Long parentCategoryId() {
		return parentCategoryId.id();
	}

    /**
	 * 获取
	 *
	 * @return CategoryName
	 * @since 2022.03
	 */
	public CategoryName getCategoryName() {
		return categoryName;
	}

	/**
	 * 分类名称
	 *
	 * @return 字符串
	 * @since 2022.03
	 */
	public String categoryName() {
		return categoryName.value();
	}

    /**
	 * 获取
	 *
	 * @return CategoryDesc
	 * @since 2022.03
	 */
	public CategoryDesc getCategoryDesc() {
		return categoryDesc;
	}

    /**
	 * 获取
	 *
	 * @return LocalDateTime
	 * @since 2022.03
	 */
	public LocalDateTime getCreateTime() {
		return createTime;
	}

    /**
	 * 获取
	 *
	 * @return LocalDateTime
	 * @since 2022.03
	 */
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

    /**
	 * 设置
	 *
	 * @param id id
	 * @since 2022.03
	 */
	public void setId( BizId id ) {
		this.id = id;
	}

    /**
	 * 设置
	 *
	 * @param parentCategoryId parentCategoryId
	 * @since 2022.03
	 */
	public void setParentCategoryId( BizId parentCategoryId ) {
		this.parentCategoryId = parentCategoryId;
	}

    /**
	 * 设置
	 *
	 * @param categoryName categoryName
	 * @since 2022.03
	 */
	public void setCategoryName( CategoryName categoryName ) {
		this.categoryName = categoryName;
	}

    /**
	 * 设置
	 *
	 * @param categoryDesc categoryDesc
	 * @since 2022.03
	 */
	public void setCategoryDesc( CategoryDesc categoryDesc ) {
		this.categoryDesc = categoryDesc;
	}

    /**
	 * 设置
	 *
	 * @param createTime createTime
	 * @since 2022.03
	 */
	public void setCreateTime( LocalDateTime createTime ) {
		this.createTime = createTime;
	}

    /**
	 * 设置
	 *
	 * @param updateTime updateTime
	 * @since 2022.03
	 */
	public void setUpdateTime( LocalDateTime updateTime ) {
		this.updateTime = updateTime;
	}

	@Override
	public boolean equals( Object o ) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Category category = (Category) o;
		return Objects.equals(id, category.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Category{"
			+ "id="
			+ id
			+ ", parentCategoryId="
			+ parentCategoryId
			+ ", categoryName="
			+ categoryName
			+ ", categoryDesc="
			+ categoryDesc
			+ ", createTime="
			+ createTime
			+ ", updateTime="
			+ updateTime
			+ '}';
	}
}
