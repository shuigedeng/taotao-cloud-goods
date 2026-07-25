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
import com.taotao.cloud.goods.application.dto.brand.command.CreateBrandCommand;
import com.taotao.cloud.goods.application.dto.brand.result.BrandResult;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.BrandPO;
import java.util.List;
import nl.basjes.parse.useragent.clienthints.ClientHints.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * 品牌基础设施装配器
 * <p>
 * 负责品牌 PO 与应用层对象之间的转换
 * </p>
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:57:55
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BrandInfraAssembler extends MarkerAssembler {

    BrandInfraAssembler INSTANCE = Mappers.getMapper(BrandInfraAssembler.class);

    BrandResult toResult(BrandPO brandPo);

    List<BrandResult> toResult(List<BrandPO> brandPos);

    BrandPO toPo( CreateBrandCommand createBrandCommand);

	CreateBrandCommand toCommand(BrandPO brandPo);
}
