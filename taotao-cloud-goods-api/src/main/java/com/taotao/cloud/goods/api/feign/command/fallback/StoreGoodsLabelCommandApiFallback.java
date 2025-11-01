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

package com.taotao.cloud.goods.api.feign.command.fallback;

import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.BatchResponse;
import com.taotao.boot.common.model.response.Response;
import com.taotao.cloud.goods.api.feign.command.StoreGoodsLabelCommandApi;
import com.taotao.cloud.goods.api.feign.dto.request.GoodsCommandApiRequest;
import com.taotao.cloud.goods.api.feign.dto.response.StoreGoodsLabelCommandApiResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FallbackFactory;

public class StoreGoodsLabelCommandApiFallback implements FallbackFactory<StoreGoodsLabelCommandApi> {

	@Override
	public StoreGoodsLabelCommandApi create(Throwable throwable) {
		return new StoreGoodsLabelCommandApi() {
			@Override
			public Response<BatchResponse<StoreGoodsLabelCommandApiResponse>> listByStoreId(Request<GoodsCommandApiRequest> id) {
				return null;
			}
		};
	}
}
