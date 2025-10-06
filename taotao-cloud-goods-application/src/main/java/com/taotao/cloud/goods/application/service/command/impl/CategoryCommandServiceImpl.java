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

package com.taotao.cloud.goods.application.service.command.impl;

import com.taotao.boot.cache.redis.repository.RedisRepository;
import com.taotao.cloud.goods.application.dto.category.command.CategoryAddCommand;
import com.taotao.cloud.goods.application.dto.category.command.CategoryUpdateCommand;
import com.taotao.cloud.goods.application.service.command.BrandCommandService;
import com.taotao.cloud.goods.application.service.command.CategoryCommandService;
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
public class CategoryCommandServiceImpl implements CategoryCommandService {

    private final RedisRepository redisRepository;

    @Override
    public boolean saveCategory(CategoryAddCommand category) {
        // return categorySaveCmdExe.saveCategory(categoryPO);
        return true;
    }

    @Override
    public boolean updateCategory(CategoryUpdateCommand category) {
        // return categoryUpdateCmdExe.updateCategory(categoryPO);
        return true;
    }

    @Override
    public void delete(Long id) {
		//brandCommandService.delete(id);
    }

    @Override
    public void updateCategoryStatus(Long categoryId, boolean enableOperations) {
        // categoryUpdateCmdExe.updateCategoryStatus(categoryId, enableOperations);
    }
}
