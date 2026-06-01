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
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.goods.application.dto.category.result.CategoryResult;
import com.taotao.cloud.goods.application.dto.category.result.CategoryTreeResult;
import com.taotao.cloud.goods.domain.entity.Category;
import com.taotao.cloud.goods.domain.valobj.CategoryDesc;
import com.taotao.cloud.goods.domain.valobj.CategoryName;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.CategoryPO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * ICategoryMapStruct
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:58:05
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryInfraAssembler extends BaseInfraAssembler,MarkerAssembler {

    CategoryInfraAssembler INSTANCE = Mappers.getMapper(CategoryInfraAssembler.class);

    CategoryTreeResult toResult(CategoryPO category);

    List<CategoryResult> toResult(List<CategoryPO> categoryPos);

	@Mapping(target = "id", source = "id")
	@Mapping(target = "parentCategoryId", source = "parentId")
	@Mapping(target = "categoryName", source = "name")
	@Mapping(target = "categoryDesc", source = "name")
	@Mapping(target = "createTime", source = "createTime")
	Category toEntity(CategoryPO po);

	List<Category> toEntity(List<CategoryPO> categoryPos);

}
