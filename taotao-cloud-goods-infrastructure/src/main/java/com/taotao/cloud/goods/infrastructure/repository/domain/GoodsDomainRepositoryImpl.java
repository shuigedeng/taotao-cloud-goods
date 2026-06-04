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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taotao.boot.common.support.asserts.BusinessAssert;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.goods.domain.aggregate.GoodsAgg;
import com.taotao.cloud.goods.domain.repository.GoodsDomainRepository;
import com.taotao.cloud.goods.domain.valobj.GoodsStatus;
import com.taotao.cloud.goods.infrastructure.assembler.GoodsInfraAssembler;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsMapper;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsSkuMapper;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.GoodsPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * GoodsDomainRepositoryImpl
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@Service
@RequiredArgsConstructor
public class GoodsDomainRepositoryImpl implements GoodsDomainRepository {

	private final GoodsMapper goodsMapper;
	private final GoodsSkuMapper goodsSkuMapper;
	private final GoodsInfraAssembler goodsInfraAssembler;


	@Override
	public int save( GoodsAgg goods, boolean skipNull ) {
		BusinessAssert.notNull(goods, "商品数据不能为空");
		GoodsPO goodsPo = goodsInfraAssembler.toPo(goods);
		return save(goodsPo, goodsMapper, skipNull);
	}


	@Override
	public GoodsAgg findUsingIdCol( Long id, boolean withLock ) {
		BusinessAssert.notNull(id, "id不能为空");
		GoodsPO goodsPo = goodsMapper.selectByIdForUpdate(id, withLock);
		BusinessAssert.notNull(goodsPo, "商品数据不存在");
		return goodsInfraAssembler.toAgg(goodsPo);
	}

	@Override
	public GoodsAgg find( String identifier, boolean withLock ) {
		BusinessAssert.notBlank(identifier, "identifier不能为空");
		LambdaQueryWrapper<GoodsPO> queryWrapper = new LambdaQueryWrapper<GoodsPO>()
			.eq(GoodsPO::getGoodsNo, identifier);
		List<GoodsPO> goodsPos = goodsMapper.selectListForUpdate(queryWrapper, withLock);
		GoodsPO goodsPo = getOne(goodsPos);
		BusinessAssert.notNull(goodsPo, "商品数据不存在");
		return goodsInfraAssembler.toAgg(goodsPo);
	}

	@Override
	public Integer countByIdIn( Collection<BizId> ids ) {
		return 0;
	}

	@Override
	public Boolean existsByCategoryIdIn( Collection<BizId> categoryIds ) {
		return null;
	}

	@Override
	public Boolean existsByIdInAndGoodsStatus( Collection<BizId> goodsIds, GoodsStatus goodsStatus ) {
		return null;
	}

	@Override
	public Boolean existsShelvedGoodsByIdIn( Collection<BizId> goodsIds ) {
		return null;
	}

	@Override
	public void batchModifyGoodsStatus( Collection<BizId> goodsIds, GoodsStatus goodsStatus ) {
	}

	@Override
	public GoodsAgg findGoodsWithNameById( Long id ) {
		return null;
	}

	@Override
	public Boolean existsByTagIds( Collection<Long> tagIds ) {
		return null;
	}
}
