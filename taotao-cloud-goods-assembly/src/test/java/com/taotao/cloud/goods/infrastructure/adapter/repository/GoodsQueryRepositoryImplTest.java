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
import com.taotao.cloud.goods.application.adapter.repository.GoodsQueryRepository;
import com.taotao.cloud.goods.application.dto.goods.query.GoodsPageQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商品查询仓储实现集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@DisplayName("商品查询仓储实现集成测试")
class GoodsQueryRepositoryImplTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private GoodsQueryRepository goodsQueryRepository;

    @Test
    @DisplayName("仓储可以正常注入")
    void shouldInjectRepository() {
        assertThat(goodsQueryRepository).isNotNull();
    }

    @Nested
    @DisplayName("分页查询商品")
    class QueryGoodsPage {

//        @Test
//        @DisplayName("正常分页查询商品")
//        void shouldQueryGoodsPage() {
//            GoodsPageQuery query = new GoodsPageQuery(null, null, null, null, null,
//                    com.taotao.boot.common.model.result.PageableUtil.of(1, 10));
//            var result = goodsQueryRepository.queryGoodsPage(query);
//            assertThat(result).isNotNull();
//        }
    }

    @Nested
    @DisplayName("多表分页查询商品")
    class QueryMutilTalbePage {

//        @Test
//        @DisplayName("正常多表分页查询")
//        void shouldQueryMutilTalbePage() {
//            GoodsPageQuery query = new GoodsPageQuery(null, null, null, null, null,
//                    null);
//            var result = goodsQueryRepository.queryMutilTalbePage(query);
//            assertThat(result).isNotNull();
//        }
    }

    @Nested
    @DisplayName("根据品牌ID查询商品")
    class QueryByBrandIds {

        @Test
        @DisplayName("正常根据品牌ID查询")
        void shouldQueryByBrandIds() {
            var result = goodsQueryRepository.queryByBrandIds(List.of(1L));
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("根据ID查询商品")
    class QueryById {

        @Test
        @DisplayName("正常根据ID查询")
        void shouldQueryById() {
            var result = goodsQueryRepository.queryById(1L);
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("统计店铺商品数量")
    class QueryCountStoreGoodsNum {

        @Test
        @DisplayName("正常统计店铺商品数量")
        void shouldQueryCountStoreGoodsNum() {
            Long result = goodsQueryRepository.queryCountStoreGoodsNum(1L);
            assertThat(result).isNotNull();
        }
    }
}
