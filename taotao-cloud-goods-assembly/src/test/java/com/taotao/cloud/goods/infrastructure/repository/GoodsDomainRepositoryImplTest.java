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

package com.taotao.cloud.goods.infrastructure.repository;

import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.domain.repository.GoodsDomainRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商品领域仓储实现集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@DisplayName("商品领域仓储实现集成测试")
class GoodsDomainRepositoryImplTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private GoodsDomainRepository goodsDomainRepository;

    @Test
    @DisplayName("仓储可以正常注入")
    void shouldInjectRepository() {
        assertThat(goodsDomainRepository).isNotNull();
    }

    @Test
    @DisplayName("根据ID集合统计数量")
    void shouldCountByIdIn() {
        Integer result = goodsDomainRepository.countByIdIn(java.util.List.of());
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("检查分类ID是否存在")
    void shouldExistsByCategoryIdIn() {
        Boolean result = goodsDomainRepository.existsByCategoryIdIn(java.util.List.of());
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("检查商品ID和状态是否存在")
    void shouldExistsByIdInAndGoodsStatus() {
        Boolean result = goodsDomainRepository.existsByIdInAndGoodsStatus(
                java.util.List.of(), com.taotao.cloud.goods.domain.valobj.GoodsStatus.SHELVED);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("检查上架商品是否存在")
    void shouldExistsShelvedGoodsByIdIn() {
        Boolean result = goodsDomainRepository.existsShelvedGoodsByIdIn(java.util.List.of());
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("根据ID查找商品")
    void shouldFindGoodsWithNameById() {
        var result = goodsDomainRepository.findGoodsWithNameById(1L);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("检查标签ID是否存在")
    void shouldExistsByTagIds() {
        Boolean result = goodsDomainRepository.existsByTagIds(java.util.List.of());
        assertThat(result).isNull();
    }
}
