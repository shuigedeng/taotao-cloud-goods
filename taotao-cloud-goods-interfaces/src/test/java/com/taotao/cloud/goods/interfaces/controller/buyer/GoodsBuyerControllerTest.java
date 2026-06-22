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

package com.taotao.cloud.goods.interfaces.controller.buyer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.taotao.boot.common.model.Result;
import com.taotao.cloud.goods.application.dto.goods.query.GoodsOtherPageQuery;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsSkuResult;
import com.taotao.cloud.goods.application.service.query.GoodsQueryService;
import com.taotao.cloud.goods.application.service.query.GoodsSkuQueryService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.bean.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

/**
 * GoodsBuyerController WebMvc 测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-06-22
 */
@WebMvcTest(GoodsBuyerController.class)
public class GoodsBuyerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoodsQueryService goodsQueryService;

    @MockBean
    private GoodsSkuQueryService goodsSkuQueryService;

    @Nested
    class GetSkuByGoodsId {

        @Test
        void shouldReturnSkuWhenGoodsExists() throws Exception {
            when(goodsSkuQueryService.queryGoodsSkuDetail(1L))
                .thenReturn(GoodsSkuResult.builder().id(1L).build());

            mockMvc.perform(get("/buyer/goods/sku/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L));
        }

        @Test
        void shouldReturnNotFoundWhenGoodsNotExists() throws Exception {
            when(goodsSkuQueryService.queryGoodsSkuDetail(99999L)).thenReturn(null);

            mockMvc.perform(get("/buyer/goods/sku/99999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
        }
    }

    @Nested
    class QueryPage {

        @Test
        void shouldReturnPagedGoods() throws Exception {
            when(goodsQueryService.queryGoodsPage(any(GoodsOtherPageQuery.class)))
                .thenReturn(Result.success(new PageImpl<>(java.util.List.of())));

            mockMvc.perform(get("/buyer/goods/page")
                    .param("page", "1")
                    .param("size", "10"))
                .andExpect(status().isOk());
        }
    }
}
