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

package com.taotao.cloud.goods.application.dto.store.cmmond;

import com.taotao.boot.ddd.model.application.dto.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * StoreGoodsLabelCO
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-14 21:52:23
 */
@Setter
@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class StoreGoodsLabelAddCmd extends Command {

    @Serial
    private static final long serialVersionUID = -7605952923416404638L;

    @Schema(description = "店铺商品分类名称")
    private String labelName;

    @Schema(description = "层级, 从0开始")
    private Integer level;

    @Schema(description = "店铺商品分类排序")
    private Integer sortOrder;

    @Schema(description = "父id, 根节点为0")
    private Long parentId;

    @Schema(description = "店铺ID")
    private Long storeId;
}
