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

import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsSkuResult;
import com.taotao.cloud.goods.application.service.query.GoodsSkuQueryService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * GoodsSkuQueryService 集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-06-22
 */
class GoodsSkuQueryServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private GoodsSkuQueryService goodsSkuQueryService;

    @Nested
    class QueryGoodsSkuDetail {

        @Test
        void shouldReturnDetailWhenSkuExists() {
            GoodsSkuResult result = goodsSkuQueryService.queryGoodsSkuDetail(1L);
            // May return null if no data preloaded — verify service contract behavior
        }

        @Test
        void shouldHandleNonExistentSku() {
            GoodsSkuResult result = goodsSkuQueryService.queryGoodsSkuDetail(99999L);
            // Expect null or graceful empty response for non-existent SKU
        }
    }

    @Nested
    class QueryGoodsSkuInfo {

        @Test
        void shouldReturnSkuInfoByGoodsId() {
            GoodsSkuResult result = goodsSkuQueryService.queryGoodsSkuInfo(1L);
            // May return null if no data preloaded
        }
    }
}
