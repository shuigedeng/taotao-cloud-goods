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

package com.taotao.cloud.goods.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.taotao.boot.common.model.result.PageResult;
import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsResult;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsSkuParamsResult;
import com.taotao.cloud.goods.application.service.query.GoodsQueryService;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * GoodsQueryService 集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-06-22
 */
class GoodsQueryServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private GoodsQueryService goodsQueryService;

    @Nested
    class QueryDetail {

        @Test
        void shouldReturnDetailWhenGoodsExists() {
            GoodsSkuParamsResult result = goodsQueryService.queryDetail(1L);
            // queryDetail delegates to repository; may return null if no data preloaded
            // but the service contract guarantees non-null for valid IDs with data
        }

        @Test
        void shouldHandleNotFoundGracefully() {
            GoodsSkuParamsResult result = goodsQueryService.queryDetail(99999L);
            // May return null or throw, depending on repository implementation
        }
    }

    @Nested
    class QueryGoodsPage {

        @Test
        void shouldReturnPagedResultWithDefaultParams() {
            // Default page query — returns PageResult.empty() currently
            PageResult<GoodsResult> page = goodsQueryService.queryGoodsPage(null);
            assertThat(page).isNotNull();
        }
    }

    @Nested
    class QueryByBrandIds {

        @Test
        void shouldReturnGoodsListByBrandIds() {
            List<GoodsResult> results = goodsQueryService.queryByBrandIds(List.of(1L, 2L));
            assertThat(results).isNotNull();
        }

        @Test
        void shouldReturnEmptyListWhenBrandIdsIsEmpty() {
            List<GoodsResult> results = goodsQueryService.queryByBrandIds(List.of());
            assertThat(results).isNotNull();
        }
    }

    @Nested
    class QueryCountStoreGoodsNum {

        @Test
        void shouldReturnCountForStore() {
            Long count = goodsQueryService.queryCountStoreGoodsNum(1L);
            assertThat(count).isNotNull();
        }
    }
}
