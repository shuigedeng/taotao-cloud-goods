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

package com.taotao.cloud.goods.application.dto.own.draft.result;

import com.taotao.cloud.goods.application.dto.own.goods.result.GoodsSkuSpecGalleryResult;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.util.List;
import lombok.*;
import lombok.experimental.Accessors;

/** 草稿商品CO */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class DraftGoodsSkuParamsResult extends DraftGoodsResult {

    @Serial private static final long serialVersionUID = 6377623919990713567L;

    @Schema(description = "分类名称")
    private List<String> categoryName;

    // @Schema(description = "商品参数")
    // private List<GoodsParamsDTO> goodsParamsDTOList;

    @Schema(description = "商品图片")
    private List<String> goodsGalleryList;

    @Schema(description = "sku列表")
    private List<GoodsSkuSpecGalleryResult> skuList;

    /**
     * 获取
     * @return categoryName
     */
    public List<String> getCategoryName() {
        return categoryName;
    }

    /**
     * 设置
     * @param categoryName
     */
    public void setCategoryName(List<String> categoryName) {
        this.categoryName = categoryName;
    }

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
    public List<GoodsSkuSpecGalleryResult> getSkuList() {
        return skuList;
    }

    /**
     * 设置
     * @param skuList
     */
    public void setSkuList(List<GoodsSkuSpecGalleryResult> skuList) {
        this.skuList = skuList;
    }
}
