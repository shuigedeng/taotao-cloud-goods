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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.taotao.cloud.goods.application.service.command.ParametersCommandService;
import com.taotao.cloud.goods.application.service.query.ParametersQueryService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ParameterManagerController.class)
public class ParameterManagerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private ParametersQueryService parametersQueryService;

	@MockitoBean
	private ParametersCommandService parametersCommandService;

	@Nested
	class Create {

		@Test
		void shouldCreateSuccessfully() throws Exception {
			mockMvc.perform(post("/manager/goods/parameters/command/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"参数名\",\"groupId\":1}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class Update {

		@Test
		void shouldUpdateSuccessfully() throws Exception {
			mockMvc.perform(post("/manager/goods/parameters/command/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"更新参数\",\"groupId\":1}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class DeleteById {

		@Test
		void shouldDeleteSuccessfully() throws Exception {
			mockMvc.perform(post("/manager/goods/parameters/command/delete")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"id\":1}"))
				.andExpect(status().isOk());
		}
	}
}
