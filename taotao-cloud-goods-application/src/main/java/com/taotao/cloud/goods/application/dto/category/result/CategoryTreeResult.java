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

package com.taotao.cloud.goods.application.dto.category.result;

import com.taotao.cloud.goods.application.dto.brand.result.BrandResult;
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
@ToString(callSuper = true)
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class CategoryTreeResult extends CategoryResult {

    private static final long serialVersionUID = 3775766246075838410L;

    @Schema(description = "父节点名称")
    private String parentTitle;

    @Schema(description = "子分类列表")
    private List<CategoryTreeResult> children;

    @Schema(description = "分类关联的品牌列表")
    private List<BrandResult> brandList;

    public List<CategoryTreeResult> getChildren() {
        if (children != null) {
            children.sort(Comparator.comparing(CategoryResult::getSortOrder));
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
    public void setChildren(List<CategoryTreeResult> children) {
        this.children = children;
    }

    /**
     * 获取
     * @return brandList
     */
    public List<BrandResult> getBrandList() {
        return brandList;
    }

    /**
     * 设置
     * @param brandList
     */
    public void setBrandList(List<BrandResult> brandList) {
        this.brandList = brandList;
    }

}
