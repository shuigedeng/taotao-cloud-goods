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

package com.taotao.cloud.goods.application.service.query;

import com.taotao.boot.ddd.model.application.service.QueryService;

/**
 * 商品相册业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:00:12
 */
public interface GoodsGalleryQueryService extends QueryService {

    /**
     * 根据原图获取缩略图
     *
     * @param origin 原图地址
     * @return {@link GoodsGalleryPO }
     * @since 2022-04-27 17:00:12
     */
    //	GoodsGalleryPO getGoodsGallery(String origin);
    //
    //	/**
    //	 * 根据商品id查询商品相册原图
    //	 *
    //	 * @param goodsId 商品ID
    //	 * @return {@link List }<{@link GoodsGalleryPO }>
    //	 * @since 2022-04-27 17:00:12
    //	 */
    //	List<GoodsGalleryPO> goodsGalleryList(Long goodsId);
}
