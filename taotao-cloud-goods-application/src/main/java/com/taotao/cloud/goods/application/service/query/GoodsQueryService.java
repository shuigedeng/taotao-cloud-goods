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
import com.taotao.cloud.goods.application.dto.goods.clientobject.GoodsSkuParamsCO;

/**
 * 商品业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:00:15
 */
public interface GoodsQueryService extends QueryService {

    /**
     * 根据品牌获取商品
     *
     * @param brandIds 品牌ids
     * @return {@link List }<{@link GoodsPO }>
     * @since 2022-04-27 17:00:15
     */
    //	List<GoodsPO> getByBrandIds(List<Long> brandIds);

    /**
     * 查询商品CO
     *
     * @param goodsId 商品id
     * @return {@link GoodsSkuParamsCO }
     * @since 2022-04-27 17:00:16
     */
    GoodsSkuParamsCO getGoodsVO(Long goodsId);

    //    /**
    //     * 商品查询
    //     *
    //     * @param goodsPageQry 查询参数
    //     * @return {@link IPage }<{@link GoodsPO }>
    //     * @since 2022-04-27 17:00:16
    //     */
    //    IPage<GoodsPO> goodsQueryPage(GoodsPageQry goodsPageQry);
    //
    //    /**
    //     * 商品查询
    //     *
    //     * @param goodsPageQry 查询参数
    //     * @return {@link List }<{@link GoodsPO }>
    //     * @since 2022-04-27 17:00:16
    //     */
    //    List<GoodsPO> queryListByParams(GoodsPageQry goodsPageQry);

    /**
     * 批量更新商品的店铺信息
     *
     * @param store
     */
    // boolean updateStoreDetail(Store store);

    /**
     * 统计店铺的商品数量
     *
     * @param storeId 店铺id
     * @return {@link Long }
     * @since 2022-04-27 17:00:16
     */
    Long countStoreGoodsNum(Long storeId);
}
