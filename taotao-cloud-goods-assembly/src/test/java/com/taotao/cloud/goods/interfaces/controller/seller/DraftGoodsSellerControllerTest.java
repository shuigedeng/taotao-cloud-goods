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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.taotao.cloud.goods.application.dto.draft.result.DraftGoodsSkuParamsResult;
import com.taotao.cloud.goods.application.dto.draft.result.DraftGoodsSkuParamsResultBuilder;
import com.taotao.cloud.goods.application.service.command.DraftGoodsCommandService;
import com.taotao.cloud.goods.application.service.query.DraftGoodsQueryService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DraftGoodsSellerController.class)
public class DraftGoodsSellerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private DraftGoodsQueryService draftGoodsQueryService;

	@MockitoBean
	private DraftGoodsCommandService draftGoodsCommandService;

	@Nested
	class QueryPage {

		@Test
		void shouldReturnNull() throws Exception {
			mockMvc.perform(get("/seller/goods/draft/goods/query/page")
					.param("page", "1")
					.param("size", "10"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class QueryDetail {

		@Test
		void shouldReturnDetailWhenDraftExists() throws Exception {
			DraftGoodsSkuParamsResult detail = DraftGoodsSkuParamsResultBuilder.builder()
//				.id(1L)
				.build();
			when(draftGoodsQueryService.queryDraftGoods(anyLong())).thenReturn(detail);

			mockMvc.perform(get("/seller/goods/draft/goods/query/detail")
					.param("id", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(1));
		}

		@Test
		void shouldReturnNullWhenDraftNotExists() throws Exception {
			when(draftGoodsQueryService.queryDraftGoods(99999L)).thenReturn(null);

			mockMvc.perform(get("/seller/goods/draft/goods/query/detail")
					.param("id", "99999"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class Create {

		@Test
		void shouldCreateSuccessfully() throws Exception {
			mockMvc.perform(post("/seller/goods/draft/goods/command/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"goodsName\":\"草稿商品\",\"categoryId\":1}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class DeleteBatch {

		@Test
		void shouldDeleteBatchSuccessfully() throws Exception {
			mockMvc.perform(post("/seller/goods/draft/goods/command/delete-batch")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"ids\":[1,2]}"))
				.andExpect(status().isOk());
		}
	}
}
