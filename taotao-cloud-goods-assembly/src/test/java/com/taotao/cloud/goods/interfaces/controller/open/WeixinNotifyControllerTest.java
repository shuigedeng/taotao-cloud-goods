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

package com.taotao.cloud.goods.interfaces.controller.open;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.taotao.cloud.goods.application.service.command.BrandCommandService;
import com.taotao.cloud.goods.application.service.query.BrandQueryService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WeixinNotifyController.class)
public class WeixinNotifyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private BrandCommandService brandCommandService;

	@MockitoBean
	private BrandQueryService brandQueryService;

	@Nested
	class Notify {

		@Test
		void shouldHandleWeixinNotifySuccessfully() throws Exception {
			mockMvc.perform(post("/open/goods/open/weixin/notify")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"test\"}"))
				.andExpect(status().isOk());
		}
	}
}
