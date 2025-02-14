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

package com.taotao.cloud.goods.application.service;

import com.taotao.boot.ddd.model.application.service.CommandService;
import com.taotao.boot.webagg.service.BaseSuperService;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.GoodsGalleryPO;
import java.util.List;

/**
 * 商品相册业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:00:12
 */
public interface GoodsGalleryCommandService extends CommandService {

    /**
     * 添加商品相册
     *
     * @param goodsGalleryList 商品相册列表
     * @param goodsId 商品ID
     * @return {@link boolean }
     * @since 2022-04-27 17:00:12
     */
    boolean add(List<String> goodsGalleryList, Long goodsId);

}
