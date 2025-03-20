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

package com.taotao.cloud.goods.application.executor.category.cmmond;

import com.taotao.boot.ddd.model.application.executor.Executor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 删除部门执行器.
 */
@Component
@RequiredArgsConstructor
public class CategoryUpdateCmdExe extends Executor {

    // private final RedisRepository redisRepository;
    // private final CategoryMapper categoryMapper;
    // private final CategoryCacheDelCmdExe categoryCacheDelCmdExe;
    // private final CategoryChildrenCmdExe categoryChildrenCmdExe;
    //
    // @Transactional(rollbackFor = Exception.class)
    // public boolean updateCategoryStatus(Long categoryId, boolean enableOperations) {
    //	// 禁用子分类
    //	CategoryPO categoryPo = this.categoryMapper.selectById(categoryId);
    //	CategoryTreeCO categoryTreeCo = BeanUtils.copy(categoryPo, CategoryTreeCO.class);
    //	List<Long> ids = new ArrayList<>();
    //
    //	assert categoryTreeCo != null;
    //
    //	ids.add(categoryTreeCo.getId());
    //	categoryChildrenCmdExe.findAllChild(categoryTreeCo);
    //	categoryChildrenCmdExe.findAllChildIds(categoryTreeCo, ids);
    //	LambdaUpdateWrapper<CategoryPO> updateWrapper = new LambdaUpdateWrapper<>();
    //	updateWrapper.in(CategoryPO::getId, ids);
    //	updateWrapper.set(CategoryPO::getDelFlag, enableOperations);
    //	this.categoryMapper.update(updateWrapper);
    //
    //	categoryCacheDelCmdExe.removeCache();
    //
    //	return true;
    // }
    //
    //
    // @CacheEvict(key = "#categoryPO.id")
    // @Transactional(rollbackFor = Exception.class)
    // public boolean updateCategory(CategoryPO categoryPo) {
    //	// 判断分类佣金是否正确
    //	if (categoryPo.getCommissionRate().compareTo(BigDecimal.ZERO) < 0) {
    //		throw new BusinessException(ResultEnum.CATEGORY_COMMISSION_RATE_ERROR);
    //	}
    //
    //	// 判断父分类与子分类的状态是否一致
    //	if (categoryPo.getParentId() != null && !Long.valueOf(0).equals(categoryPo.getParentId())) {
    //		CategoryPO parentCategoryPo = this.categoryMapper.selectById(categoryPo.getParentId());
    //		if (!parentCategoryPo.getDelFlag().equals(categoryPo.getDelFlag())) {
    //			throw new BusinessException(ResultEnum.CATEGORY_DELETE_FLAG_ERROR);
    //		}
    //	}
    //
    //	UpdateWrapper<CategoryPO> updateWrapper = new UpdateWrapper<>();
    //	updateWrapper
    //		.eq("id", categoryPo.getId())
    //		.set("name", categoryPo.getName())
    //		.set("image", categoryPo.getImage())
    //		.set("sort_order", categoryPo.getSortOrder())
    //		.set(DELETE_FLAG_COLUMN, categoryPo.getDelFlag())
    //		.set("commission_rate", categoryPo.getCommissionRate());
    //	this.categoryMapper.update(categoryPo, updateWrapper);
    //	categoryCacheDelCmdExe.removeCache();
    //	return true;
    // }

}
