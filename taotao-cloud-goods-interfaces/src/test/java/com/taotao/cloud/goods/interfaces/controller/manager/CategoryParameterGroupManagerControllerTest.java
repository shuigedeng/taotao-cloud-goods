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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.taotao.cloud.goods.application.dto.parameter.result.CategoryParameterGroupResult;
import com.taotao.cloud.goods.application.service.query.CategoryParameterGroupQueryService;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.bean.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * CategoryParameterGroupManagerController WebMvc 测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-06-22
 */
@WebMvcTest(CategoryParameterGroupManagerController.class)
public class CategoryParameterGroupManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryParameterGroupQueryService categoryParameterGroupQueryService;

    @Nested
    class QueryByCategoryId {

        @Test
        void shouldReturnGroupsWhenCategoryExists() throws Exception {
            when(categoryParameterGroupQueryService.queryByCategoryId(anyLong()))
                .thenReturn(List.of(
                    CategoryParameterGroupResult.builder().id(1L).groupName("基本参数").build()
                ));

            mockMvc.perform(get("/manager/category/parameters/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].groupName").value("基本参数"));
        }

        @Test
        void shouldReturnEmptyListWhenCategoryNotExists() throws Exception {
            when(categoryParameterGroupQueryService.queryByCategoryId(99999L))
                .thenReturn(List.of());

            mockMvc.perform(get("/manager/category/parameters/99999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
        }
    }
}
