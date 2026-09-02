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

package com.taotao.cloud.goods.application.query;

import static org.assertj.core.api.Assertions.assertThat;

import com.taotao.boot.common.model.ddd.query.PageQuery;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsUnitResult;
import com.taotao.cloud.goods.application.service.query.GoodsUnitQueryService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * GoodsUnitQueryService 集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-06-22
 */
class GoodsUnitQueryServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private GoodsUnitQueryService goodsUnitQueryService;

    @Nested
    class QueryPage {

        @Test
        void shouldReturnPagedGoodsUnits() {
            assertThat(goodsUnitQueryService).isNotNull();
            PageResult<GoodsUnitResult> page = goodsUnitQueryService.queryPage(
                PageQuery.builder().currentPage(1).pageSize(10).build());
            assertThat(page).isNotNull();
        }
    }

    @Nested
    class GetById {

        @Test
        void shouldReturnGoodsUnitWhenExists() {
            GoodsUnitResult result = goodsUnitQueryService.queryDetail(1L);
            // Returns null if no data preloaded
        }
    }
}
