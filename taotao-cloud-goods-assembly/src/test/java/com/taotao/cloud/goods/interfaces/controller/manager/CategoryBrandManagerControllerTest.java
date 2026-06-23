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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.taotao.cloud.goods.application.dto.category.result.CategoryBrandResult;
import com.taotao.cloud.goods.application.dto.category.result.CategoryBrandResultBuilder;
import com.taotao.cloud.goods.application.service.command.CategoryBrandCommandService;
import com.taotao.cloud.goods.application.service.query.CategoryBrandQueryService;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CategoryBrandManagerController.class)
public class CategoryBrandManagerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private CategoryBrandCommandService categoryBrandCommandService;

	@MockitoBean
	private CategoryBrandQueryService categoryBrandQueryService;

	@Nested
	class QueryByCategoryId {

		@Test
		void shouldReturnBrandListWhenCategoryExists() throws Exception {
			CategoryBrandResult brand = CategoryBrandResultBuilder.builder()
				.build();
			when(categoryBrandQueryService.queryByCategoryId(anyLong()))
				.thenReturn(List.of(brand));

			mockMvc.perform(get("/manager/goods/category/brand/query/category-id")
					.param("categoryId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].id").value(1));
		}

		@Test
		void shouldReturnEmptyListWhenNoBrands() throws Exception {
			when(categoryBrandQueryService.queryByCategoryId(anyLong()))
				.thenReturn(List.of());

			mockMvc.perform(get("/manager/goods/category/brand/query/category-id")
					.param("categoryId", "999"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").isEmpty());
		}
	}

	@Nested
	class SaveCategoryBrand {

		@Test
		void shouldSaveSuccessfully() throws Exception {
			mockMvc.perform(post("/manager/goods/category/brand/command/category/brands")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"categoryId\":1,\"brandIds\":[1,2,3]}"))
				.andExpect(status().isOk());
		}
	}
}
