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

package com.taotao.cloud.goods.interfaces.controller.internal.query;

import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.Response;
import com.taotao.boot.security.spring.annotation.NotAuth;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.InternalController;
import com.taotao.cloud.goods.api.internal.dto.query.GoodsApiQuery;
import com.taotao.cloud.goods.api.internal.dto.response.GoodsApiResponse;
import com.taotao.cloud.goods.api.internal.query.GoodsQueryApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内部服务端-商品API
 * <p>
 * 提供内部服务调用的商品 API，用于微服务间的商品数据交互
 * </p>
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-10-09 14:24:19
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "内部服务端-商品QueryAPI", description = "内部服务端-商品QueryAPI")
public class GoodsQueryApiController extends InternalController implements  GoodsQueryApi {


	@Override
	@Operation(summary = "根据父id获取商品分类列表", description = "根据父id获取商品分类列表111")
	@RequestLogger
	@NotAuth
	public Response<GoodsApiResponse> queryStoreDetail(@Valid @RequestBody Request<GoodsApiQuery> request) {
		GoodsApiQuery goodsApiQuery = request.getOrder();
		return null;
	}

	@Override
	@Operation(summary = "根据父id获取商品分类列表", description = "根据父id获取商品分类列表111")
	@RequestLogger
	public Response<GoodsApiResponse> queryUnderStoreGoods(@Valid @RequestBody Request<GoodsApiQuery> request) {
		GoodsApiQuery goodsApiQuery = request.getOrder();
		return null;
	}

	@Override
	@Operation(summary = "根据父id获取商品分类列表", description = "根据父id获取商品分类列表111")
	@RequestLogger
	public Response<GoodsApiResponse> queryCountStoreGoodsNum(@Valid @RequestBody Request<GoodsApiQuery> request) {
		GoodsApiQuery ordgoodsApiQueryer = request.getOrder();
		return null;
	}
}
