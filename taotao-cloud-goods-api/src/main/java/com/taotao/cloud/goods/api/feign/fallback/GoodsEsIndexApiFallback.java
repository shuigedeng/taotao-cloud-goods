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

package com.taotao.cloud.goods.api.feign.fallback;

import com.taotao.boot.common.model.FeignRequest;
import com.taotao.boot.common.model.FeignResponse;
import com.taotao.cloud.goods.api.feign.GoodsEsIndexApi;
import com.taotao.cloud.goods.api.feign.request.GoodsApiRequest;
import com.taotao.cloud.goods.api.feign.response.EsGoodsIndexApiResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * FeignEsGoodsIndexServiceFallback
 *
 * @author shuigedeng
 * @since 2020/4/29 21:43
 */
public class GoodsEsIndexApiFallback implements FallbackFactory<GoodsEsIndexApi> {
    @Override
    public GoodsEsIndexApi create(Throwable throwable) {
        return new GoodsEsIndexApi() {
			@Override
			public FeignResponse<List<EsGoodsIndexApiResponse>> getEsGoodsBySkuIds(
				FeignRequest<List<GoodsApiRequest>> skuIdList) {
				return null;
			}

			@Override
			public FeignResponse<Boolean> cleanInvalidPromotion(FeignRequest<GoodsApiRequest> feignRequest) {
				return null;
			}
		};
    }
}
