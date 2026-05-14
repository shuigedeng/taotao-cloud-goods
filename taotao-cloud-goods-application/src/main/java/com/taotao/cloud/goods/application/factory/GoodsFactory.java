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

import com.taotao.boot.common.model.ddd.types.MarkerFactory;
import com.taotao.boot.common.support.asserts.BusinessAssert;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.boot.ddd.model.val.Price;
import com.taotao.cloud.goods.application.dto.goods.command.CreateGoodsCommand;
import com.taotao.cloud.goods.domain.aggregate.GoodsAgg;
import com.taotao.cloud.goods.domain.entity.Category;
import com.taotao.cloud.goods.domain.entity.Tag;
import com.taotao.cloud.goods.domain.valobj.*;

/**
 * 商品创建工厂<br/> 注：领域对象创建工厂，强调初始创建领域对象的操作（区别于技术层面的构造函数）
 */
public class GoodsFactory implements MarkerFactory {

	/**
	 * 根据创建命令创建初始商品
	 *
	 * @param createGoodsCommand 商品创建命令
	 * @return 商品
	 */
	public static GoodsAgg createGoods( CreateGoodsCommand createGoodsCommand ) {

		BusinessAssert.notNull(createGoodsCommand, "goodsCreateCommand is null");

		GoodsWeight goodsWeight = GoodsWeightBuilder
			.builder()
			.weight(createGoodsCommand.goodsWeight())
			.unit(WeightUnit.of(createGoodsCommand.goodsWeightUnit()))
			.build();

		GoodsSpec goodsSpec = GoodsSpecBuilder
			.builder()
			.manufactureDate(createGoodsCommand.manufactureDate())
			.expirationDate(createGoodsCommand.expirationDate())
			.goodsWeight(goodsWeight)
			.goodsDesc(createGoodsCommand.goodsDesc())
			.build();

		return GoodsAgg.create(
			BizId.newBizId(),
			Category.bizId(BizId.fromValue(createGoodsCommand.categoryId())),
			GoodsName.of(createGoodsCommand.goodsName()),
			goodsSpec,
			Price.of(createGoodsCommand.goodsPrice()),
			GoodsStatus.UNSHELVED,
			Tag.bizIds(BizId.fromNullableValues(createGoodsCommand.tagIds())));
	}
}
