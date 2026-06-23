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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.taotao.cloud.goods.application.dto.category.result.CategoryTreeResult;
import com.taotao.cloud.goods.application.service.query.CategoryQueryService;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * CategoryBuyerController WebMvc 测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-06-22
 */
@WebMvcTest(CategoryBuyerController.class)
public class CategoryBuyerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryQueryService categoryQueryService;

    @Nested
    class QueryCategoryTreeByParentId {

        @Test
        void shouldReturnCategoryTreeByParentId() throws Exception {
            CategoryTreeResult treeNode = new CategoryTreeResult();
            treeNode.setId(1L);
            when(categoryQueryService.queryCategoryTreeByParentId(anyLong()))
                .thenReturn(List.of(treeNode));

            mockMvc.perform(get("/buyer/goods/category/query/tree/parent-id")
                    .param("parentId", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1L));
        }

        @Test
        void shouldReturnEmptyListWhenParentIdNotExists() throws Exception {
            when(categoryQueryService.queryCategoryTreeByParentId(99999L))
                .thenReturn(List.of());

            mockMvc.perform(get("/buyer/goods/category/query/tree/parent-id")
                    .param("parentId", "99999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
        }
    }
}
