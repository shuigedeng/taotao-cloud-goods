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

package com.taotao.cloud.goods.application.dto.draft.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.*;
import lombok.experimental.Accessors;

/** 草稿商品DTO */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class DraftGoodsSkuParamsUpdateCommand extends DraftGoodsBaseCommand {

    private static final long serialVersionUID = 5255666163196674178L;

    // @Valid
    // @Schema(description = "商品参数")
    // private List<GoodsParamsDTO> goodsParamsDTOList;

    @Schema(description = "商品图片")
    private List<String> goodsGalleryList;

    @Valid
    @Schema(description = "sku列表")
    private List<Map<String, Object>> skuList;

    /**
     * 获取
     * @return goodsGalleryList
     */
    public List<String> getGoodsGalleryList() {
        return goodsGalleryList;
    }

    /**
     * 设置
     * @param goodsGalleryList
     */
    public void setGoodsGalleryList(List<String> goodsGalleryList) {
        this.goodsGalleryList = goodsGalleryList;
    }

    /**
     * 获取
     * @return skuList
     */
    public List<Map<String, Object>> getSkuList() {
        return skuList;
    }

    /**
     * 设置
     * @param skuList
     */
    public void setSkuList(List<Map<String, Object>> skuList) {
        this.skuList = skuList;
    }
}
