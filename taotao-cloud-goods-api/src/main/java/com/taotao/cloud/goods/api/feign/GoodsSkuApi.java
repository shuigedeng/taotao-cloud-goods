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

package com.taotao.cloud.goods.api.feign;

import com.taotao.boot.common.constant.ServiceNameConstants;
import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.Response;
import com.taotao.cloud.goods.api.feign.fallback.CategoryApiFallback;
import com.taotao.cloud.goods.api.feign.fallback.GoodsSkuApiFallback;
import com.taotao.cloud.goods.api.feign.request.GoodsApiRequest;
import com.taotao.cloud.goods.api.feign.request.GoodsSkuSpecGalleryApiRequest;
import com.taotao.cloud.goods.api.feign.response.GoodsSkuSpecGalleryApiResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用订单模块
 *
 * @author shuigedeng
 * @since 2020/5/2 16:42
 */
@FeignClient(
	contextId = "GoodsSkuApi",
	value = ServiceNameConstants.TAOTAO_CLOUD_GOODS,
	fallbackFactory = GoodsSkuApiFallback.class)
public interface GoodsSkuApi {

	@PostMapping(value = "/product/updateGoodsStuck")
	Response<Boolean> updateGoodsStuck(@Validated @RequestBody Request<List<GoodsSkuSpecGalleryApiRequest>> goodsSkus);

	@PostMapping(value = "/product/updateBatchById")
	Response<Boolean> updateBatchById(@Validated @RequestBody Request<List<GoodsSkuSpecGalleryApiRequest>> goodsSkus);

	@PostMapping(value = "/product/getGoodsSkuByIdFromCache/sku-ids")
	Response<List<GoodsSkuSpecGalleryApiResponse>> getGoodsSkuByIdFromCache(@Validated @RequestBody Request<GoodsApiRequest> skuIds);

	@PostMapping(value = "/product/getStock")
	Response<Integer> getStock(@Validated @RequestBody Request<GoodsApiRequest> skuId);
}
