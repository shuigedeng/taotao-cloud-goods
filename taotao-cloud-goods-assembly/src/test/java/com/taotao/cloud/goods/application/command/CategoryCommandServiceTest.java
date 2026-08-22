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
import static org.assertj.core.api.Assertions.assertThatCode;

import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.application.dto.category.command.CreateCategoryCommand;
import com.taotao.cloud.goods.application.dto.category.command.CreateCategoryCommandBuilder;
import com.taotao.cloud.goods.application.dto.category.command.UpdateCategoryCommand;
import com.taotao.cloud.goods.application.dto.category.command.UpdateCategoryCommandBuilder;
import com.taotao.cloud.goods.application.service.command.CategoryCommandService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CategoryCommandService 集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-08-22
 */
class CategoryCommandServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private CategoryCommandService categoryCommandService;

    @Nested
    class SaveCategory {

        @Test
        void shouldReturnFalseForNonexistentParent() {
            boolean result = categoryCommandService.saveCategory(
                CreateCategoryCommandBuilder.builder().id("99999").build());
            assertThat(result).isFalse();
        }

        @Test
        void shouldReturnFalseForNullId() {
            boolean result = categoryCommandService.saveCategory(
                CreateCategoryCommandBuilder.builder().build());
            assertThat(result).isFalse();
        }
    }

    @Nested
    class UpdateCategory {

        @Test
        void shouldReturnFalseForNonexistentCategory() {
            boolean result = categoryCommandService.updateCategory(
                UpdateCategoryCommandBuilder.builder().id("99999").build());
            assertThat(result).isFalse();
        }

        @Test
        void shouldReturnFalseForNullId() {
            boolean result = categoryCommandService.updateCategory(
                UpdateCategoryCommandBuilder.builder().build());
            assertThat(result).isFalse();
        }
    }

    @Nested
    class Delete {

        @Test
        void shouldExecuteWithoutError() {
            assertThatCode(() -> categoryCommandService.delete(99999L))
                .doesNotThrowAnyException();
        }
    }

    @Nested
    class UpdateCategoryStatus {

        @Test
        void shouldExecuteWithoutErrorWhenEnable() {
            assertThatCode(() -> categoryCommandService.updateCategoryStatus(1L, true))
                .doesNotThrowAnyException();
        }

        @Test
        void shouldExecuteWithoutErrorWhenDisable() {
            assertThatCode(() -> categoryCommandService.updateCategoryStatus(1L, false))
                .doesNotThrowAnyException();
        }
    }
}
