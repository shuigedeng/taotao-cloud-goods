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

package com.taotao.cloud.goods.application.dto.specification.clientobject;

import com.taotao.boot.common.model.PageResult;
import com.taotao.boot.ddd.model.application.dto.ClientObject;
import com.taotao.cloud.goods.application.dto.goods.clientobject.GoodsSkuCO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 库存警告封装类
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-14 21:52:39
 */
@Setter
@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class StockWarningCO extends ClientObject {

    @Serial
    private static final long serialVersionUID = -7605952923416404638L;

    @Schema(description = "库存警告数量")
    private Integer stockWarningNum;

    @Schema(description = "商品SKU列表")
    private PageResult<GoodsSkuCO> goodsSkuPage;
}
