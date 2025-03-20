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
 * 查看部门执行器.
 */
@Component
@RequiredArgsConstructor
public class CategorySearchQryExe extends Executor {

    //	private final RedisRepository redisRepository;
    //	private final CategoryMapper categoryMapper;
    //
    //	public List<CategoryPO> listByIdsOrderByLevel(List<Long> ids) {
    //		LambdaQueryWrapper<CategoryPO> wrapper = new LambdaQueryWrapper<>();
    //		wrapper.in(CategoryPO::getId, ids);
    //		wrapper.orderByAsc(CategoryPO::level);
    //		return categoryMapper.selectList(wrapper);
    //	}
    //
    //	public List<CategoryPO> firstCategory() {
    //		QueryWrapper<CategoryPO> queryWrapper = Wrappers.query();
    //		queryWrapper.eq("level", 0);
    //		return categoryMapper.selectList(queryWrapper);
    //	}
    //
    //	public List<String> getCategoryNameByIds(List<Long> ids) {
    //		List<String> categoryName = new ArrayList<>();
    //		List<CategoryPO> categoryPoList = (List<CategoryPO>) redisRepository.get(
    //			CATEGORY_ARRAY.getPrefix());
    //		// 如果缓存中为空，则重新获取缓存
    //		if (categoryPoList == null) {
    //			//categoryTree();
    //			categoryPoList = (List<CategoryPO>) redisRepository.get(CATEGORY_ARRAY.getPrefix());
    //		}
    //
    //		// 还为空的话，直接返回
    //		if (categoryPoList == null) {
    //			return new ArrayList<>();
    //		}
    //
    //		// 循环顶级分类
    //		for (CategoryPO categoryPo : categoryPoList) {
    //			// 循环查询的id匹配
    //			for (Long id : ids) {
    //				if (categoryPo.getId().equals(id)) {
    //					// 写入商品分类
    //					categoryName.add(categoryPo.name());
    //				}
    //			}
    //		}
    //		return categoryName;
    //	}
    //
    //	//public List<CategoryPO> findByAllBySortOrder(CategoryPO categoryPo) {
    //	//	QueryWrapper<CategoryPO> queryWrapper = new QueryWrapper<>();
    //	//	queryWrapper
    //	//		.eq(categoryPo.getLevel() != null, "level", categoryPo.getLevel())
    //	//		.eq(CharSequenceUtil.isNotBlank(categoryPo.getName()), "name", categoryPo.getName())
    //	//		.eq(categoryPo.getParentId() != null, "parent_id", categoryPo.getParentId())
    //	//		.ne(categoryPo.getId() != null, "id", categoryPo.getId())
    //	//		.eq(DELETE_FLAG_COLUMN, false)
    //	//		.orderByAsc("sort_order");
    //	//	return this.categoryMapper.selectList(queryWrapper);
    //	//}
}
