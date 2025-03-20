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

package com.taotao.cloud.goods.application.executor.category.query;

import com.taotao.boot.ddd.model.application.executor.Executor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 删除部门执行器.
 */
@Component
@RequiredArgsConstructor
public class CategoryTreeCmdExe extends Executor {

    //	private final RedisRepository redisRepository;
    //	private final CategoryMapper categoryMapper;
    //
    //	public List<CategoryTreeCO> categoryTree() {
    //		// 获取缓存数据
    //		List<CategoryTreeCO> categoryTreeCoList = redisRepository.lGet(
    //			CATEGORY.getPrefix(), 0L, redisRepository.lGetListSize(CATEGORY.getPrefix()));
    //		if (categoryTreeCoList != null) {
    //			return categoryTreeCoList;
    //		}
    //
    //		// 获取全部分类
    //		LambdaQueryWrapper<CategoryPO> queryWrapper = new LambdaQueryWrapper<>();
    //		queryWrapper.eq(CategoryPO::getDelFlag, false);
    //		List<CategoryPO> list = categoryMapper.selectList(queryWrapper);
    //
    //		// 构造分类树
    //		categoryTreeCoList = new ArrayList<>();
    //		for (CategoryPO categoryPo : list) {
    //			if (Long.valueOf(0).equals(categoryPo.parentId())) {
    //				//CategoryTreeCO categoryTreeCo = CategoryConvert.INSTANCE.convert(categoryPo);
    //				//categoryTreeCo.setParentTitle(categoryPo.getName());
    //				//categoryTreeCo.setChildren(findChildren(list, categoryTreeCo));
    //				//categoryTreeCoList.add(categoryTreeCo);
    //			}
    //		}
    //
    //		categoryTreeCoList.sort(Comparator.comparing(CategoryTreeCO::getSortOrder));
    //
    //		if (!categoryTreeCoList.isEmpty()) {
    //			redisRepository.lSet(CATEGORY.getPrefix(), categoryTreeCoList);
    //			redisRepository.lSet(CATEGORY_ARRAY.getPrefix(), list);
    //		}
    //		return categoryTreeCoList;
    //	}
    //
    //	/**
    //	 * 递归树形CO
    //	 *
    //	 * @param categories     分类列表
    //	 * @param categoryTreeCo 分类CO
    //	 * @return 分类CO列表
    //	 */
    //	public List<CategoryTreeCO> findChildren(List<CategoryPO> categories,
    //											 CategoryTreeCO categoryTreeCo) {
    //		List<CategoryTreeCO> children = new ArrayList<>();
    //		categories.forEach(item -> {
    //			if (item.parentId().equals(categoryTreeCo.getId())) {
    //				//CategoryTreeCO temp = CategoryConvert.INSTANCE.convert(item);
    //				//temp.setParentTitle(item.getName());
    //				//temp.setChildren(findChildren(categories, temp));
    //				//children.add(temp);
    //			}
    //		});
    //
    //		return children;
    //	}
}
