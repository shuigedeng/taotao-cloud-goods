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
import com.taotao.cloud.goods.application.dto.commodity.command.CreateCommodityCommand;
import com.taotao.cloud.goods.application.dto.commodity.command.CreateCommodityCommandBuilder;
import com.taotao.cloud.goods.application.service.command.CommodityCommandService;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CommodityCommandService 集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-08-22
 */
class CommodityCommandServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private CommodityCommandService commodityCommandService;

    @Nested
    class AddCommodity {

        @Test
        void shouldReturnResultWhenAddingCommodities() {
            CreateCommodityCommand command = CreateCommodityCommandBuilder.builder()
                .goodsId(1L)
                .name("test-goods")
                .url("https://example.com/goods")
                .auditStatus(0)
                .build();

            boolean result = commodityCommandService.addCommodity(List.of(command));

            assertThat(result).isFalse();
        }

        @Test
        void shouldReturnResultWhenAddingEmptyList() {
            boolean result = commodityCommandService.addCommodity(List.of());

            assertThat(result).isFalse();
        }
    }

    @Nested
    class DeleteCommodity {

        @Test
        void shouldReturnResultWhenDeletingExistingGoodsId() {
            boolean result = commodityCommandService.deleteCommodity(1L);

            assertThat(result).isFalse();
        }

        @Test
        void shouldReturnResultWhenDeletingNonExistentGoodsId() {
            boolean result = commodityCommandService.deleteCommodity(99999L);

            assertThat(result).isFalse();
        }
    }
}
