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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.taotao.cloud.goods.application.service.command.SpecificationCommandService;
import com.taotao.cloud.goods.application.service.query.SpecificationQueryService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SpecificationManagerController.class)
public class SpecificationManagerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private SpecificationQueryService specificationQueryService;

	@MockitoBean
	private SpecificationCommandService specificationCommandService;

	@Nested
	class QueryAll {

		@Test
		void shouldReturnNull() throws Exception {
			mockMvc.perform(get("/manager/goods/spec/query/all"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class QueryPage {

		@Test
		void shouldReturnNull() throws Exception {
			mockMvc.perform(get("/manager/goods/spec/query/page")
					.param("specName", "颜色"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class Create {

		@Test
		void shouldCreateSuccessfully() throws Exception {
			mockMvc.perform(post("/manager/goods/spec/command/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"specName\":\"颜色\",\"specValue\":\"红,蓝\"}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class Update {

		@Test
		void shouldUpdateSuccessfully() throws Exception {
			mockMvc.perform(post("/manager/goods/spec/command/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"id\":1,\"specName\":\"尺寸\",\"specValue\":\"S,M,L\"}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class DeleteBatch {

		@Test
		void shouldDeleteBatchSuccessfully() throws Exception {
			mockMvc.perform(post("/manager/goods/spec/command/delete-batch")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"ids\":[1,2,3]}"))
				.andExpect(status().isOk());
		}
	}
}
