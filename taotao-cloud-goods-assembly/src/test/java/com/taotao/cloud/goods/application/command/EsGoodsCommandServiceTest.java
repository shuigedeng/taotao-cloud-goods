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
import com.taotao.cloud.goods.application.service.command.EsGoodsCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商品索引命令服务集成测试
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:00:00
 */
@DisplayName("商品索引命令服务集成测试")
class EsGoodsCommandServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private EsGoodsCommandService esGoodsCommandService;

    @Test
    @DisplayName("商品索引服务可以正常注入")
    void shouldInjectService() {
        assertThat(esGoodsCommandService).isNotNull();
    }
}
