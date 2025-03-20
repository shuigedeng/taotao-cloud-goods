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

package com.taotao.cloud.goods.domain.goodstag.repository;

import com.taotao.boot.ddd.model.domain.repository.DomainRepository;
import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.cloud.goods.domain.goodstag.aggregate.GoodsTagAgg;
import java.util.Collection;

public interface GoodsTagDomainRepository extends DomainRepository {
    /**
     * 新增部门.
     *
     * @param dept 部门对象
     */
    void create(GoodsTagAgg dept);

    /**
     * 修改部门.
     *
     * @param dept 部门对象
     */
    void modify(GoodsTagAgg dept);

    /**
     * 根据ID删除部门.
     *
     * @param ids IDS
     */
    void remove(Long[] ids);

    boolean isSatisfiedBy(Collection<BizId> goodsTagIds);
}
