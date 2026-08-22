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
import com.taotao.cloud.goods.application.service.command.StoreGoodsLabelCommandService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * StoreGoodsLabelCommandService 集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-08-22
 */
class StoreGoodsLabelCommandServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private StoreGoodsLabelCommandService storeGoodsLabelCommandService;

    @Nested
    class RemoveStoreGoodsLabel {

        @Test
        void shouldReturnFalseForNonexistentLabel() {
            boolean result = storeGoodsLabelCommandService.removeStoreGoodsLabel(99999L);
            assertThat(result).isFalse();
        }

        @Test
        void shouldReturnFalseForNullLabelId() {
            boolean result = storeGoodsLabelCommandService.removeStoreGoodsLabel(null);
            assertThat(result).isFalse();
        }
    }
}
