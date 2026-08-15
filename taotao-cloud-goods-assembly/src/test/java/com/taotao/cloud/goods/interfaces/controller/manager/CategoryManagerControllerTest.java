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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.taotao.cloud.goods.application.dto.category.result.CategoryTreeResult;
import com.taotao.cloud.goods.application.service.command.CategoryCommandService;
import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import com.taotao.cloud.goods.application.service.query.CategoryQueryService;
import com.taotao.cloud.goods.application.service.query.GoodsQueryService;
import java.util.List;

import com.taotao.cloud.goods.interfaces.controller.admin.AdminCategoryController;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AdminCategoryController.class)
public class CategoryManagerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private CategoryQueryService categoryQueryService;

	@MockitoBean
	private CategoryCommandService categoryCommandService;

	@MockitoBean
	private GoodsQueryService goodsQueryService;

	@MockitoBean
	private GoodsCommandService goodsCommandService;

	@Nested
	class QueryChildrenByParentId {

		@Test
		void shouldReturnNull() throws Exception {
			mockMvc.perform(get("/admin/goods/category/query/children")
					.param("parentId", "1"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class QueryCategoryTreeResult {

		@Test
		void shouldReturnCategoryTree() throws Exception {
			CategoryTreeResult tree = new CategoryTreeResult();
			when(categoryQueryService.queryCategoryTreeResult())
				.thenReturn(List.of(tree));

			mockMvc.perform(get("/admin/goods/category/query/children/all"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].name").value("电子产品"));
		}

		@Test
		void shouldReturnEmptyListWhenNoCategories() throws Exception {
			when(categoryQueryService.queryCategoryTreeResult())
				.thenReturn(List.of());

			mockMvc.perform(get("/admin/goods/category/query/children/all"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").isEmpty());
		}
	}

	@Nested
	class Create {

		@Test
		void shouldCreateSuccessfully() throws Exception {
			mockMvc.perform(post("/admin/goods/category/command/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"新分类\",\"parentId\":0}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class Update {

		@Test
		void shouldUpdateSuccessfully() throws Exception {
			mockMvc.perform(post("/admin/goods/category/command/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"id\":1,\"name\":\"更新分类\"}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class Delete {

		@Test
		void shouldDeleteSuccessfully() throws Exception {
			mockMvc.perform(post("/admin/goods/category/command/delete")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"id\":1}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class Disable {

		@Test
		void shouldDisableSuccessfully() throws Exception {
			mockMvc.perform(post("/admin/goods/category/command/disable")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"id\":1,\"enable\":false}"))
				.andExpect(status().isOk());
		}
	}
}
