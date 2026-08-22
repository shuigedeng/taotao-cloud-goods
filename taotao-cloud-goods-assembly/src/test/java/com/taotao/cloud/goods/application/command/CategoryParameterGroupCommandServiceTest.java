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
import com.taotao.cloud.goods.application.dto.category.command.CreateCategoryParameterGroupCommand;
import com.taotao.cloud.goods.application.dto.category.command.CreateCategoryParameterGroupCommandBuilder;
import com.taotao.cloud.goods.application.service.command.CategoryParameterGroupCommandService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CategoryParameterGroupCommandService 集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-08-22
 */
class CategoryParameterGroupCommandServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private CategoryParameterGroupCommandService categoryParameterGroupCommandService;

    @Nested
    class UpdateCategoryGroup {

        @Test
        void shouldReturnFalseForNonexistentCategory() {
            boolean result = categoryParameterGroupCommandService.updateCategoryGroup(
                CreateCategoryParameterGroupCommandBuilder.builder().id("99999").build());
            assertThat(result).isFalse();
        }

        @Test
        void shouldReturnFalseForNullId() {
            boolean result = categoryParameterGroupCommandService.updateCategoryGroup(
                CreateCategoryParameterGroupCommandBuilder.builder().build());
            assertThat(result).isFalse();
        }
    }

    @Nested
    class DeleteByCategoryId {

        @Test
        void shouldReturnFalseForNonexistentCategory() {
            boolean result = categoryParameterGroupCommandService.deleteByCategoryId(99999L);
            assertThat(result).isFalse();
        }
    }
}
