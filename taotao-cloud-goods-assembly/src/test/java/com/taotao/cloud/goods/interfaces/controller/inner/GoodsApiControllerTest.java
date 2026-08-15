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

package com.taotao.cloud.goods.interfaces.controller.inner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(InnerGoodsController.class)
public class GoodsApiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Nested
	class UpdateStoreDetail {

		@Test
		void shouldCallUpdateStoreDetail() throws Exception {
			mockMvc.perform(post("/inner/goods/command/store/detail")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"id\":1}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class UnderStoreGoods {

		@Test
		void shouldCallUnderStoreGoods() throws Exception {
			mockMvc.perform(post("/inner/goods/command/strore/goods")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"id\":1}"))
				.andExpect(status().isOk());
		}
	}

	@Nested
	class CountStoreGoodsNum {

		@Test
		void shouldCallCountStoreGoodsNum() throws Exception {
			mockMvc.perform(post("/inner/goods/command/strore/goods/num")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"storeId\":1}"))
				.andExpect(status().isOk());
		}
	}
}
