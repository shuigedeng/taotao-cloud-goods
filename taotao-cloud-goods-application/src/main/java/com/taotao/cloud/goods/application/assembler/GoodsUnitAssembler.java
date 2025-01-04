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

package com.taotao.cloud.goods.application.assembler;

import com.taotao.cloud.goods.application.dto.goods.clientobject.GoodsUnitCO;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.GoodsUnitPO;
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
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GoodsUnitAssembler {

    /** 实例 */
    GoodsUnitAssembler INSTANCE = Mappers.getMapper(GoodsUnitAssembler.class);

    /**
     * 转换
     *
     * @param goodsUnit 货物单位
     * @return {@link GoodsUnitVO }
     * @since 2023-08-11 14:54:25
     */
    GoodsUnitCO convert(GoodsUnitPO goodsUnit);
}
