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

package com.taotao.cloud.goods.domain.assembler;

import com.taotao.boot.common.model.ddd.types.MarkerAssembler;
import com.taotao.cloud.goods.domain.aggregate.GoodsAgg;
import com.taotao.cloud.goods.domain.event.GoodsAggSnapshotEvent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * 商品领域装配器
 * <p>
 * 负责领域对象之间的转换，如聚合根快照的生成
 * </p>
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GoodsDomainAssembler extends MarkerAssembler {
	GoodsDomainAssembler INSTANCE = Mappers.getMapper(GoodsDomainAssembler.class);
	GoodsAggSnapshotEvent toAggSnapshot(GoodsAgg goodsAgg);
}
