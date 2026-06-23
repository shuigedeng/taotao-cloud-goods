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

import com.taotao.cloud.goods.application.dto.category.result.CategoryBrandResult;
import com.taotao.cloud.goods.application.dto.category.result.CategoryBrandResultBuilder;
import com.taotao.cloud.goods.application.dto.specification.result.SpecificationResult;
import com.taotao.cloud.goods.application.service.query.CategorySpecificationQueryService;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * CategorySpecificationSellerController WebMvc 测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-06-22
 */
@WebMvcTest(CategorySpecificationSellerController.class)
public class CategorySpecificationSellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategorySpecificationQueryService categorySpecificationQueryService;

    @Nested
    class QueryByCategoryId {

        @Test
        void shouldReturnSpecificationsWhenCategoryExists() throws Exception {
			CategoryBrandResult build = CategoryBrandResultBuilder.builder().id("1").build();
			when(categorySpecificationQueryService.queryByCategoryId(anyLong()))
                .thenReturn(List.of(
                ));

            mockMvc.perform(get("/seller/goods/category/spec/query/category-id")
                    .param("categoryId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].specName").value("颜色"));
        }

        @Test
        void shouldReturnEmptyListWhenCategoryNotExists() throws Exception {
            when(categorySpecificationQueryService.queryByCategoryId(99999L))
                .thenReturn(List.of());

            mockMvc.perform(get("/seller/goods/category/spec/query/category-id")
                    .param("categoryId", "99999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
        }
    }
}
