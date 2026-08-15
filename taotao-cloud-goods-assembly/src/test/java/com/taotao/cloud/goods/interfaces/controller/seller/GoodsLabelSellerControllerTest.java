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

import com.taotao.cloud.goods.application.dto.store.result.StoreGoodsLabelResult;
import com.taotao.cloud.goods.application.dto.store.result.StoreGoodsLabelResultBuilder;
import com.taotao.cloud.goods.application.service.command.StoreGoodsLabelCommandService;
import com.taotao.cloud.goods.application.service.query.StoreGoodsLabelQueryService;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SellerGoodsLabelController.class)
public class GoodsLabelSellerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private StoreGoodsLabelQueryService storeGoodsLabelQueryService;

	@MockitoBean
	private StoreGoodsLabelCommandService storeGoodsLabelCommandService;

	@Nested
	class QueryList {

		@Test
		void shouldReturnLabelList() throws Exception {
			StoreGoodsLabelResult label = StoreGoodsLabelResultBuilder.builder()
				.id(1L)
				.labelName("热销")
//				.storeId(1L)
				.build();
			when(storeGoodsLabelQueryService.queryByStoreId(anyLong()))
				.thenReturn(List.of(label));

			mockMvc.perform(get("/seller/goods/label/query/list"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].labelName").value("热销"));
		}

		@Test
		void shouldReturnEmptyListWhenNoLabels() throws Exception {
			when(storeGoodsLabelQueryService.queryByStoreId(anyLong()))
				.thenReturn(List.of());

			mockMvc.perform(get("/seller/goods/label/query/list"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").isEmpty());
		}
	}

	@Nested
	class QueryDetail {

		@Test
		void shouldReturnNull() throws Exception {
			mockMvc.perform(get("/seller/goods/label/query/detail")
					.param("id", "1"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class Create {

		@Test
		void shouldCreateSuccessfully() throws Exception {
			mockMvc.perform(post("/seller/goods/label/command/add")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"labelName\":\"新品\"}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class Update {

		@Test
		void shouldUpdateSuccessfully() throws Exception {
			mockMvc.perform(post("/seller/goods/label/command/edit")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"id\":1,\"labelName\":\"热卖\"}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class Delete {

		@Test
		void shouldDeleteSuccessfully() throws Exception {
			mockMvc.perform(post("/seller/goods/label/command/delete")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"id\":1}"))
				.andExpect(status().isOk());
		}
	}
}
