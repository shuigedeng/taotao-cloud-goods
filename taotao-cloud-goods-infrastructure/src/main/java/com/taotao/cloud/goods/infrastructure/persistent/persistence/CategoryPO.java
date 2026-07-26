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
import java.math.BigDecimal;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

/**
 * 商品分类表
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
        name = CategoryPO.TABLE_NAME,
        indexes = {
            @Index(name = "idx_create_time", columnList = "`create_time`"),
            @Index(name = "idx_sort_order", columnList = "`sort_order`"),
        })
@TableName(CategoryPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = CategoryPO.TABLE_NAME)
public class CategoryPO extends BasePO<CategoryPO> {

    public static final String TABLE_NAME = "ttc_category";

    /** 分类名称 */
    @Column(name = "`name`", columnDefinition = "varchar(255) not null comment '分类名称'")
    private String name;

    /** 父id, 根节点为0 */
    @Column(name = "`parent_id`", columnDefinition = "bigint not null comment '父id, 根节点为0'")
    private Long parentId;

    /** 层级, 从0开始 */
    @Column(name = "`level`", columnDefinition = "int not null default 0 comment '层级, 从0开始'")
    private Integer level;

    /** 排序值 */
    @Column(name = "`sort_order`", columnDefinition = "int not null default 0 comment '排序值'")
    private Integer sortOrder;

    /** 佣金比例 */
    @Column(name = "`commission_rate`", columnDefinition = "decimal(10,2) not null comment '佣金比例'")
    private BigDecimal commissionRate;

    /** 分类图标 */
    @Column(name = "`image`", columnDefinition = "varchar(255) not null comment '分类图标'")
    private String image;

    /** 是否支持频道 */
    @Column(
            name = "`support_channel`",
            columnDefinition = "boolean null default false comment '是否支持频道'")
    private Boolean supportChannel;

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     *
     * @param name name
     * @return 无返回值
     * @since 2022.03
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置
     *
     * @param parentId parentId
     * @return 无返回值
     * @since 2022.03
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置
     *
     * @param level level
     * @return 无返回值
     * @since 2022.03
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置
     *
     * @param sortOrder sortOrder
     * @return 无返回值
     * @since 2022.03
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 获取
     *
     * @return BigDecimal
     * @since 2022.03
     */
    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    /**
     * 设置
     *
     * @param commissionRate commissionRate
     * @return 无返回值
     * @since 2022.03
     */
    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置
     *
     * @param image image
     * @return 无返回值
     * @since 2022.03
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取
     *
     * @return 是否成功
     * @since 2022.03
     */
    public Boolean getSupportChannel() {
        return supportChannel;
    }

    /**
     * 设置
     *
     * @param supportChannel supportChannel
     * @return 无返回值
     * @since 2022.03
     */
    public void setSupportChannel(Boolean supportChannel) {
        this.supportChannel = supportChannel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        CategoryPO categoryPO = (CategoryPO) o;
        return getId() != null && Objects.equals(getId(), categoryPO.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
