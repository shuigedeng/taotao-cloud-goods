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

import com.taotao.boot.common.model.request.BatchRequest;
import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.BatchResponse;
import com.taotao.boot.common.model.response.EmptyResponse;
import com.taotao.boot.common.model.response.Response;
import com.taotao.cloud.goods.api.feign.command.GoodsSkuCommandApi;
import com.taotao.cloud.goods.api.feign.dto.request.GoodsCommandApiRequest;
import com.taotao.cloud.goods.api.feign.dto.request.GoodsSkuSpecGalleryCommandApiRequest;
import com.taotao.cloud.goods.api.feign.dto.response.CategoryCommandApiResponse;
import com.taotao.cloud.goods.api.feign.dto.response.GoodsSkuSpecGalleryCommandApiResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * FeignGoodsSkuServiceFallback
 *
 * @author shuigedeng
 * @since 2020/4/29 21:43
 */
public class GoodsSkuCommandApiFallback implements FallbackFactory<GoodsSkuCommandApi> {
    @Override
    public GoodsSkuCommandApi create(Throwable throwable) {
        return new GoodsSkuCommandApi() {

			@Override
			public Response<EmptyResponse> updateGoodsStuck(
				Request<BatchRequest<GoodsSkuSpecGalleryCommandApiRequest>> goodsSkus) {
				return null;
			}

			@Override
			public Response<EmptyResponse> updateBatchById(
				Request<BatchRequest<GoodsSkuSpecGalleryCommandApiRequest>> goodsSkus) {
				return null;
			}

			@Override
			public Response<BatchResponse<GoodsSkuSpecGalleryCommandApiResponse>> getGoodsSkuByIdFromCache(
				Request<GoodsCommandApiRequest> skuIds) {
				return null;
			}

			@Override
			public Response<CategoryCommandApiResponse> getStock(Request<GoodsCommandApiRequest> skuId) {
				return null;
			}
		};
    }
}
