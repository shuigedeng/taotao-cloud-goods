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

package com.taotao.cloud.goods.application.service.query.impl;

import com.taotao.boot.cache.redis.repository.RedisRepository;
import com.taotao.cloud.goods.application.dto.category.clientobject.CategoryTreeCO;
import com.taotao.cloud.goods.application.executor.category.cmmond.CategoryDelCmdExe;
import com.taotao.cloud.goods.application.executor.category.cmmond.CategorySaveCmdExe;
import com.taotao.cloud.goods.application.executor.category.cmmond.CategoryUpdateCmdExe;
import com.taotao.cloud.goods.application.executor.category.query.CategoryChildrenCmdExe;
import com.taotao.cloud.goods.application.executor.category.query.CategorySearchQryExe;
import com.taotao.cloud.goods.application.executor.category.query.CategoryTreeCmdExe;
import com.taotao.cloud.goods.application.service.query.CategoryQueryService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final RedisRepository redisRepository;

    /**
     * 商品品牌业务层
     */
    // private final BrandCommandService brandService;

    private final CategoryTreeCmdExe categoryTreeCmdExe;

    private final CategoryChildrenCmdExe categoryChildrenCmdExe;
    private final CategorySearchQryExe categorySearchQryExe;
    private final CategoryUpdateCmdExe categoryUpdateCmdExe;
    private final CategorySaveCmdExe categorySaveCmdExe;
    private final CategoryDelCmdExe categoryDelCmdExe;

    //	@Override
    //	public List<CategoryPO> childrenList(Long parentId) {
    //		return categoryChildrenCmdExe.childrenList(parentId);
    //	}
    //
    //	@Override
    //	public CategoryPO getCategoryById(Long id) {
    //		return this.getById(id);
    //	}
    //
    //	@Override
    //	public List<CategoryPO> dbList(String parentId) {
    //		return List.of();
    //	}
    //
    //	@Override
    //	public CategoryPO getCategoryById(String id) {
    //		return null;
    //	}
    //
    //	@Override
    //	public List<CategoryPO> listByIdsOrderByLevel(List<Long> ids) {
    //		return categorySearchQryExe.listByIdsOrderByLevel(ids);
    //	}

    @Override
    public List<Map<String, Object>> listMapsByIdsOrderByLevel(List<String> ids, String columns) {
        return List.of();
    }

    @Override
    public List<CategoryTreeCO> categoryTree() {
        //		return categoryTreeCmdExe.categoryTree();
        return null;
    }

    @Override
    public List<CategoryTreeCO> listAllChildren(Long parentId) {
        //		return categoryChildrenCmdExe.listAllChildren(parentId);
        return null;
    }

    @Override
    public List<CategoryTreeCO> listAllChildren() {
        //		return categoryChildrenCmdExe.listAllChildren();
        return null;
    }

    @Override
    public List<String> getCategoryNameByIds(List<Long> ids) {
        //		return categorySearchQryExe.getCategoryNameByIds(ids);
        return null;
    }

    //	@Override
    //	public List<CategoryPO> findByAllBySortOrder(CategorySearchQry category) {
    //		//return categorySearchQryExe.findByAllBySortOrder(categoryPO);
    //		return null;
    //	}

    @Override
    public List<CategoryTreeCO> getStoreCategory(String[] categories) {
        List<String> arr = Arrays.asList(categories.clone());
        return categoryTree().stream().filter(item -> arr.contains(item.getId())).toList();
    }

    //	@Override
    //	public List<CategoryPO> firstCategory() {
    //		return categorySearchQryExe.firstCategory();
    //	}
}
