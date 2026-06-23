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

import com.taotao.boot.common.model.result.PageResult;
import com.taotao.cloud.goods.application.dto.goods.query.GoodsPageQuery;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsResult;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsResultBuilder;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsSkuParamsResult;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsSkuParamsResultBuilder;
import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import com.taotao.cloud.goods.application.service.command.GoodsSkuCommandService;
import com.taotao.cloud.goods.application.service.query.GoodsQueryService;
import com.taotao.cloud.goods.application.service.query.GoodsSkuQueryService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GoodsManagerController.class)
public class GoodsManagerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private GoodsQueryService goodsQueryService;

	@MockitoBean
	private GoodsCommandService goodsCommandService;

	@MockitoBean
	private GoodsSkuQueryService goodsSkuQueryService;

	@MockitoBean
	private GoodsSkuCommandService goodsSkuCommandService;

	@Nested
	class QueryPage {

		@Test
		void shouldReturnPagedGoods() throws Exception {
			when(goodsQueryService.queryGoodsPage(any(GoodsPageQuery.class)))
				.thenReturn(PageResult.<GoodsResult>builder().build());

			mockMvc.perform(get("/manager/goods/query/page")
					.param("page", "1")
					.param("size", "10"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class QuerySkuPage {

		@Test
		void shouldReturnNull() throws Exception {
			mockMvc.perform(get("/manager/goods/query/sku/page")
					.param("page", "1")
					.param("size", "10"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class QueryAuthPage {

		@Test
		void shouldReturnNull() throws Exception {
			mockMvc.perform(get("/manager/goods/query/auth/page")
					.param("page", "1")
					.param("size", "10"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class Create {

		@Test
		void shouldReturnGoodsResultWhenCreated() throws Exception {
			GoodsResult result = GoodsResultBuilder.builder().id(1L).build();
			when(goodsCommandService.createGoods(any())).thenReturn(result);

			mockMvc.perform(post("/manager/goods/command/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"goodsName\":\"测试商品\",\"categoryId\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(1));
		}
	}

	@Nested
	class UnderGoods {

		@Test
		void shouldUnderSuccessfully() throws Exception {
			mockMvc.perform(post("/manager/goods/command/under")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"goodsIds\":[1,2],\"reason\":\"下架\"}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class UnderGoodsxx {

		@Test
		void shouldUnderxxSuccessfully() throws Exception {
			mockMvc.perform(post("/manager/goods/command/underxx")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"params\":\"test\"}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class AuthGoods {

		@Test
		void shouldAuthSuccessfully() throws Exception {
			mockMvc.perform(post("/manager/goods/command/auth")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"goodsIds\":[1],\"authFlag\":\"PASS\"}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class UnpGoods {

		@Test
		void shouldUpGoodsSuccessfully() throws Exception {
			mockMvc.perform(post("/manager/goods/command/up")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"goodsIds\":[1,2]}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class QueryDetail {

		@Test
		void shouldReturnDetailWhenGoodsExists() throws Exception {
			GoodsSkuParamsResult detail = GoodsSkuParamsResultBuilder.builder()
				.build();
			when(goodsQueryService.queryDetail(anyLong())).thenReturn(detail);

			mockMvc.perform(get("/manager/goods/query/detail")
					.param("id", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(1));
		}

		@Test
		void shouldReturnNullWhenGoodsNotExists() throws Exception {
			when(goodsQueryService.queryDetail(99999L)).thenReturn(null);

			mockMvc.perform(get("/manager/goods/query/detail")
					.param("id", "99999"))
				.andExpect(status().isOk());
		}
	}
}
