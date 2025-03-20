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

package com.taotao.cloud.goods.application.dto.category.clientobject;

import com.taotao.boot.ddd.model.application.dto.ClientObject;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 商品分类
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "商品分类CO")
public class CategoryCO extends ClientObject {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = " 父id, 根节点为0")
    private Long parentId;

    @Schema(description = "层级, 从0开始")
    private Integer level;

    @Schema(description = "排序值")
    private Integer sortOrder;

    @Schema(description = "佣金比例")
    private BigDecimal commissionRate;

    @Schema(description = "分类图标")
    private String image;

    @Schema(description = "是否支持频道")
    private Boolean supportChannel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getSupportChannel() {
        return supportChannel;
    }

    public void setSupportChannel(Boolean supportChannel) {
        this.supportChannel = supportChannel;
    }
}
