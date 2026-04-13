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
import com.taotao.cloud.goods.application.dto.own.brand.command.CreateBrandCommand;
import com.taotao.cloud.goods.application.dto.own.brand.result.BrandResult;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.BrandPO;
import java.util.List;
import nl.basjes.parse.useragent.clienthints.ClientHints.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * BrandMapStruct
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:57:55
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BrandInfraAssembler extends MarkerAssembler {

    /**
     * 实例
     */
    BrandInfraAssembler INSTANCE = Mappers.getMapper(BrandInfraAssembler.class);

    /**
     * 品牌,品牌签证官
     *
     * @param brandPo 品牌
     * @return {@link BrandResult }
     * @since 2022-04-27 16:57:56
     */
    BrandResult toResult(BrandPO brandPo);

    /**
     * 品牌品牌vos
     *
     * @param brandPos 品牌
     * @return {@link List }<{@link BrandResult }>
     * @since 2022-04-27 16:57:56
     */
    List<BrandResult> toResult(List<BrandPO> brandPos);

    /**
     * 品牌dtoto品牌
     *
     * @param brandAddCommand 品牌dto
     * @return {@link Brand }
     * @since 2022-04-27 16:57:56
     */
    BrandPO toPo( CreateBrandCommand brandAddCommand);
	CreateBrandCommand toCommand(BrandPO brandPo);
}
