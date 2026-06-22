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

import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.ddd.model.application.service.QueryService;
import com.taotao.cloud.goods.application.dto.goods.query.EsGoodsSearchQuery;
import com.taotao.cloud.goods.application.dto.goods.result.EsGoodsRelatedResult;
import com.taotao.cloud.goods.application.dto.goods.result.EsGoodsResult;
import java.util.List;

/**
 * ES商品搜索业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:00:07
 */
public interface EsGoodsQueryService extends QueryService {

    /**
     * 商品搜索
     *
     * @param query 搜索参数
     * @return ES商品分页结果
     */
    PageResult<EsGoodsResult> searchGoods(EsGoodsSearchQuery query);

    /**
     * 获取热门关键词
     *
     * @param count 热词数量
     * @return 热门关键词列表
     */
    List<String> queryHotWords(Integer count);

    /**
     * 获取筛选器
     *
     * @param query 搜索条件
     * @return 筛选器结果
     */
    EsGoodsRelatedResult querySelector(EsGoodsSearchQuery query);
}
