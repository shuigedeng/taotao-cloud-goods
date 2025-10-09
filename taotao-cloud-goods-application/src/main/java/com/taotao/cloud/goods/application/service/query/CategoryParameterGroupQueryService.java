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

package com.taotao.cloud.goods.application.service.query;

import com.taotao.boot.ddd.model.application.service.QueryService;
import com.taotao.cloud.goods.application.dto.own.parameter.result.ParameterGroupResult;
import java.util.List;

/**
 * 分类绑定参数组业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:59:23
 */
public interface CategoryParameterGroupQueryService extends QueryService {

    /**
     * 查询分类绑定参数集合
     *
     * @param categoryId 分类Id
     * @return {@link List }<{@link ParameterGroupResult }>
     * @since 2022-04-27 16:59:23
     */
    List<ParameterGroupResult> getCategoryParams(Long categoryId);

    /**
     * 查询分类绑定参数组信息
     *
     * @param categoryId 分类id
     * @return {@link List }<{@link CategoryParameterGroupPO }>
     * @since 2022-04-27 16:59:23
     */
    //    List<CategoryParameterGroupPO> getCategoryGroup(Long categoryId);

}
