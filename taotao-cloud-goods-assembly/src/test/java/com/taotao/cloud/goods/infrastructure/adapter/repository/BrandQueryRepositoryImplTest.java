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
import com.taotao.cloud.goods.application.adapter.repository.BrandQueryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

/**
 * 品牌查询仓储实现集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@DisplayName("品牌查询仓储实现集成测试")
class BrandQueryRepositoryImplTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private BrandQueryRepository brandQueryRepository;

    @Test
    @DisplayName("仓储可以正常注入")
    void shouldInjectRepository() {
        assertThat(brandQueryRepository).isNotNull();
    }

    @Test
    @DisplayName("根据ID查询品牌")
    void shouldQueryById() {
        var result = brandQueryRepository.queryById(1L);
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("查询所有可用品牌")
    void shouldQueryAllAvailable() {
        var result = brandQueryRepository.queryAllAvailable();
        assertThat(result).isNotNull();
    }
}
