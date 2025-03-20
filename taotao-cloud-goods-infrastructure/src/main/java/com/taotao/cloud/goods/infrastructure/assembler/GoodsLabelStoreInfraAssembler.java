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

package com.taotao.cloud.goods.infrastructure.assembler;

import com.taotao.cloud.goods.application.dto.store.clientobject.StoreGoodsLabelInfoCO;
import com.taotao.cloud.goods.application.dto.store.cmmond.StoreGoodsLabelAddCmd;
import com.taotao.cloud.goods.application.dto.store.cmmond.StoreGoodsLabelEditCmd;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.StoreGoodsLabelPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * IGoodsLabelStoreMapStruct
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:58:16
 */
@Mapper
public interface GoodsLabelStoreInfraAssembler {

    /** 实例 */
    GoodsLabelStoreInfraAssembler INSTANCE = Mappers.getMapper(GoodsLabelStoreInfraAssembler.class);

    /**
     * 商店商品标签存储货物标签信息签证官
     *
     * @param storeGoodsLabel 商店商品标签
     * @return {@link StoreGoodsLabelInfoVO }
     * @since 2022-04-27 16:58:17
     */
    StoreGoodsLabelInfoCO convert(StoreGoodsLabelPO storeGoodsLabel);

    /**
     * 商店商品标签dtoto商店商品标签
     *
     * @param storeGoodsLabelDTO 商店商品标签dto
     * @return {@link StoreGoodsLabel }
     * @since 2022-04-27 16:58:17
     */
    StoreGoodsLabelPO convert(StoreGoodsLabelAddCmd storeGoodsLabelDTO);

    StoreGoodsLabelPO convert(StoreGoodsLabelEditCmd storeGoodsLabelDTO);
}
