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

import com.taotao.cloud.goods.application.dto.category.clientobject.CategoryBrandCO;
import com.taotao.cloud.goods.application.service.query.CategoryBrandQueryService;
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
public class CategoryBrandQueryServiceImpl implements CategoryBrandQueryService {
    @Override
    public List<CategoryBrandCO> getCategoryBrandList(Long categoryId) {
        return List.of();
    }

    //	@Override
    //	public List<CategoryBrandCO> getCategoryBrandList(Long categoryId) {
    //		List<CategoryBrandDO> categoryBrandList = im().getCategoryBrandList(categoryId);
    //		return CategoryBrandAssembler.INSTANCE.convert(categoryBrandList);
    //	}
    //
    //	public List<CategoryBrandPO> getCategoryBrandListByBrandId(List<Long> brandId) {
    //		LambdaQueryWrapper<CategoryBrandPO> wrapper = new LambdaQueryWrapper<>();
    //		wrapper.in(CategoryBrandPO::brandId, brandId);
    //		return list(wrapper);
    //	}

}
