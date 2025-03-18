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

import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.cloud.goods.domain.goods.aggregate.GoodsAgg;
import com.taotao.cloud.goods.domain.goods.valobj.GoodsName;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.GoodsPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * IGoodsSkuMapStruct
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:58:21
 */
@Mapper
public interface GoodsInfraAssembler {

    /** 实例 */
    GoodsInfraAssembler INSTANCE = Mappers.getMapper(GoodsInfraAssembler.class);

    GoodsPO convert(GoodsAgg goods);

	default Long map(BizId value) {
		return value != null ? value.id() : null;
	}
	default String map(GoodsName value) {
		return value != null ? value.getValue() : null;
	}
}
