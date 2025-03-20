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

package com.taotao.cloud.goods.application.dto.store.clientobject;

import com.taotao.boot.ddd.model.application.dto.ClientObject;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 店铺分类CO
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-14 21:52:23
 */
@Setter
@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class StoreGoodsLabelCO extends ClientObject {

    @Serial
    private static final long serialVersionUID = -7605952923416404638L;

    @Schema(description = "店铺商品分类ID")
    private Long id;

    @Schema(description = "店铺商品分类名称")
    private String labelName;

    @Schema(description = "层级, 从0开始")
    private Integer level;

    @Schema(description = "店铺商品分类排序")
    private Integer sortOrder;

    @Schema(description = "下级分类列表")
    private List<StoreGoodsLabelCO> children;

    public StoreGoodsLabelCO(Long id, String labelName, Integer level, Integer sortOrder) {
        this.id = id;
        this.labelName = labelName;
        this.level = level;
        this.sortOrder = sortOrder;
    }

    /**
     * 获取
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取
     * @return labelName
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * 设置
     * @param labelName
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
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
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置
     * @param sortOrder
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 获取
     * @return children
     */
    public List<StoreGoodsLabelCO> getChildren() {
        return children;
    }

    /**
     * 设置
     * @param children
     */
    public void setChildren(List<StoreGoodsLabelCO> children) {
        this.children = children;
    }

    public String toString() {
        return "StoreGoodsLabelCO{serialVersionUID = " + serialVersionUID + ", id = " + id + ", labelName = "
                + labelName + ", level = " + level + ", sortOrder = " + sortOrder + ", children = " + children + "}";
    }
}
