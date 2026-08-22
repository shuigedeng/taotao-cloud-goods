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

package com.taotao.cloud.goods.infrastructure.adapter.repository;

import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.application.adapter.repository.CategorySpecificationQueryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商品分类规格查询仓储实现集成测试
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/4/12
 */
@DisplayName("商品分类规格查询仓储实现集成测试")
class CategorySpecificationQueryRepositoryImplTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private CategorySpecificationQueryRepository categorySpecificationQueryRepository;

    @Test
    @DisplayName("仓储可以正常注入")
    void shouldInjectRepository() {
        assertThat(categorySpecificationQueryRepository).isNotNull();
    }

    @Test
    @DisplayName("查询分类规格列表")
    void shouldQueryCategorySpecList() {
        var result = categorySpecificationQueryRepository.queryCategorySpecList(1L);
        assertThat(result).isNotNull();
    }
}
