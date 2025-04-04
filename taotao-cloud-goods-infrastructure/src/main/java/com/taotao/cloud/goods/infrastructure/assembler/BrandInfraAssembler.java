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

import com.taotao.cloud.goods.application.dto.brand.clientobject.BrandCO;
import com.taotao.cloud.goods.application.dto.brand.cmmond.BrandAddCmd;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.BrandPO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * BrandMapStruct
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:57:55
 */
@Mapper
public interface BrandInfraAssembler {

    /**
     * 实例
     */
    BrandInfraAssembler INSTANCE = Mappers.getMapper(BrandInfraAssembler.class);

    /**
     * 品牌,品牌签证官
     *
     * @param brand 品牌
     * @return {@link BrandVO }
     * @since 2022-04-27 16:57:56
     */
    BrandCO convert(BrandPO brand);

    /**
     * 品牌品牌vos
     *
     * @param brands 品牌
     * @return {@link List }<{@link BrandVO }>
     * @since 2022-04-27 16:57:56
     */
    List<BrandCO> convert(List<BrandPO> brands);

    /**
     * 品牌dtoto品牌
     *
     * @param brandDTO 品牌dto
     * @return {@link Brand }
     * @since 2022-04-27 16:57:56
     */
    BrandPO convert(BrandAddCmd brandDTO);
}
