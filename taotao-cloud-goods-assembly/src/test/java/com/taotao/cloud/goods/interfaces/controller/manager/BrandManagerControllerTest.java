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

package com.taotao.cloud.goods.interfaces.controller.manager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.taotao.boot.common.model.result.PageResult;
import com.taotao.cloud.goods.application.dto.brand.query.BrandPageQuery;
import com.taotao.cloud.goods.application.dto.brand.result.BrandResult;
import com.taotao.cloud.goods.application.service.query.BrandQueryService;
import com.taotao.cloud.goods.interfaces.controller.admin.AdminBrandController;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * BrandManagerController WebMvc 测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-06-22
 */
@WebMvcTest(AdminBrandController.class)
public class BrandManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BrandQueryService brandQueryService;

    @Nested
    class QueryPage {

        @Test
        void shouldReturnPagedBrands() throws Exception {
            when(brandQueryService.queryPage(any(BrandPageQuery.class)))
                .thenReturn(PageResult.<BrandResult>builder().build());

            mockMvc.perform(get("/admin/goods/brand/query/page")
                    .param("name", "test"))
                .andExpect(status().isOk());
        }
    }
}
