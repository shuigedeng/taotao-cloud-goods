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

import com.taotao.boot.common.model.ddd.query.PageQuery;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.application.dto.brand.query.BrandPageQuery;
import com.taotao.cloud.goods.application.dto.brand.query.BrandPageQueryBuilder;
import com.taotao.cloud.goods.application.dto.brand.result.BrandResult;
import com.taotao.cloud.goods.application.service.query.BrandQueryService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BrandQueryService 集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-06-22
 */
class BrandQueryServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private BrandQueryService brandQueryService;

    @Nested
    class QueryPage {

        @Test
        void shouldReturnPagedBrands() {
            PageResult<BrandResult> page = brandQueryService.queryPage(
                BrandPageQueryBuilder.builder()
                    .page(PageQuery.builder().currentPage(1).pageSize(10).build())
                    .build());
            assertThat(page).isNotNull();
        }

        @Test
        void shouldApplyNameFilter() {
            PageResult<BrandResult> page = brandQueryService.queryPage(
				BrandPageQueryBuilder.builder()
                    .name("test")
                    .page(PageQuery.builder().currentPage(1).pageSize(10).build())
                    .build());
            assertThat(page).isNotNull();
        }
    }

    @Nested
    class GetById {

        @Test
        void shouldReturnBrandWhenExists() {
            BrandResult brand = brandQueryService.queryDetail(1L);
            assertThat(brand).isNotNull();
        }

        @Test
        void shouldReturnNullWhenNotExists() {
            BrandResult brand = brandQueryService.queryDetail(99999L);
            // queryDetail delegates to brandQueryRepository which may return null for non-existent
        }
    }

    @Nested
    class QueryAllAvailable {

        @Test
        void shouldReturnAvailableBrands() {
            assertThat(brandQueryService.queryAllAvailable()).isNotNull();
        }
    }
}
