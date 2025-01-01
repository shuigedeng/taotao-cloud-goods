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

package com.taotao.cloud.goods.application.dto.goods.clientobject;

import com.taotao.boot.ddd.model.application.ClientObject;
import com.taotao.cloud.goods.application.dto.specification.dto.clientobject.SpecValueCO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 商品规格CO */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsSkuSpecCO extends ClientObject {

    @Schema(description = "商品skuId")
    private Long skuId;

    @Schema(description = "商品sku所包含规格")
    private List<SpecValueCO> specValues;

    @Schema(description = "库存")
    private Integer quantity;
}
