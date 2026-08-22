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
import com.taotao.cloud.goods.application.dto.goods.command.CreateCustomWordsCommand;
import com.taotao.cloud.goods.application.dto.goods.command.CreateCustomWordsCommandBuilder;
import com.taotao.cloud.goods.application.dto.goods.command.UpdateCustomWordsCommand;
import com.taotao.cloud.goods.application.dto.goods.command.UpdateCustomWordsCommandBuilder;
import com.taotao.cloud.goods.application.service.command.CustomWordsCommandService;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CustomWordsCommandService 集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-08-22
 */
class CustomWordsCommandServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private CustomWordsCommandService customWordsCommandService;

    @Nested
    class Deploy {

        @Test
        void shouldReturnDeployContent() {
            String content = customWordsCommandService.deploy();

            assertThat(content).isNotNull();
        }
    }

    @Nested
    class AddCustomWords {

        @Test
        void shouldReturnResultWhenAddingCustomWords() {
            CreateCustomWordsCommand command = CreateCustomWordsCommandBuilder.builder()
                .id("test-word")
                .build();

            boolean result = customWordsCommandService.addCustomWords(command);

            assertThat(result).isFalse();
        }
    }

    @Nested
    class UpdateCustomWords {

        @Test
        void shouldReturnResultWhenUpdatingCustomWords() {
            UpdateCustomWordsCommand command = UpdateCustomWordsCommandBuilder.builder().build();

            boolean result = customWordsCommandService.updateCustomWords(command);

            assertThat(result).isFalse();
        }
    }

    @Nested
    class DeleteCustomWords {

        @Test
        void shouldReturnResultWhenDeletingById() {
            boolean result = customWordsCommandService.deleteCustomWords("1");

            assertThat(result).isFalse();
        }

        @Test
        void shouldReturnResultWhenDeletingNonExistentId() {
            boolean result = customWordsCommandService.deleteCustomWords("99999");

            assertThat(result).isFalse();
        }
    }

    @Nested
    class DeleteBathByName {

        @Test
        void shouldReturnResultWhenDeletingByNames() {
            boolean result = customWordsCommandService.deleteBathByName(List.of("word1", "word2"));

            assertThat(result).isFalse();
        }

        @Test
        void shouldReturnResultWhenDeletingByEmptyNames() {
            boolean result = customWordsCommandService.deleteBathByName(List.of());

            assertThat(result).isFalse();
        }
    }
}
