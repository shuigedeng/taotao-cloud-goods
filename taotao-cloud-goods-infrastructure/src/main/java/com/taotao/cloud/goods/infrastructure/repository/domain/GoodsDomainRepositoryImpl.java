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

package com.taotao.cloud.goods.infrastructure.repository.domain;

import com.taotao.boot.data.datasource.tx.TransactionalUtils;
import com.taotao.boot.data.datasource.tx.TxWrapper;
import com.taotao.boot.data.mybatis.utils.MybatisUtil;
import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.boot.flowengine.state.retry.TxWrapperOther;
import com.taotao.cloud.goods.domain.goods.aggregate.GoodsAgg;
import com.taotao.cloud.goods.domain.goods.repository.GoodsDomainRepository;
import com.taotao.cloud.goods.domain.goods.valobj.GoodsStatus;
import com.taotao.cloud.goods.infrastructure.assembler.GoodsInfraAssembler;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsMapper;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsSkuMapper;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.GoodsPO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoodsDomainRepositoryImpl implements GoodsDomainRepository {

    private final TransactionalUtils transactionalUtil;
    private final TxWrapper txWrapper;
    private final MybatisUtil mybatisUtil;
    private final GoodsMapper goodsMapper;
    private final GoodsSkuMapper goodsSkuMapper;

    @Override
    public void save(GoodsAgg goods) {
        GoodsPO goodsPo = GoodsInfraAssembler.INSTANCE.convert(goods);

        goodsMapper.insert(goodsPo);
    }

    @Override
    public Integer countByIdIn(Collection<BizId> ids) {
        return 0;
    }

    @Override
    public Boolean existsByCategoryIdIn(Collection<BizId> categoryIds) {
        return null;
    }

    @Override
    public Boolean existsByIdInAndGoodsStatus(Collection<BizId> goodsIds, GoodsStatus goodsStatus) {
        return null;
    }

    @Override
    public Boolean existsShelvedGoodsByIdIn(Collection<BizId> goodsIds) {
        return null;
    }

    @Override
    public void batchModifyGoodsStatus(Collection<BizId> goodsIds, GoodsStatus goodsStatus) {}

    @Override
    public GoodsAgg findGoodsWithCNameById(Long id) {
        return null;
    }

    @Override
    public Boolean existsByTagIds(Collection<Long> tagIds) {
        return null;
    }

    @Override
    public Page<GoodsAgg> findGoodsWithCNamePageJpa(
            String goodsName,
            Long categoryId,
            BigDecimal startGoodsPrice,
            BigDecimal endGoodsPrice,
            Integer goodsStatus,
            LocalDate beforeExpirationDate,
            LocalDateTime createTimeStart,
            LocalDateTime createTimeEnd,
            Pageable pageable) {
        return null;
    }
}
