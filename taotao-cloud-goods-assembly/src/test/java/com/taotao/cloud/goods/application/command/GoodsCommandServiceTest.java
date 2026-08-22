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
import com.taotao.cloud.goods.application.dto.goods.command.*;
import com.taotao.cloud.goods.application.dto.store.command.StoreIdCommand;
import com.taotao.cloud.goods.application.dto.store.command.UpdateStoreParamsCommand;
import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

/**
 * 商品命令服务集成测试
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:00:15
 */
@DisplayName("商品命令服务集成测试")
class GoodsCommandServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private GoodsCommandService goodsCommandService;

    @Nested
    @DisplayName("下架所有商家商")
    class UnderStoreGoods {

        @Test
        @DisplayName("正常下架商家商品")
        void shouldUnderStoreGoods() {
            StoreIdCommand command = new StoreIdCommand(1L);
            assertThatNoException().isThrownBy(() -> goodsCommandService.underStoreGoods(command));
        }
    }

    @Nested
    @DisplayName("更新商品参数")
    class UpdateGoodsParams {

        @Test
        @DisplayName("正常更新商品参数")
        void shouldUpdateGoodsParams() {
            UpdateStoreParamsCommand command = new UpdateStoreParamsCommand(1L, null);
            assertThatNoException().isThrownBy(() -> goodsCommandService.updateGoodsParams(command));
        }
    }

    @Nested
    @DisplayName("添加商品")
    class AddGoods {

        @Test
        @DisplayName("正常添加商品")
        void shouldAddGoods() {
//            SaveGoodsCommand command = new SaveGoodsCommand(null, null, null, null,
//                    null, null, null, null, null, null,
//                    null, null, null, null, null);
//            assertThatNoException().isThrownBy(() -> goodsCommandService.addGoods(command));
        }
    }

    @Nested
    @DisplayName("修改商品")
    class EditGoods {

        @Test
        @DisplayName("正常修改商品")
        void shouldEditGoods() {
//            SaveGoodsCommand command = new SaveGoodsCommand(null, null, null, null,
//                    null, null, null, null, null, null,
//                    null, null, null, null, null);
//            assertThatNoException().isThrownBy(() -> goodsCommandService.editGoods(command));
        }
    }

    @Nested
    @DisplayName("批量审核商品")
    class AuditGoods {

        @Test
        @DisplayName("正常审核商品")
        void shouldAuditGoods() {
//            AuditGoodsCommand command = new AuditGoodsCommand(List.of(1L), "PASS");
//            assertThatNoException().isThrownBy(() -> goodsCommandService.auditGoods(command));
        }
    }

    @Nested
    @DisplayName("更新商品上架状")
    class UpdateGoodsMarketAble {

        @Test
        @DisplayName("正常更新商品上架状")
        void shouldUpdateGoodsMarketAble() {
//            MarketAbleGoodsCommand command = new MarketAbleGoodsCommand(List.of(1L), "TRUE");
//            assertThatNoException().isThrownBy(() -> goodsCommandService.updateGoodsMarketAble(command));
        }
    }

    @Nested
    @DisplayName("删除商品")
    class DeleteGoods {

        @Test
        @DisplayName("正常删除商品")
        void shouldDeleteGoods() {
            GoodsIdsCommand command = new GoodsIdsCommand(List.of(1L));
            assertThatNoException().isThrownBy(() -> goodsCommandService.deleteGoods(command));
        }
    }

    @Nested
    @DisplayName("设置商品运费模板")
    class Freight {

        @Test
        @DisplayName("正常设置运费模板")
        void shouldSetFreight() {
            FreightGoodsCommand command = new FreightGoodsCommand(List.of(1L), 1L);
            assertThatNoException().isThrownBy(() -> goodsCommandService.freight(command));
        }
    }

    @Nested
    @DisplayName("修改商品库存数量")
    class UpdateStock {

        @Test
        @DisplayName("正常修改库存数量")
        void shouldUpdateStock() {
            UpdateStockGoodsCommand command = new UpdateStockGoodsCommand(1L, 100);
            assertThatNoException().isThrownBy(() -> goodsCommandService.updateStock(command));
        }
    }

    @Nested
    @DisplayName("更新商品评价数量")
    class UpdateGoodsCommentNum {

        @Test
        @DisplayName("正常更新评价数量")
        void shouldUpdateGoodsCommentNum() {
            GoodsIdCommand command = new GoodsIdCommand(1L);
            assertThatNoException().isThrownBy(() -> goodsCommandService.updateGoodsCommentNum(command));
        }
    }

    @Nested
    @DisplayName("更新商品购买数量")
    class UpdateGoodsBuyCount {

        @Test
        @DisplayName("正常更新购买数量")
        void shouldUpdateGoodsBuyCount() {
            UpdateGoodsBuyCountCommand command = new UpdateGoodsBuyCountCommand(1L, 10);
            assertThatNoException().isThrownBy(() -> goodsCommandService.updateGoodsBuyCount(command));
        }
    }

    @Nested
    @DisplayName("创建商品")
    class CreateGoods {

        @Test
        @DisplayName("正常创建商品")
        void shouldCreateGoods() {
//            CreateGoodsCommand command = new CreateGoodsCommand(null, null, null, null);
//            var result = goodsCommandService.createGoods(command);
//            assertThat(result).isNull();
        }
    }

    @Nested
    @DisplayName("处理Kafka通知")
    class HandleKafkaNotify {

        @Test
        @DisplayName("正常处理Kafka通知")
        void shouldHandleKafkaNotify() {
//            NotifyGoodsCommand command = new NotifyGoodsCommand("test-topic", "test-key", "test-value");
//            assertThatNoException().isThrownBy(() -> goodsCommandService.handleKafkaNotify(command));
        }
    }

    @Nested
    @DisplayName("定时自动创建商品")
    class ScheduleAutoCreateGoods {

        @Test
        @DisplayName("正常定时自动创建商品")
        void shouldScheduleAutoCreateGoods() {
//            ScheduleAutoCreateGoodsCommand command = new ScheduleAutoCreateGoodsCommand();
//            assertThatNoException().isThrownBy(() -> goodsCommandService.scheduleAutoCreateGoods(command));
        }
    }

    @Nested
    @DisplayName("处理商品创建事件")
    class HandleGoodsCreatedEvent {

        @Test
        @DisplayName("正常处理商品创建事件")
        void shouldHandleGoodsCreatedEvent() {
//            GoodsCreatedHandleCommand command = new GoodsCreatedHandleCommand(1L);
//            assertThatNoException().isThrownBy(() -> goodsCommandService.handleGoodsCreatedEvent(command));
        }
    }
}
