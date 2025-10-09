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
import com.taotao.boot.common.enums.DelFlagEnum;
import com.taotao.cloud.goods.application.assembler.CategoryAppAssembler;
import com.taotao.cloud.goods.application.dto.own.category.result.CategoryTreeResult;
import com.taotao.cloud.goods.application.service.query.CategoryQueryService;

import java.util.*;

import com.taotao.cloud.goods.domain.aggregate.CategoryAgg;
import com.taotao.cloud.goods.domain.repository.CategoryDomainRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import static com.taotao.boot.common.enums.CachePrefixEnum.CATEGORY;
import static com.taotao.boot.common.enums.CachePrefixEnum.CATEGORY_ARRAY;

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
	private final CategoryDomainRepository categoryDomainRepository;
	private final CategoryAppAssembler categoryAssembler;

    @Override
    public List<Map<String, Object>> listMapsByIdsOrderByLevel(List<String> ids, String columns) {
        return List.of();
    }

    @Override
    public List<CategoryTreeResult> categoryTree() {
		// 获取缓存数据
		List<CategoryTreeResult> categoryTreeResults = redisRepository.lGet(
			CATEGORY.getPrefix(), 0L, redisRepository.lGetListSize(CATEGORY.getPrefix()));
		if (categoryTreeResults != null) {
			return categoryTreeResults;
		}

		List<CategoryAgg> categoryAggs = categoryDomainRepository.findCategory(DelFlagEnum.NORMAL);

		// 构造分类树
		categoryTreeResults = new ArrayList<>();
		for (CategoryAgg category : categoryAggs) {
			if (Long.valueOf(0).equals(category.parentCategoryId())) {
				CategoryTreeResult categoryTreeResult = categoryAssembler.toResult(category);
				categoryTreeResult.setParentTitle(category.categoryName());
				categoryTreeResult.setChildren(findChildren(categoryAggs, categoryTreeResult));
				categoryTreeResults.add(categoryTreeResult);
			}
		}

		categoryTreeResults.sort(Comparator.comparing(CategoryTreeResult::getSortOrder));

		if (!categoryTreeResults.isEmpty()) {
			redisRepository.lSet(CATEGORY.getPrefix(), categoryTreeResults);
			redisRepository.lSet(CATEGORY_ARRAY.getPrefix(), categoryAggs);
		}

		return categoryTreeResults;

	}

	/**
	 * 递归树形VO
	 *
	 * @param categories     分类列表
	 * @param categoryTreeVO 分类VO
	 * @return 分类VO列表
	 */
	private List<CategoryTreeResult> findChildren(List<CategoryAgg> categoryAggs, CategoryTreeResult categoryTreeResult) {
		List<CategoryTreeResult> children = new ArrayList<>();
		categoryAggs.forEach(item -> {
			if (item.getParentCategoryId().getId().equals(categoryTreeResult.getId())) {
				CategoryTreeResult temp = categoryAssembler.toResult(item);
				temp.setParentTitle(item.categoryName());
				temp.setChildren(findChildren(categoryAggs, temp));
				children.add(temp);
			}
		});

		return children;
	}

    @Override
    public List<CategoryTreeResult> listAllChildren(Long parentId) {
		if (Long.valueOf(0).equals(parentId)) {
			return categoryTree();
		}

		// 循环代码，找到对象，把他的子分类返回
		List<CategoryTreeResult> categoryTreeResults = categoryTree();
		for (CategoryTreeResult categoryTreeResult : categoryTreeResults) {
			if (categoryTreeResult.getId().equals(parentId)) {
				return categoryTreeResult.getChildren();
			} else {
				return getChildren(parentId, categoryTreeResult.getChildren());
			}
		}
		return new ArrayList<>();
    }

	/**
	 * 递归自身，找到id等于parentId的对象，获取他的children 返回
	 *
	 * @param parentId           父ID
	 * @param categoryTreeVOList 分类VO
	 * @return 子分类列表VO
	 */
	private List<CategoryTreeResult> getChildren(Long parentId, List<CategoryTreeResult> categoryTreeResults) {
		for (CategoryTreeResult categoryTreeResult : categoryTreeResults) {
			if (categoryTreeResult.getId().equals(parentId)) {
				return categoryTreeResult.getChildren();
			}

			if (categoryTreeResult.getChildren() != null && !categoryTreeResult.getChildren().isEmpty()) {
				return getChildren(parentId, categoryTreeResults);
			}
		}
		return categoryTreeResults;
	}

    @Override
    public List<CategoryTreeResult> listAllChildren() {
		// 获取全部分类
		List<CategoryAgg> categoryAggs = categoryDomainRepository.findCategory(DelFlagEnum.NORMAL);

		// 构造分类树
		List<CategoryTreeResult> categoryTreeResults = new ArrayList<>();
		for (CategoryAgg categoryAgg : categoryAggs) {
			if (Long.valueOf(0).equals(categoryAgg.parentCategoryId())) {
				CategoryTreeResult categoryTreeResult = new CategoryTreeResult();
				categoryTreeResult.setChildren(findChildren(categoryAggs, categoryTreeResult));
				categoryTreeResults.add(categoryTreeResult);
			}
		}

		categoryTreeResults.sort(Comparator.comparing(CategoryTreeResult::getSortOrder));
		return categoryTreeResults;
    }

    @Override
    public List<String> getCategoryNameByIds(List<Long> ids) {
		List<String> categoryName = new ArrayList<>();
		List<CategoryAgg> categoryAggs = (List<CategoryAgg>) redisRepository.get(CATEGORY_ARRAY.getPrefix());
		// 如果缓存中为空，则重新获取缓存
		if (categoryAggs == null) {
			categoryTree();
			categoryAggs = (List<CategoryAgg>) redisRepository.get(CATEGORY_ARRAY.getPrefix());
		}

		// 还为空的话，直接返回
		if (categoryAggs == null) {
			return new ArrayList<>();
		}

		// 循环顶级分类
		for (CategoryAgg category : categoryAggs) {
			// 循环查询的id匹配
			for (Long id : ids) {
				if (category.getId().getId().equals(id)) {
					// 写入商品分类
					categoryName.add(category.categoryName());
				}
			}
		}

		return categoryName;
    }

    //	@Override
    //	public List<CategoryPO> findByAllBySortOrder(CategorySearchQry category) {
    //		//return categorySearchQryExe.findByAllBySortOrder(categoryPO);
    //		return null;
    //	}

    @Override
    public List<CategoryTreeResult> getStoreCategory(String[] categories) {
        List<String> arr = Arrays.asList(categories.clone());
        return categoryTree().stream().filter(item -> arr.contains(item.getId())).toList();
    }

    //	@Override
    //	public List<CategoryPO> firstCategory() {
    //		return categorySearchQryExe.firstCategory();
    //	}
}
