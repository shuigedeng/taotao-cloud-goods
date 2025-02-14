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

import com.taotao.boot.cache.redis.repository.RedisRepository;
import com.taotao.boot.webagg.service.impl.BaseSuperServiceImpl;
import com.taotao.cloud.goods.application.dto.category.cmmond.CategoryAddCmd;
import com.taotao.cloud.goods.application.dto.category.cmmond.CategoryUpdateCmd;
import com.taotao.cloud.goods.application.executor.category.cmmond.CategoryDelCmdExe;
import com.taotao.cloud.goods.application.executor.category.cmmond.CategorySaveCmdExe;
import com.taotao.cloud.goods.application.executor.category.cmmond.CategoryUpdateCmdExe;
import com.taotao.cloud.goods.application.executor.category.query.CategoryChildrenCmdExe;
import com.taotao.cloud.goods.application.executor.category.query.CategorySearchQryExe;
import com.taotao.cloud.goods.application.executor.category.query.CategoryTreeCmdExe;
import com.taotao.cloud.goods.application.service.BrandCommandService;
import com.taotao.cloud.goods.application.service.CategoryCommandService;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.CategoryMapper;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.CategoryPO;
import com.taotao.cloud.goods.infrastructure.persistent.repository.cls.CategoryRepository;
import com.taotao.cloud.goods.infrastructure.persistent.repository.inf.ICategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 商品分类业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:09
 */
@AllArgsConstructor
@Service
@CacheConfig(cacheNames = "{category}")
public class CategoryCommandServiceImpl
	implements CategoryCommandService {

	private final RedisRepository redisRepository;
	/**
	 * 商品品牌业务层
	 */
	//private final BrandCommandService brandService;

	private final CategoryTreeCmdExe categoryTreeCmdExe;
	private final CategoryChildrenCmdExe categoryChildrenCmdExe;
	private final CategorySearchQryExe categorySearchQryExe;
	private final CategoryUpdateCmdExe categoryUpdateCmdExe;
	private final CategorySaveCmdExe categorySaveCmdExe;
	private final CategoryDelCmdExe categoryDelCmdExe;


	@Override
	public boolean saveCategory(CategoryAddCmd category) {
		//return categorySaveCmdExe.saveCategory(categoryPO);
		return true;
	}

	@Override
	public boolean updateCategory(CategoryUpdateCmd category) {
		//return categoryUpdateCmdExe.updateCategory(categoryPO);
		return true;
	}

	@Override
	public void delete(Long id) {
		categoryDelCmdExe.delete(id);
	}

	@Override
	public void updateCategoryStatus(Long categoryId, boolean enableOperations) {
		//categoryUpdateCmdExe.updateCategoryStatus(categoryId, enableOperations);
	}
}
