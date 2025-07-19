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

package com.taotao.cloud.goods.application.dto.hotwords.clientobject;

import com.taotao.boot.ddd.model.application.dto.ClientObject;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.*;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
// public class DeptCO extends TreeUtil.TreeNode<DeptCO> {
public class HotWordsCO extends ClientObject {

    @Serial private static final long serialVersionUID = 4116703987840123059L;

    @Schema(name = "sort", description = "部门排序")
    private Integer sort;
}
