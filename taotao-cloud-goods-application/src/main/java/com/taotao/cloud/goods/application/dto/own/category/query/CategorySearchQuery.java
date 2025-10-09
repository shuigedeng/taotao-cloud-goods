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

package com.taotao.cloud.goods.application.dto.own.category.query;

import com.taotao.boot.ddd.model.application.dto.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import java.io.Serial;
import java.math.BigDecimal;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 分类查询参数
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:33:05
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class CategorySearchQuery extends Query {

    @Serial private static final long serialVersionUID = -7605952923416404638L;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "父id")
    private String parentId;

    @Schema(description = "层级")
    private Integer level;

    @Schema(description = "排序值")
    private BigDecimal sortOrder;

    @Schema(description = "佣金比例")
    @Digits(integer = 9, fraction = 2, message = "佣金比例格式不正确")
    @DecimalMin(value = "0.00", message = "佣金比例最小为0.00")
    @DecimalMax(value = "1.00", message = "佣金比例最大为1.00")
    private BigDecimal commissionRate;

    @Schema(description = "父节点名称")
    private String parentTitle;

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取
     * @return level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取
     * @return sortOrder
     */
    public BigDecimal getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置
     * @param sortOrder
     */
    public void setSortOrder(BigDecimal sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 获取
     * @return commissionRate
     */
    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    /**
     * 设置
     * @param commissionRate
     */
    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    /**
     * 获取
     * @return parentTitle
     */
    public String getParentTitle() {
        return parentTitle;
    }

    /**
     * 设置
     * @param parentTitle
     */
    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }
}
