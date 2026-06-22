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

import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.ddd.model.application.service.QueryService;
import com.taotao.cloud.goods.application.dto.specification.query.SpecificationPageQuery;
import com.taotao.cloud.goods.application.dto.specification.result.SpecificationResult;
import java.util.List;

/**
 * 规格业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:01:01
 */
public interface SpecificationQueryService extends QueryService {

    /**
     * 获取所有可用规格
     *
     * @return 规格列表
     */
    List<SpecificationResult> listAll();

    /**
     * 分页查询规格
     *
     * @param query 查询条件
     * @return 规格分页结果
     */
    PageResult<SpecificationResult> queryPage(SpecificationPageQuery query);
}
