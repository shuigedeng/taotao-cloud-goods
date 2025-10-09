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

package com.taotao.cloud.goods.application.dto.own.goods.command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import lombok.*;

/**
 * 创建商品Command
 *
 */
@Setter
@Getter
@ToString
public class GoodsCreateCommand {

    /**
     * 所属分类ID
     */
    private Long categoryId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 生产日期
     */
    private LocalDate manufactureDate;

    /**
     * 过期日期
     */
    private LocalDate expirationDate;

    /**
     * 商品重量
     */
    private BigDecimal goodsWeight;

    /**
     * 商品重量单位
     */
    private String goodsWeightUnit;

    /**
     * 商品介绍
     */
    private String goodsDesc;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品标签ID集合
     */
    private Set<Long> tagIds;

    /**
     * 创建商品
     *
     * @return 商品
     */
    //    public GoodsAgg createGoods() {
    //        return GoodsFactory.createGoods(
    //                BizId.fromValue(this.getCategoryId()),
    //                GoodsName.of(this.getGoodsName()),
    //                GoodsSpec.builder()
    //                        .manufactureDate(this.getManufactureDate())
    //                        .expirationDate(this.getExpirationDate())
    //                        .goodsWeight(GoodsWeight.of(this.getGoodsWeight(),
    // WeightUnit.of(this.getGoodsWeightUnit())))
    //                        .goodsDesc(this.getGoodsDesc())
    //                        .build(),
    //                Price.of(this.getGoodsPrice()),
    //                BizId.fromNullableValues(tagIds)
    //        );
    //
    //    }
}
