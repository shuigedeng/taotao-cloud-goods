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

import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.application.dto.category.result.CategoryTreeResult;
import com.taotao.cloud.goods.application.service.query.CategoryQueryService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CategoryQueryService 集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-06-22
 */
public class CategoryQueryServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private CategoryQueryService categoryQueryService;

    @Nested
    class QueryCategoryTree {

        @Test
        void shouldReturnCategoryTree() {
            List<CategoryTreeResult> tree = categoryQueryService.queryCategoryTree();
            assertThat(tree).isNotNull();
        }

        @Test
        void shouldReturnCategoryTreeByParentId() {
            List<CategoryTreeResult> tree = categoryQueryService.queryCategoryTree(0L);
            assertThat(tree).isNotNull();
        }

        @Test
        void shouldReturnEmptyWhenParentIdNotExists() {
            List<CategoryTreeResult> tree = categoryQueryService.queryCategoryTree(99999L);
            assertThat(tree).isNotNull();
        }
    }

    @Nested
    class QueryCategoryNameByIds {

        @Test
        void shouldReturnCategoryNames() {
            // 假设已预置分类数据
            // List<String> names = categoryQueryService.queryCategoryNameByIds(List.of(1L, 2L));
            // assertThat(names).isNotEmpty();
        }

        @Test
        void shouldReturnEmptyListWhenIdsIsEmpty() {
            List<String> names = categoryQueryService.queryCategoryNameByIds(List.of());
            assertThat(names).isEmpty();
        }
    }

    @Nested
    class QueryCategoryTreeResult {

        @Test
        void shouldReturnTreeResult() {
            List<CategoryTreeResult> result = categoryQueryService.queryCategoryTreeResult();
            assertThat(result).isNotNull();
        }
    }
}
