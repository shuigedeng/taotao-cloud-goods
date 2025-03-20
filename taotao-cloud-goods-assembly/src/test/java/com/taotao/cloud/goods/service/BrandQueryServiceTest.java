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

package com.taotao.cloud.goods.service;

import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.application.dto.brand.clientobject.BrandCO;
import com.taotao.cloud.goods.application.service.query.BrandQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BrandQueryServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private BrandQueryService brandQueryService;

    // @TtcTest(duration = 1000, reporter = HtmlReporter.class)
    @Test
    public void helloTest111() throws InterruptedException {

        BrandCO byId = brandQueryService.getById(1L);
        System.out.println("asdfasdfsadfsadf");
    }
}
