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

import com.taotao.boot.ddd.model.domain.AggregateRoot;
import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.cloud.goods.domain.valobj.CategoryDesc;
import com.taotao.cloud.goods.domain.valobj.CategoryName;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 商品分类
 *
 * @author luohq
 * @date 2023-01-04 13:20
 */
public class CategoryAgg extends AggregateRoot<BizId> {

    /**
     * 分类ID
     */
    @NotNull private BizId id;

    /**
     * 上级分类ID
     */
    private BizId parentCategoryId;

    /**
     * 分类名称
     */
    @NotNull private CategoryName categoryName;

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

    CategoryAgg() {}

    /**
     * 创建分类信息
     *
     * @param id               分类ID
     * @param parentCategoryId 上级分类ID
     * @param categoryName     分类名称
     * @param categoryDesc     分类描述
     */
    CategoryAgg(
            BizId id,
            BizId parentCategoryId,
            CategoryName categoryName,
            CategoryDesc categoryDesc) {
        this.id = id;
        this.parentCategoryId = parentCategoryId;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.createTime = LocalDateTime.now();
        this.updateTime = this.createTime;
        this.validateSelf();
    }

    public CategoryAgg(
            BizId id,
            BizId parentCategoryId,
            CategoryName categoryName,
            CategoryDesc categoryDesc,
            LocalDateTime createTime,
            LocalDateTime updateTime) {
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
     * @param categoryName     分类名称
     * @param categoryDesc     分类描述
     */
    public void modifyBasicInfo(
            BizId parentCategoryId, CategoryName categoryName, CategoryDesc categoryDesc) {
        this.parentCategoryId = parentCategoryId;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.updateTime = LocalDateTime.now();
        this.validateSelf();
    }

    @Override
    public BizId getId() {
        return id;
    }

    public BizId getParentCategoryId() {
        return parentCategoryId;
    }
    public Long parentCategoryId() {
        return parentCategoryId.getId();
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }
    public String categoryName() {
        return categoryName.getValue();
    }

    public CategoryDesc getCategoryDesc() {
        return categoryDesc;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

	@Override
	public void setId(BizId id) {
		this.id = id;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CategoryAgg category = (CategoryAgg) o;
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
