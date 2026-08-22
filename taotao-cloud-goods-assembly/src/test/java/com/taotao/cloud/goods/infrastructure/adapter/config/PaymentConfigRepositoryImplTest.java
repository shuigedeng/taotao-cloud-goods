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

package com.taotao.cloud.goods.infrastructure.adapter.config;

import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.application.adapter.config.PaymentConfigRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 支付配置仓储实现集成测试
 *
 * @author shuigedeng
 * @since 2022-04-27 17:00:00
 */
@DisplayName("支付配置仓储实现集成测试")
class PaymentConfigRepositoryImplTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private PaymentConfigRepository paymentConfigRepository;

    @Test
    @DisplayName("仓储可以正常注入")
    void shouldInjectRepository() {
        assertThat(paymentConfigRepository).isNotNull();
    }

    @Test
    @DisplayName("获取最大重试次数")
    void shouldGetMaxRetryTimes() {
        int result = paymentConfigRepository.getMaxRetryTimes();
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("获取折扣率")
    void shouldGetDiscountRate() {
        var result = paymentConfigRepository.getDiscountRate();
        assertThat(result).isNotNull();
    }
}
