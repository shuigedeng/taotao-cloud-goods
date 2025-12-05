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

package com.taotao.cloud.goods.api.client.command;

import com.taotao.boot.common.constant.ServiceNameConstants;
import com.taotao.boot.common.model.request.BatchRequest;
import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.BatchResponse;
import com.taotao.boot.common.model.response.EmptyResponse;
import com.taotao.boot.common.model.response.Response;
import com.taotao.cloud.goods.api.client.command.fallback.GoodsSkuCommandApiFallback;
import com.taotao.cloud.goods.api.client.dto.request.GoodsCommandApiRequest;
import com.taotao.cloud.goods.api.client.dto.request.GoodsSkuSpecGalleryCommandApiRequest;
import com.taotao.cloud.goods.api.client.dto.response.CategoryCommandApiResponse;
import com.taotao.cloud.goods.api.client.dto.response.GoodsSkuSpecGalleryCommandApiResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * 远程调用订单模块
 *
 * @author shuigedeng
 * @since 2020/5/2 16:42
 */
@HttpExchange(value = ServiceNameConstants.TAOTAO_CLOUD_GOODS)
public interface GoodsSkuCommandApi {

	@PostExchange(value = "/product/updateGoodsStuck")
	Response<EmptyResponse> updateGoodsStuck(@Validated @RequestBody Request<BatchRequest<GoodsSkuSpecGalleryCommandApiRequest>> goodsSkus);

	@PostExchange(value = "/product/updateBatchById")
	Response<EmptyResponse> updateBatchById(@Validated @RequestBody Request<BatchRequest<GoodsSkuSpecGalleryCommandApiRequest>> goodsSkus);

	@PostExchange(value = "/product/getGoodsSkuByIdFromCache/sku-ids")
	Response<BatchResponse<GoodsSkuSpecGalleryCommandApiResponse>> getGoodsSkuByIdFromCache(@Validated @RequestBody Request<GoodsCommandApiRequest> skuIds);

	@PostExchange(value = "/product/getStock")
	Response<CategoryCommandApiResponse> getStock(@Validated @RequestBody Request<GoodsCommandApiRequest> skuId);
}
