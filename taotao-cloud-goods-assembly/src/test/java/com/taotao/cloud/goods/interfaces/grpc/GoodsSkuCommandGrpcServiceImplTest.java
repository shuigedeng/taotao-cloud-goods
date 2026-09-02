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

package com.taotao.cloud.goods.interfaces.grpc;

import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

/**
 * 商品SKU命令gRPC服务集成测试
 *
 * @author shuigedeng
 * @since 2022-04-27 17:00:00
 */
@DisplayName("商品SKU命令gRPC服务集成测试")
class GoodsSkuCommandGrpcServiceImplTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private GoodsSkuCommandGrpcServiceImpl goodsSkuCommandGrpcService;

    private StreamObserver<GoodsSkuGrpcCommandResponse> createNoOpObserver() {
        return new StreamObserver<>() {
            @Override
            public void onNext(GoodsSkuGrpcCommandResponse value) {}
            @Override
            public void onError(Throwable t) {}
            @Override
            public void onCompleted() {}
        };
    }

    @Test
    @DisplayName("服务可以正常注入")
    void shouldInjectService() {
        assertThat(goodsSkuCommandGrpcService).isNotNull();
    }

    @Nested
    @DisplayName("更新商品库存")
    class UpdateGoodsStuck {

        @Test
        @DisplayName("正常更新商品库存")
        void shouldUpdateGoodsStuck() {
            GoodsSkuGrpcCommand request = GoodsSkuGrpcCommand.newBuilder().build();
            assertThatNoException().isThrownBy(
                    () -> goodsSkuCommandGrpcService.updateGoodsStuck(request, createNoOpObserver()));
        }
    }

    @Nested
    @DisplayName("批量更新")
    class UpdateBatchById {

        @Test
        @DisplayName("正常批量更新")
        void shouldUpdateBatchById() {
            GoodsSkuGrpcCommand request = GoodsSkuGrpcCommand.newBuilder().build();
            assertThatNoException().isThrownBy(
                    () -> goodsSkuCommandGrpcService.updateBatchById(request, createNoOpObserver()));
        }
    }

    @Nested
    @DisplayName("从缓存获取商品SKU")
    class GetGoodsSkuByIdFromCache {

        @Test
        @DisplayName("正常从缓存获取")
        void shouldGetGoodsSkuByIdFromCache() {
            GoodsSkuGrpcCommand request = GoodsSkuGrpcCommand.newBuilder().build();
            assertThatNoException().isThrownBy(
                    () -> goodsSkuCommandGrpcService.getGoodsSkuByIdFromCache(request, createNoOpObserver()));
        }
    }
}
