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

import com.taotao.boot.common.model.ddd.types.MarkerAssembler;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * 参数应用层装配器
 * <p>
 * 负责将参数领域对象转换为应用层DTO，供接口层返回
 * </p>
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:58:27
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ParametersAppAssembler extends MarkerAssembler {

    /** 实例 */
    ParametersAppAssembler INSTANCE = Mappers.getMapper(ParametersAppAssembler.class);

}
