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

package com.taotao.cloud.goods.interfaces.feign;

import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.Response;
import com.taotao.boot.web.annotation.Api;
import com.taotao.boot.webagg.controller.ApiController;
import com.taotao.cloud.goods.api.feign.GoodsApi;
import com.taotao.cloud.goods.api.feign.request.GoodsApiRequest;
import com.taotao.cloud.goods.api.feign.response.GoodsApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * 移动端-字典API
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-10-09 14:24:19
 */
@Api
@Validated
@RestController
@Tag(name = "内部服务-商品API", description = "内部服务-商品API")
public class GoodsApiController extends ApiController implements GoodsApi {

    @Override
    public Response<GoodsApiResponse> updateStoreDetail(Request<GoodsApiRequest> id) {
        return null;
    }

    @Override
    public Response<GoodsApiResponse> underStoreGoods(Request<GoodsApiRequest> id) {
        return null;
    }

    @Override
    public Response<GoodsApiResponse> countStoreGoodsNum(
            Request<GoodsApiRequest> storeId) {
        return null;
    }
}
