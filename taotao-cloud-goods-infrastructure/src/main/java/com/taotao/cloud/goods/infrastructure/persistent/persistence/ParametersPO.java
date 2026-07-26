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
 * 商品参数表
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
        name = ParametersPO.TABLE_NAME,
        indexes = {
            @Index(name = "idx_create_time", columnList = "`create_time`"),
        })
@TableName(ParametersPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = ParametersPO.TABLE_NAME)
public class ParametersPO extends BasePO<ParametersPO> {

    public static final String TABLE_NAME = "ttc_parameters";

    /** 参数名称 */
    @Column(name = "`param_name`", columnDefinition = "varchar(255) not null comment '参数名称'")
    private String paramName;

    /** 选择值 */
    @Column(name = "`options`", columnDefinition = "varchar(255) not null comment '选择值'")
    private String options;

    /** 是否可索引，0 不显示 1 显示 */
    @Column(
            name = "`is_index`",
            columnDefinition = "int not null default 1 comment '是否可索引，0 不显示 1 显示'")
    private Integer isIndex;

    /** 是否必填 是1否0 */
    @Column(name = "`required`", columnDefinition = "int not null comment '是否必填 是1否0'")
    private Integer required;

    /** 参数分组id */
    @Column(name = "`group_id`", columnDefinition = "bigint not null comment '参数分组id'")
    private Long groupId;

    /** 分类id */
    @Column(name = "`category_id`", columnDefinition = "bigint not null comment '分类id'")
    private Long categoryId;

    /** 排序 */
    @Column(name = "`sort`", columnDefinition = "int not null comment '排序'")
    private Integer sort;

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * 设置
     *
     * @param paramName paramName
     * @return 无返回值
     * @since 2022.03
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getOptions() {
        return options;
    }

    /**
     * 设置
     *
     * @param options options
     * @return 无返回值
     * @since 2022.03
     */
    public void setOptions(String options) {
        this.options = options;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getIsIndex() {
        return isIndex;
    }

    /**
     * 设置
     *
     * @param isIndex isIndex
     * @return 无返回值
     * @since 2022.03
     */
    public void setIsIndex(Integer isIndex) {
        this.isIndex = isIndex;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getRequired() {
        return required;
    }

    /**
     * 设置
     *
     * @param required required
     * @return 无返回值
     * @since 2022.03
     */
    public void setRequired(Integer required) {
        this.required = required;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * 设置
     *
     * @param groupId groupId
     * @return 无返回值
     * @since 2022.03
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

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
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置
     *
     * @param sort sort
     * @return 无返回值
     * @since 2022.03
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        ParametersPO that = (ParametersPO) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
