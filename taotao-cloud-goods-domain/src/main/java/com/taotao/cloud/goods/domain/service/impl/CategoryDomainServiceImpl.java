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

package com.taotao.cloud.goods.domain.service.impl;

import com.taotao.cloud.goods.domain.aggregate.CategoryAgg;
import com.taotao.cloud.goods.domain.repository.CategoryDomainRepository;
import com.taotao.cloud.goods.domain.service.CategoryDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryDomainServiceImpl implements CategoryDomainService {

    private CategoryDomainRepository categoryDomainRepository;

    @Override
    public void create(CategoryAgg dept) {}

    @Override
    public void modify(CategoryAgg dept) {}

    @Override
    public void remove(Long[] ids) {}
}
