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

import com.taotao.boot.common.model.ddd.types.MarkerResult;
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

@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "商品分类CO")
public class CategoryResult implements MarkerResult {

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

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */

    public Long getId() {
        return id;
    }

    /**
     * 设置
     *
     * @param id id
     * @return 无返回值
     * @since 2022.03
     */

    public void setId(Long id) {
        this.id = id;
    }

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
}
