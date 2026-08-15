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

package com.taotao.cloud.goods.infrastructure.assembler;

import com.taotao.boot.common.model.ddd.types.MarkerAssembler;
import com.taotao.cloud.goods.application.dto.goods.query.GoodsPageQuery;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsResult;
import com.taotao.cloud.goods.domain.aggregate.GoodsAgg;
import com.taotao.cloud.goods.infrastructure.persistent.model.dos.GoodsDO;
import com.taotao.cloud.goods.infrastructure.persistent.model.params.GoodsParam;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.GoodsPO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 商品基础设施装配器
 * <p>
 * 负责 GoodsPO/GoodsDO 与聚合根 GoodsAgg、DTO 之间的转换
 * </p>
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:58:21
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GoodsInfraAssembler extends BaseInfraAssembler, MarkerAssembler {

	GoodsInfraAssembler INSTANCE = Mappers.getMapper(GoodsInfraAssembler.class);

	/**
	 * 创建商品Agg
	 *
	 * @return 无返回值
	 * @since 2022.03
	 */
	@ObjectFactory
	default GoodsAgg createGoodsAgg() {
		return GoodsAgg.init();
	}

	GoodsPO toPo( GoodsAgg source );

	GoodsAgg toAgg( GoodsPO source );

	GoodsResult toResult( GoodsPO source );

	List<GoodsResult> toResult( List<GoodsPO> source );

	GoodsResult toResult( GoodsDO source );

	GoodsParam toParam( GoodsPageQuery goodsPageQuery );

	GoodsAgg copyFrom( GoodsAgg source );

	void mergeFromAgg( GoodsAgg source, @MappingTarget GoodsAgg target );

	void mergeFromPo( GoodsPO source, @MappingTarget GoodsAgg target );
}

