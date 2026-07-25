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

package com.taotao.cloud.goods.infrastructure.repository.application;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.utils.lang.StringUtils;
import com.taotao.boot.data.mybatis.mybatisplus.MpUtils;
import com.taotao.cloud.goods.application.dto.goods.query.GoodsPageQuery;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsResult;
import com.taotao.cloud.goods.application.repository.GoodsQueryRepository;
import com.taotao.cloud.goods.common.enums.GoodsStatusEnum;
import com.taotao.cloud.goods.infrastructure.assembler.GoodsInfraAssembler;
import com.taotao.cloud.goods.infrastructure.data.dataobj.GoodsDO;
import com.taotao.cloud.goods.infrastructure.data.dataparam.GoodsParam;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsMapper;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.GoodsPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 商品查询仓储实现
 * <p>
 * 实现 GoodsQueryRepository 接口，提供商品的查询持久化操作
 * </p>
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@Service
@RequiredArgsConstructor
public class GoodsQueryRepositoryImpl implements GoodsQueryRepository {

	private final GoodsMapper goodsMapper;
	private final GoodsInfraAssembler goodsInfraAssembler;

	@Override
	public PageResult<GoodsResult> queryGoodsPage( GoodsPageQuery goodsPageQuery ) {
		IPage<GoodsPO> page = MpUtils.buildMpPage(goodsPageQuery.page());

		LambdaQueryWrapper<GoodsPO> queryWrapper = buildQueryWrapper(goodsPageQuery);

		IPage<GoodsPO> goodsPoPage = goodsMapper.selectPage(page, queryWrapper);

		return MpUtils.convertMpPage(goodsPoPage, goodsInfraAssembler::toResult);
	}

	@Override
	public PageResult<GoodsResult> queryMutilTalbePage( GoodsPageQuery goodsPageQuery ) {
		IPage<GoodsDO> page = MpUtils.buildMpPage(goodsPageQuery.page());

		GoodsParam param = goodsInfraAssembler.toParam(goodsPageQuery);

		IPage<GoodsDO> goodsSkuPage = goodsMapper.selectGoodsByGoodsParam(page, param);

		return MpUtils.convertMpPage(goodsSkuPage, goodsInfraAssembler::toResult);
	}

	@Override
	public List<GoodsResult> queryByBrandIds( List<Long> brandIds ) {
		LambdaQueryWrapper<GoodsPO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.in(GoodsPO::getBrandId, brandIds);
		List<GoodsPO> goodsPos = goodsMapper.selectList(lambdaQueryWrapper);
		return goodsInfraAssembler.toResult(goodsPos);
	}

	@Override
	public GoodsResult queryById( Long goodsId ) {
		GoodsPO goodsPo = goodsMapper.selectById(goodsId);
		return goodsInfraAssembler.toResult(goodsPo);
	}

	@Override
	public Long queryCountStoreGoodsNum( Long storeId ) {
		LambdaQueryWrapper<GoodsPO> queryWrapper = new LambdaQueryWrapper<GoodsPO>()
			.eq(GoodsPO::getStoreId, storeId)
			// .eq(Goods::getAuthFlag, GoodsAuthEnum.PASS.name())
			.eq(GoodsPO::getMarketEnable, GoodsStatusEnum.UPPER.name());

		return goodsMapper.selectCount(queryWrapper);
	}

	private LambdaQueryWrapper<GoodsPO> buildQueryWrapper( GoodsPageQuery query ) {
		return new LambdaQueryWrapper<GoodsPO>()
			.eq(Objects.nonNull(query.categoryId()), GoodsPO::getId, query.categoryId())
			.like(StringUtils.hasText(query.goodsName()), GoodsPO::getGoodsName, query.goodsName())
			.ge(Objects.nonNull(query.endGoodsPrice()), GoodsPO::getPrice, query.endGoodsPrice())
			.le(Objects.nonNull(query.endGoodsPrice()), GoodsPO::getPrice, query.endGoodsPrice())
			.orderByDesc(GoodsPO::getCreateTime);
	}
}
