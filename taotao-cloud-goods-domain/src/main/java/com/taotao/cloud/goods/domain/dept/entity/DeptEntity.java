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

package com.taotao.cloud.goods.domain.dept.entity;

import static lombok.AccessLevel.PRIVATE;

import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.ddd.domain.model.AggregateRoot;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
@Schema(name = "Dept", description = "部门")
public class DeptEntity extends AggregateRoot<Long> {

    @Schema(name = "name", description = "部门名称")
    private String name;

    @Schema(name = "pid", description = "部门父节点ID")
    private Long pid;

    @Schema(name = "path", description = "部门节点")
    private String path;

    @Schema(name = "sort", description = "部门排序")
    private Integer sort;

    public void checkName(long count) {
        if (count > 0) {
            throw new BusinessException("部门名称已存在，请重新填写");
        }
    }

    public void checkIdAndPid() {
        if (id.equals(pid)) {
            throw new BusinessException("上级部门不能为当前部门");
        }
    }
}
