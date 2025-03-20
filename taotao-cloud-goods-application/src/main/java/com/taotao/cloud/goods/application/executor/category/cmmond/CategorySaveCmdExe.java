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

import com.taotao.boot.cache.redis.repository.RedisRepository;
import com.taotao.boot.ddd.model.application.executor.Executor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 删除部门执行器.
 */
@Component
@RequiredArgsConstructor
public class CategorySaveCmdExe extends Executor {

    private final RedisRepository redisRepository;
    private final CategoryCacheDelCmdExe categoryCacheDelCmdExe;

    // @Transactional(rollbackFor = Exception.class)
    // public boolean saveCategory(CategoryPO categoryPo) {
    //	// 判断分类佣金是否正确
    //	if (categoryPo.getCommissionRate().compareTo(BigDecimal.ZERO) < 0) {
    //		throw new BusinessException(ResultEnum.CATEGORY_COMMISSION_RATE_ERROR);
    //	}
    //
    //	// 子分类与父分类的状态一致
    //	if (categoryPo.getParentId() != null && !Long.valueOf(0).equals(categoryPo.getParentId())) {
    //		CategoryPO parentCategoryPo = this.categoryMapper.selectById(categoryPo.getParentId());
    //		categoryPo.setDelFlag(parentCategoryPo.getDelFlag());
    //	}
    //	this.categoryMapper.insert(categoryPo);
    //	categoryCacheDelCmdExe.removeCache();
    //	return true;
    // }
}
