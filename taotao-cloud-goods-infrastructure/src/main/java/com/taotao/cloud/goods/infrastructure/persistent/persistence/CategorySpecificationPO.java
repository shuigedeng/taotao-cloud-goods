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

package com.taotao.cloud.goods.infrastructure.persistent.persistence;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.webagg.entity.BasePO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

/**
 * 商品分类规格关联表
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-20 16:59:38
 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = CategorySpecificationPO.TABLE_NAME,
        indexes = {
            @Index(name = "idx_create_time", columnList = "`create_time`"),
        })
@TableName(CategorySpecificationPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = CategorySpecificationPO.TABLE_NAME)
public class CategorySpecificationPO extends BasePO<CategorySpecificationPO> {

    public static final String TABLE_NAME = "ttc_category_specification";

    /** 分类id */
    @Column(name = "`category_id`", columnDefinition = "bigint not null comment '分类id'")
    private Long categoryId;

    /** 规格id */
    @Column(name = "`specification_id`", columnDefinition = "bigint not null comment '规格id'")
    private Long specificationId;

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 设置
     *
     * @param categoryId categoryId
     * @return 无返回值
     * @since 2022.03
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getSpecificationId() {
        return specificationId;
    }

    /**
     * 设置
     *
     * @param specificationId specificationId
     * @return 无返回值
     * @since 2022.03
     */
    public void setSpecificationId(Long specificationId) {
        this.specificationId = specificationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        CategorySpecificationPO that = (CategorySpecificationPO) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
