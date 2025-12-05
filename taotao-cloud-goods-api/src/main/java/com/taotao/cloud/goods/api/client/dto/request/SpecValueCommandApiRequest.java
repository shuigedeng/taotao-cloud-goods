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

package com.taotao.cloud.goods.api.client.dto.request;

import com.taotao.boot.common.model.request.RequestBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/** 规格值 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class SpecValueCommandApiRequest extends RequestBase implements Serializable {

    @Serial
    private static final long serialVersionUID = -4433579132929428572L;

    @Schema(description = "规格项名字")
    private String specName;

    @Schema(description = "规格值")
    private String specValue;

    @Schema(description = "该规格是否有图片，1 有 0 没有")
    private Integer specType;

    @Schema(description = "规格的图片")
    private List<SpecImages> specImage;

    @Setter
@Getter
@ToString
    public static class SpecImages implements Serializable {

        private static final long serialVersionUID = 1816357809660916086L;

        private String url;

        private String name;

        private String status;
    }
}
