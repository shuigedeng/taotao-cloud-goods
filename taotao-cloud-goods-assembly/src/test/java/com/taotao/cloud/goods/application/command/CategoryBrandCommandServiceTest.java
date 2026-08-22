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

package com.taotao.cloud.goods.application.command;

import static org.assertj.core.api.Assertions.assertThat;

import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.application.dto.category.command.CategoryIdBrandCommand;
import com.taotao.cloud.goods.application.dto.category.command.CategoryIdBrandCommandBuilder;
import com.taotao.cloud.goods.application.dto.category.command.CategoryIdCommand;
import com.taotao.cloud.goods.application.dto.category.command.CategoryIdCommandBuilder;
import com.taotao.cloud.goods.application.service.command.CategoryBrandCommandService;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CategoryBrandCommandService 集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-08-22
 */
class CategoryBrandCommandServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private CategoryBrandCommandService categoryBrandCommandService;

    @Nested
    class DeleteByCategoryId {

        @Test
        void shouldReturnFalseForNonexistentCategory() {
            boolean result = categoryBrandCommandService.deleteByCategoryId(
                CategoryIdCommandBuilder.builder().categoryId(99999L).build());
            assertThat(result).isFalse();
        }

        @Test
        void shouldReturnFalseForNullCategoryId() {
            boolean result = categoryBrandCommandService.deleteByCategoryId(
                CategoryIdCommandBuilder.builder().build());
            assertThat(result).isFalse();
        }
    }

    @Nested
    class SaveCategoryBrandList {

        @Test
        void shouldReturnFalseForNonexistentCategory() {
            boolean result = categoryBrandCommandService.saveCategoryBrandList(
                CategoryIdBrandCommandBuilder.builder()
                    .categoryId(99999L)
                    .brandIds(List.of(1L, 2L))
                    .build());
            assertThat(result).isFalse();
        }

        @Test
        void shouldReturnFalseForEmptyBrandList() {
            boolean result = categoryBrandCommandService.saveCategoryBrandList(
                CategoryIdBrandCommandBuilder.builder()
                    .categoryId(1L)
                    .brandIds(List.of())
                    .build());
            assertThat(result).isFalse();
        }
    }
}
