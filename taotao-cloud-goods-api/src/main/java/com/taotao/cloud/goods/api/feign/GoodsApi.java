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
import com.taotao.boot.common.model.FeignRequest;
import com.taotao.boot.common.model.FeignResponse;
import com.taotao.cloud.goods.api.feign.fallback.CategoryApiFallback;
import com.taotao.cloud.goods.api.feign.fallback.GoodsApiFallback;
import com.taotao.cloud.goods.api.feign.request.GoodsApiRequest;
import com.taotao.cloud.goods.api.feign.response.GoodsApiResponse;
import com.taotao.cloud.openfeign.annotation.FeignInner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 远程调用订单模块
 *
 * @author shuigedeng
 * @since 2020/5/2 16:42
 */
@FeignClient(
	contextId = "GoodsApi",
        value = ServiceNameConstants.TAOTAO_CLOUD_GOODS,
        fallbackFactory = GoodsApiFallback.class)
public interface GoodsApi {
	@FeignInner
    @PostMapping(value = "/product/strore/detail/{id:[0-9]*}")
	FeignResponse<GoodsApiResponse> updateStoreDetail(@Validated @RequestBody FeignRequest<GoodsApiRequest> id);

	@FeignInner
    @PostMapping(value = "/product/strore/goods/{id:[0-9]*}")
	FeignResponse<GoodsApiResponse> underStoreGoods(@Validated @RequestBody FeignRequest<GoodsApiRequest> id);

	@FeignInner
    @PostMapping(value = "/product/strore/goods/num/{storeId:[0-9]*}")
	FeignResponse<GoodsApiResponse> countStoreGoodsNum(@Validated @RequestBody FeignRequest<GoodsApiRequest> storeId);
}
