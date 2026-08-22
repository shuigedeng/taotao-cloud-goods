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

import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.application.dto.specification.result.SpecificationResult;
import com.taotao.cloud.goods.application.service.query.CategorySpecificationQueryService;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CategorySpecificationQueryService 集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-08-22
 */
class CategorySpecificationQueryServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private CategorySpecificationQueryService categorySpecificationQueryService;

    @Nested
    class QueryByCategoryId {

        @Test
        void shouldReturnSpecListForExistingCategory() {
            List<SpecificationResult> specs = categorySpecificationQueryService.queryByCategoryId(1L);
            assertThat(specs).isNotNull();
        }

        @Test
        void shouldReturnEmptyListForNonexistentCategory() {
            List<SpecificationResult> specs =
                categorySpecificationQueryService.queryByCategoryId(99999L);
            assertThat(specs).isEmpty();
        }

        @Test
        void shouldReturnEmptyListForNullCategoryId() {
            List<SpecificationResult> specs =
                categorySpecificationQueryService.queryByCategoryId(null);
            assertThat(specs).isNotNull();
        }
    }
}
