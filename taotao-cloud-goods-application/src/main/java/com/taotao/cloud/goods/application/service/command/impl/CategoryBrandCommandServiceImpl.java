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

import com.taotao.cloud.goods.application.service.command.CategoryBrandCommandService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 规格项业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:01:32
 */
@Service
public class CategoryBrandCommandServiceImpl implements CategoryBrandCommandService {
    @Override
    public boolean deleteByCategoryId(Long categoryId) {
        return false;
    }

    @Override
    public boolean saveCategoryBrandList(Long categoryId, List<Long> brandIds) {
        return false;
    }

    //	@Override
    //	public boolean deleteByCategoryId(Long categoryId) {
    //		LambdaQueryWrapper<CategoryBrandPO> wrapper = new LambdaQueryWrapper<>();
    //		wrapper.in(CategoryBrandPO::categoryId, categoryId);
    //		return im().delete(wrapper) > 0;
    //	}
    //
    //
    //	@Override
    //	@Transactional(rollbackFor = Exception.class)
    //	public boolean saveCategoryBrandList(Long categoryId, List<Long> brandIds) {
    //		// 删除分类品牌绑定信息
    //		deleteByCategoryId(categoryId);
    //
    //		// 绑定品牌信息
    //		if (!brandIds.isEmpty()) {
    //			List<CategoryBrandPO> categoryBrandPos = new ArrayList<>();
    //			for (Long brandId : brandIds) {
    //				categoryBrandPos.add(new CategoryBrandPO(categoryId, brandId));
    //			}
    //			this.saveBatch(categoryBrandPos);
    //		}
    //
    //		return true;
    //	}
}
