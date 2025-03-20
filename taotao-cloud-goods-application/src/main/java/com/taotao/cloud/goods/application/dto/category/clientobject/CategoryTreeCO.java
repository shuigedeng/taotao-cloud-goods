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

import com.taotao.cloud.goods.application.dto.brand.clientobject.BrandCO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Comparator;
import java.util.List;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 分类CO
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class CategoryTreeCO extends CategoryCO {

    private static final long serialVersionUID = 3775766246075838410L;

    @Schema(description = "父节点名称")
    private String parentTitle;

    @Schema(description = "子分类列表")
    private List<CategoryTreeCO> children;

    @Schema(description = "分类关联的品牌列表")
    private List<BrandCO> brandList;

    public List<CategoryTreeCO> getChildren() {
        if (children != null) {
            children.sort(Comparator.comparing(CategoryCO::getSortOrder));
            return children;
        }
        return null;
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

    /**
     * 设置
     * @param children
     */
    public void setChildren(List<CategoryTreeCO> children) {
        this.children = children;
    }

    /**
     * 获取
     * @return brandList
     */
    public List<BrandCO> getBrandList() {
        return brandList;
    }

    /**
     * 设置
     * @param brandList
     */
    public void setBrandList(List<BrandCO> brandList) {
        this.brandList = brandList;
    }

    public String toString() {
        return "CategoryTreeCO{serialVersionUID = " + serialVersionUID + ", parentTitle = " + parentTitle
                + ", children = " + children + ", brandList = " + brandList + "}";
    }
}
