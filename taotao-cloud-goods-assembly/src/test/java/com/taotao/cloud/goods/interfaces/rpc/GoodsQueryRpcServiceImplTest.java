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

package com.taotao.cloud.goods.interfaces.rpc;

import com.taotao.boot.common.model.request.Request;
import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.api.rpc.dto.query.GoodsRpcQuery;
import com.taotao.cloud.goods.api.rpc.query.GoodsQueryRpcService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商品查询RPC服务集成测试
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 20:26:36
 */
@DisplayName("商品查询RPC服务集成测试")
class GoodsQueryRpcServiceImplTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private GoodsQueryRpcService goodsQueryRpcService;

    @Test
    @DisplayName("服务可以正常注入")
    void shouldInjectService() {
        assertThat(goodsQueryRpcService).isNotNull();
    }

    @Test
    @DisplayName("根据参数查询商品")
    void shouldQueryGoodsByParams() {
        Request<GoodsRpcQuery> request = Request.from(new GoodsRpcQuery(1L));
        var result = goodsQueryRpcService.queryGoodsByParams(request);
        assertThat(result).isNotNull();
    }
}
