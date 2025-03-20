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

package com.taotao.cloud.goods.infrastructure.repository;

import com.taotao.boot.data.datasource.tx.TransactionalUtil;
import com.taotao.boot.data.mybatis.utils.MybatisUtil;
import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.boot.ddd.util.validation.Validates;
import com.taotao.cloud.goods.domain.goodstag.aggregate.GoodsTagAgg;
import com.taotao.cloud.goods.domain.goodstag.repository.GoodsTagDomainRepository;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsMapper;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsSkuMapper;
import com.taotao.cloud.goods.infrastructure.persistent.repository.cls.GoodsTagRepository;
import java.util.Collection;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoodsTagDomainRepositoryImpl implements GoodsTagDomainRepository {

    private final TransactionalUtil transactionalUtil;
    private final MybatisUtil mybatisUtil;
    private final GoodsMapper goodsMapper;
    private final GoodsSkuMapper goodsSkuMapper;
    private final GoodsTagRepository goodsTagRepository;

    @Override
    public void create(GoodsTagAgg dept) {}

    @Override
    public void modify(GoodsTagAgg dept) {}

    @Override
    public void remove(Long[] ids) {}

    @Override
    public boolean isSatisfiedBy(Collection<BizId> goodsTagIds) {
        // 若标签ID集合为空，则直接验证通过
        if (Objects.isNull(goodsTagIds) || goodsTagIds.isEmpty()) {
            return true;
        }
        // 查询存在的标签数量
        Integer existGoodsTagCount = this.goodsTagRepository.countByIdIn(goodsTagIds);
        Validates.isTrue(existGoodsTagCount.equals(goodsTagIds.size()), "goods tags don't exist");
        return true;
    }
}
