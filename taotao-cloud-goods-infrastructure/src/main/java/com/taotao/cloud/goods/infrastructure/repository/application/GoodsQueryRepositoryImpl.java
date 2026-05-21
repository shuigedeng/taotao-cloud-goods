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
import com.taotao.boot.data.mybatis.mybatisplus.MpUtils;
import com.taotao.cloud.goods.application.acl.dto.sys.req.DictReq;
import com.taotao.cloud.goods.application.acl.dto.sys.res.DictRes;
import com.taotao.cloud.goods.application.acl.service.SysAclService;
import com.taotao.cloud.goods.application.dto.brand.result.BrandResult;
import com.taotao.cloud.goods.application.dto.goods.query.GoodsPageQuery;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsResult;
import com.taotao.cloud.goods.application.repository.BrandQueryRepository;
import com.taotao.cloud.goods.application.repository.GoodsQueryRepository;
import com.taotao.cloud.goods.infrastructure.assembler.BrandInfraAssembler;
import com.taotao.cloud.goods.infrastructure.assembler.GoodsInfraAssembler;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.BrandMapper;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsMapper;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.BrandPO;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.GoodsPO;
import com.taotao.cloud.goods.infrastructure.persistent.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * BrandQueryRepositoryImpl
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
		IPage<GoodsPO> goodsPoPage = goodsMapper.selectPage(MpUtils.buildMpPage(goodsPageQuery.page()), null);
		return MpUtils.convertMybatisPage(goodsPoPage, goodsInfraAssembler::toResult);
	}
}
