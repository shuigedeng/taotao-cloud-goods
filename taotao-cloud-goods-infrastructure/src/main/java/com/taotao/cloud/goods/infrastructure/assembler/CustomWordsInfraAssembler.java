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

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ICustomWordsMapStruct
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:58:09
 */
@Mapper
public interface CustomWordsInfraAssembler {

    /** 实例 */
    CustomWordsInfraAssembler INSTANCE = Mappers.getMapper(CustomWordsInfraAssembler.class);

    /**
     * 自定义单词voto自定义单词
     *
     * @param customWordsVO 自定义单词签证官
     * @return {@link CustomWords }
     * @since 2022-04-27 16:58:09
     */
    // CustomWords convert(CustomWordsVO customWordsVO);
}
