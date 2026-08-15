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

package com.taotao.cloud.goods.interfaces.controller.seller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.taotao.cloud.goods.application.dto.goods.result.GoodsSkuParamsResult;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsSkuParamsResultBuilder;
import com.taotao.cloud.goods.application.service.query.GoodsQueryService;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * GoodsSellerController WebMvc 测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-06-22
 */
@WebMvcTest(SellerGoodsController.class)
public class GoodsSellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GoodsQueryService goodsQueryService;

    @Nested
    class QueryByGoodsId {

        @Test
        void shouldReturnDetailWhenGoodsExists() throws Exception {
            GoodsSkuParamsResult detail = GoodsSkuParamsResultBuilder.builder()
                .categoryName(List.of("电子产品"))
                .build();
            when(goodsQueryService.queryDetail(anyLong())).thenReturn(detail);

            mockMvc.perform(get("/seller/goods/query/goods-id")
                    .param("goodsId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.categoryName[0]").value("电子产品"));
        }

        @Test
        void shouldReturnNullWhenGoodsNotExists() throws Exception {
            when(goodsQueryService.queryDetail(99999L)).thenReturn(null);

            mockMvc.perform(get("/seller/goods/query/goods-id")
                    .param("goodsId", "99999"))
                .andExpect(status().isOk());
        }
    }
}
