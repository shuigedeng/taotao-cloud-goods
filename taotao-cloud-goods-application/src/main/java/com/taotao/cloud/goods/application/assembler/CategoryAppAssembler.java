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
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.goods.application.dto.category.result.CategoryTreeResult;
import com.taotao.cloud.goods.domain.entity.Category;
import com.taotao.cloud.goods.domain.valobj.GoodsName;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * 商品分类应用层装配器
 * <p>
 * 负责将商品分类领域对象转换为应用层DTO，供接口层返回
 * </p>
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:58:27
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryAppAssembler extends MarkerAssembler {

    /** 实例 */
    CategoryAppAssembler INSTANCE = Mappers.getMapper(CategoryAppAssembler.class);


	CategoryTreeResult toResult( Category categoryAgg);

    /**
     * map 方法
     *
     * @param value 值
     * @return 无返回值
     * @since 2022.03
     */
    default Long map(BizId value) {
        return value != null ? value.id() : null;
    }

    /**
     * map 方法
     *
     * @param value 值
     * @return 无返回值
     * @since 2022.03
     */
    default String map(GoodsName value) {
        return value != null ? value.value() : null;
    }
}
