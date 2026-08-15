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

import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.Response;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.InnerController;
import com.taotao.cloud.goods.api.inner.command.GoodsCommandApi;
import com.taotao.cloud.goods.api.inner.dto.command.GoodsApiCommand;
import com.taotao.cloud.goods.api.inner.dto.response.GoodsCommandApiResponse;
import com.taotao.cloud.goods.api.inner.query.GoodsQueryApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
@Validated
@RequiredArgsConstructor
@RestController
@Tag(name = "内部服务端-商品API", description = "内部服务端-商品API")
public class InnerGoodsController extends InnerController implements GoodsCommandApi , GoodsQueryApi {

    @Override
	@RequestLogger
	@Operation(summary = "根据父id获取商品分类列表", description = "根据父id获取商品分类列表111")
    public Response<GoodsCommandApiResponse> updateStoreDetail(@Validated Request<GoodsApiCommand> id) {
        return null;
    }

    @Override
	@RequestLogger
	@Operation(summary = "根据父id获取商品分类列表", description = "根据父id获取商品分类列表222")
    public Response<GoodsCommandApiResponse> underStoreGoods(@Validated Request<GoodsApiCommand> id) {
        return null;
    }

    @Override
	@RequestLogger
	@Operation(summary = "根据父id获取商品分类列表", description = "根据父id获取商品分类列表33")
    public Response<GoodsCommandApiResponse> countStoreGoodsNum(
		@Validated Request<GoodsApiCommand> storeId) {
        return null;
    }

	@Override
	public Response<GoodsCommandApiResponse> queryStoreDetail( Request<GoodsApiCommand> id ) {
		return null;
	}

	@Override
	public Response<GoodsCommandApiResponse> queryUnderStoreGoods( Request<GoodsApiCommand> id ) {
		return null;
	}

	@Override
	public Response<GoodsCommandApiResponse> queryCountStoreGoodsNum( Request<GoodsApiCommand> storeId ) {
		return null;
	}
}
