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

package com.taotao.cloud.goods.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taotao.boot.webagg.service.impl.BaseSuperServiceImpl;
import com.taotao.cloud.goods.application.assembler.CategoryBrandAssembler;
import com.taotao.cloud.goods.application.dto.category.clientobject.CategoryBrandCO;
import com.taotao.cloud.goods.application.service.CategoryBrandQueryService;
import com.taotao.cloud.goods.infrastructure.dataobject.CategoryBrandDO;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.CategoryBrandMapper;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.CategoryBrandPO;
import com.taotao.cloud.goods.infrastructure.persistent.repository.cls.CategoryBrandRepository;
import com.taotao.cloud.goods.infrastructure.persistent.repository.inf.ICategoryBrandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 规格项业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:01:32
 */
@Service
public class CategoryBrandQueryServiceImpl extends BaseSuperServiceImpl<
	CategoryBrandPO,
	Long,
	CategoryBrandMapper,
	CategoryBrandRepository,
	ICategoryBrandRepository> implements CategoryBrandQueryService {

	@Override
	public List<CategoryBrandCO> getCategoryBrandList(Long categoryId) {
		List<CategoryBrandDO> categoryBrandList = im().getCategoryBrandList(categoryId);
		return CategoryBrandAssembler.INSTANCE.convert(categoryBrandList);
	}

	public List<CategoryBrandPO> getCategoryBrandListByBrandId(List<Long> brandId) {
		LambdaQueryWrapper<CategoryBrandPO> wrapper = new LambdaQueryWrapper<>();
		wrapper.in(CategoryBrandPO::getBrandId, brandId);
		return list(wrapper);
	}

}
