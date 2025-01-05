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

package com.taotao.cloud.goods.application.service;

import com.taotao.boot.webagg.service.BaseSuperService;
import com.taotao.cloud.goods.application.dto.category.cmmond.CategoryAddCmd;
import com.taotao.cloud.goods.application.dto.category.cmmond.CategoryUpdateCmd;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.CategoryPO;

/**
 * 商品分类业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:59:28
 */
public interface CategoryCommandService extends BaseSuperService<CategoryPO, Long> {

	/**
	 * 添加商品分类
	 *
	 * @param category 商品分类信息
	 * @return 添加结果
	 */
	boolean saveCategory(CategoryAddCmd category);

	/**
	 * 修改商品分类
	 *
	 * @param category 商品分类信息
	 * @return 修改结果
	 */
	boolean updateCategory(CategoryUpdateCmd category);

	/**
	 * 批量删除分类
	 *
	 * @param id 分类ID
	 */
	void delete(Long id);

	/**
	 * 分类状态的更改
	 *
	 * @param categoryId       商品分类ID
	 * @param enableOperations 是否可用
	 */
	void updateCategoryStatus(Long categoryId, boolean enableOperations);

}
