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

import com.taotao.cloud.goods.application.dto.specification.clientobject.SpecificationCO;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.SpecificationPO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ISpecificationMapStruct
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:58:30
 */
@Mapper
public interface SpecificationInfraAssembler {

    /** 实例 */
    SpecificationInfraAssembler INSTANCE = Mappers.getMapper(SpecificationInfraAssembler.class);

    /**
     * 规范来规范vos
     *
     * @param specifications 规范
     * @return {@link List }<{@link SpecificationVO }>
     * @since 2022-04-27 16:58:30
     */
    List<SpecificationCO> convert(List<SpecificationPO> specifications);

    SpecificationCO convert(SpecificationPO specification);

    /**
     * 规范dtoto规范
     *
     * @param specificationDTO 规范dto
     * @return {@link Specification }
     * @since 2022-04-27 16:58:30
     */
    // SpecificationPO convert(SpecificationDTO specificationDTO);
}
