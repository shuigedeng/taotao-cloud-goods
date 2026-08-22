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
import com.taotao.cloud.goods.api.grpc.CountStoreGoodsNumGrpcCommand;
import com.taotao.cloud.goods.api.grpc.CountStoreGoodsNumGrpcCommandResponse;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

/**
 * 商品命令gRPC服务集成测试
 *
 * @author shuigedeng
 * @since 2022-04-27 17:00:00
 */
@DisplayName("商品命令gRPC服务集成测试")
class GoodsCommandGrpcServiceImplTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private GoodsCommandGrpcServiceImpl goodsCommandGrpcService;

    @Test
    @DisplayName("服务可以正常注入")
    void shouldInjectService() {
        assertThat(goodsCommandGrpcService).isNotNull();
    }

    @Test
    @DisplayName("统计店铺商品数量")
    void shouldCountStoreGoodsNum() {
        CountStoreGoodsNumGrpcCommand request = CountStoreGoodsNumGrpcCommand.newBuilder().build();
        StreamObserver<CountStoreGoodsNumGrpcCommandResponse> observer = new StreamObserver<>() {
            @Override
            public void onNext(CountStoreGoodsNumGrpcCommandResponse value) {}
            @Override
            public void onError(Throwable t) {}
            @Override
            public void onCompleted() {}
        };
        assertThatNoException().isThrownBy(() -> goodsCommandGrpcService.countStoreGoodsNum(request, observer));
    }
}
