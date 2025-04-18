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

package com.taotao.cloud.goods.application.dto.parameter.clientobject;

import com.taotao.boot.ddd.model.application.dto.ClientObject;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.util.List;
import lombok.*;
import lombok.experimental.Accessors;

/** 参数组vo */
@Setter
@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class ParameterGroupCO extends ClientObject {

    @Serial
    private static final long serialVersionUID = 724427321881170297L;

    @Schema(description = "参数组关联的参数集合")
    private List<ParametersCO> params;

    @Schema(description = "参数组名称")
    private String groupName;

    @Schema(description = "参数组id")
    private Long groupId;

    /**
     * 获取
     * @return params
     */
    public List<ParametersCO> getParams() {
        return params;
    }

    /**
     * 设置
     * @param params
     */
    public void setParams(List<ParametersCO> params) {
        this.params = params;
    }

    /**
     * 获取
     * @return groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取
     * @return groupId
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * 设置
     * @param groupId
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
