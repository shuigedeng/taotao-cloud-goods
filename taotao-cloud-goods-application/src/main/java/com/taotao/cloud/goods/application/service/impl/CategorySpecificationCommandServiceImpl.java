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
import com.taotao.cloud.goods.application.service.CategorySpecificationCommandService;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.ICategorySpecificationMapper;
import com.taotao.cloud.goods.infrastructure.persistent.po.CategorySpecificationPO;
import com.taotao.cloud.goods.infrastructure.persistent.po.SpecificationPO;
import com.taotao.cloud.goods.infrastructure.persistent.repository.cls.CategorySpecificationRepository;
import com.taotao.cloud.goods.infrastructure.persistent.repository.inf.ICategorySpecificationRepository;
import com.taotao.boot.web.base.service.impl.BaseSuperServiceImpl;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 商品分类规格业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:15
 */
@Service
@AllArgsConstructor
public class CategorySpecificationCommandServiceImpl
	extends BaseSuperServiceImpl<
	CategorySpecificationPO,
	Long,
	ICategorySpecificationMapper,
	CategorySpecificationRepository,
	ICategorySpecificationRepository>
	implements CategorySpecificationCommandService {

	@Override
	public List<SpecificationPO> getCategorySpecList(Long categoryId) {
		return im().getCategorySpecList(categoryId);
	}

	@Override
	public boolean deleteByCategoryId(Long categoryId) {
		return im().delete(new LambdaQueryWrapper<CategorySpecificationPO>()
			.eq(CategorySpecificationPO::getCategoryId, categoryId)) > 0;
	}
}