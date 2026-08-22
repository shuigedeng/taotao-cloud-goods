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

import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.application.dto.goods.command.UpdateGoodsSkuStockCommand;
import com.taotao.cloud.goods.application.service.command.GoodsSkuCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商品SKU命令服务集成测试
 *
 * @author shuigedeng
 * @version 2023.07
 * @since 2023-08-18 16:00:58
 */
@DisplayName("商品SKU命令服务集成测试")
class GoodsSkuCommandServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private GoodsSkuCommandService goodsSkuCommandService;

    @Nested
    @DisplayName("清除SKU缓存")
    class ClearCache {

        @Test
        @DisplayName("正常清除SKU缓存")
        void shouldClearCache() {
            boolean result = goodsSkuCommandService.clearCache(1L);
            assertThat(result).isTrue();
        }
    }

    @Nested
    @DisplayName("根据店铺ID更新SKU状")
    class UpdateGoodsSkuStatusByStoreId {

        @Test
        @DisplayName("正常更新SKU状")
        void shouldUpdateGoodsSkuStatus() {
            boolean result = goodsSkuCommandService.updateGoodsSkuStatusByStoreId(1L, "TRUE", "TRUE");
            assertThat(result).isTrue();
        }
    }

    @Nested
    @DisplayName("批量更新SKU库存")
    class UpdateStocks {

        @Test
        @DisplayName("正常批量更新库存")
        void shouldUpdateStocks() {
            UpdateGoodsSkuStockCommand cmd1 = new UpdateGoodsSkuStockCommand(1L, 100);
            UpdateGoodsSkuStockCommand cmd2 = new UpdateGoodsSkuStockCommand(2L, 200);
            boolean result = goodsSkuCommandService.updateStocks(List.of(cmd1, cmd2));
            assertThat(result).isTrue();
        }
    }

    @Nested
    @DisplayName("更新SKU库存")
    class UpdateStock {

        @Test
        @DisplayName("正常更新库存")
        void shouldUpdateStock() {
            boolean result = goodsSkuCommandService.updateStock(1L, 100);
            assertThat(result).isTrue();
        }
    }

    @Nested
    @DisplayName("更新SKU评价数量")
    class UpdateGoodsSkuCommentNum {

        @Test
        @DisplayName("正常更新评价数量")
        void shouldUpdateGoodsSkuCommentNum() {
            boolean result = goodsSkuCommandService.updateGoodsSkuCommentNum(1L);
            assertThat(result).isTrue();
        }
    }
}
