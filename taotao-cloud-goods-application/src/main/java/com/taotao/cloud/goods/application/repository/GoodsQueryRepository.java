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

package com.taotao.cloud.goods.application.repository;

import com.taotao.boot.common.model.result.PageResult;
import com.taotao.cloud.goods.application.dto.brand.result.BrandResult;
import com.taotao.cloud.goods.application.dto.goods.query.GoodsPageQuery;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsResult;

import java.util.List;

public interface GoodsQueryRepository {

	PageResult<GoodsResult> queryGoodsPage( GoodsPageQuery goodsPageQuery );
	PageResult<GoodsResult> queryMutilTalbePage( GoodsPageQuery goodsPageQuery );

	List<GoodsResult> queryByBrandIds( List<Long> brandIds );

	GoodsResult queryById( Long goodsId );

	Long queryCountStoreGoodsNum( Long storeId );
}
