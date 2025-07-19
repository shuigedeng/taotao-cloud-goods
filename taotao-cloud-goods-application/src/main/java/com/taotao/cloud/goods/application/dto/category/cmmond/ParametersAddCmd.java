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

package com.taotao.cloud.goods.application.dto.category.cmmond;

import com.taotao.boot.ddd.model.application.dto.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.*;
import lombok.experimental.Accessors;

/** 商品参数 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class ParametersAddCmd extends Command {

    @Serial private static final long serialVersionUID = 724427321881170297L;

    @Schema(description = "参数名称")
    private String paramName;

    @Schema(description = "选择值")
    private String options;

    @Schema(description = "是否可索引，0 不显示 1 显示")
    private Integer isIndex;

    @Schema(description = "是否必填 是1否0")
    private Integer required;

    @Schema(description = "参数分组id")
    private Long groupId;

    @Schema(description = "分类id")
    private Long categoryId;

    @Schema(description = "排序")
    private Integer sort;

    /**
     * 获取
     * @return paramName
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * 设置
     * @param paramName
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * 获取
     * @return options
     */
    public String getOptions() {
        return options;
    }

    /**
     * 设置
     * @param options
     */
    public void setOptions(String options) {
        this.options = options;
    }

    /**
     * 获取
     * @return isIndex
     */
    public Integer getIsIndex() {
        return isIndex;
    }

    /**
     * 设置
     * @param isIndex
     */
    public void setIsIndex(Integer isIndex) {
        this.isIndex = isIndex;
    }

    /**
     * 获取
     * @return required
     */
    public Integer getRequired() {
        return required;
    }

    /**
     * 设置
     * @param required
     */
    public void setRequired(Integer required) {
        this.required = required;
    }

    /**
     * 获取
     * @return groupId
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * 设置
     * @param groupId
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取
     * @return categoryId
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 设置
     * @param categoryId
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String toString() {
        return "ParametersAddCmd{serialVersionUID = "
                + serialVersionUID
                + ", paramName = "
                + paramName
                + ", options = "
                + options
                + ", isIndex = "
                + isIndex
                + ", required = "
                + required
                + ", groupId = "
                + groupId
                + ", categoryId = "
                + categoryId
                + ", sort = "
                + sort
                + "}";
    }
}
