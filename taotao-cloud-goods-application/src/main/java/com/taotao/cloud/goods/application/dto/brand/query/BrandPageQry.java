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

package com.taotao.cloud.goods.application.dto.brand.query;

import com.taotao.boot.common.model.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 商品品牌dto
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:33:01
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "商品品牌dto")
public class BrandPageQry extends PageQuery {

    @Serial
    private static final long serialVersionUID = 8906820486037326039L;

    @Schema(description = "品牌名称")
    private String name;

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
