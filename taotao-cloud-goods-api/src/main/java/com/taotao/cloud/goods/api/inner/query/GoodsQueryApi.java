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

package com.taotao.cloud.goods.api.inner.query;

import com.taotao.boot.common.constant.ServiceNameConstants;
import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.Response;
import com.taotao.cloud.goods.api.inner.dto.request.GoodsCommandApiRequest;
import com.taotao.cloud.goods.api.inner.dto.response.GoodsCommandApiResponse;
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
public interface GoodsQueryApi {

    @PostExchange(value = "/product/strore/detail/{id:[0-9]*}")
	Response<GoodsCommandApiResponse> updateStoreDetail(@Validated @RequestBody Request<GoodsCommandApiRequest> id);


    @PostExchange(value = "/product/strore/goods/{id:[0-9]*}")
	Response<GoodsCommandApiResponse> underStoreGoods(@Validated @RequestBody Request<GoodsCommandApiRequest> id);


    @PostExchange(value = "/product/strore/goods/num/{storeId:[0-9]*}")
	Response<GoodsCommandApiResponse> countStoreGoodsNum(@Validated @RequestBody Request<GoodsCommandApiRequest> storeId);
}
