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

package com.taotao.cloud.goods.application.factory;

import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.boot.ddd.model.types.Price;
import com.taotao.cloud.goods.application.dto.goods.cmmond.GoodsCreateCommand;
import com.taotao.cloud.goods.domain.aggregate.GoodsAgg;
import com.taotao.cloud.goods.domain.valobj.GoodsName;
import com.taotao.cloud.goods.domain.valobj.GoodsSpec;
import com.taotao.cloud.goods.domain.valobj.GoodsStatus;
import com.taotao.cloud.goods.domain.valobj.GoodsWeight;
import com.taotao.cloud.goods.domain.valobj.WeightUnit;
import java.util.Set;

/**
 * 商品创建工厂<br/> 注：领域对象创建工厂，强调初始创建领域对象的操作（区别于技术层面的构造函数）
 */
public class GoodsFactory {

    /**
     * 创建初始商品
     *
     * @param categoryId 商品分类ID
     * @param goodsName  商品名称
     * @param goodsSpec  商品规格
     * @param goodsPrice 商品价格
     * @param tagIds     商品标签ID集合
     * @return 初始商品
     */
    public static GoodsAgg createGoods(
            BizId categoryId,
            GoodsName goodsName,
            GoodsSpec goodsSpec,
            Price goodsPrice,
            Set<BizId> tagIds) {
        return new GoodsAgg(
                BizId.newBizId(),
                categoryId,
                goodsName,
                goodsSpec,
                goodsPrice,
                GoodsStatus.UNSHELVED,
                tagIds);
    }

    /**
     * 根据创建命令创建初始商品
     *
     * @param goodsCreateCommand 商品创建命令
     * @return 初始商品
     */
    public static GoodsAgg createGoods(GoodsCreateCommand goodsCreateCommand) {
        return new GoodsAgg(
                BizId.newBizId(),
                BizId.fromValue(goodsCreateCommand.getCategoryId()),
                GoodsName.of(goodsCreateCommand.getGoodsName()),
                GoodsSpec.builder()
                        .manufactureDate(goodsCreateCommand.getManufactureDate())
                        .expirationDate(goodsCreateCommand.getExpirationDate())
                        .goodsWeight(
                                GoodsWeight.builder()
                                        .weight(goodsCreateCommand.getGoodsWeight())
                                        .unit(
                                                WeightUnit.of(
                                                        goodsCreateCommand.getGoodsWeightUnit()))
                                        .build())
                        .goodsDesc(goodsCreateCommand.getGoodsDesc())
                        .build(),
                Price.of(goodsCreateCommand.getGoodsPrice()),
                GoodsStatus.UNSHELVED,
                BizId.fromNullableValues(goodsCreateCommand.getTagIds()));
    }
}
