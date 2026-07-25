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
import com.taotao.cloud.goods.application.acl.service.SysAclService;
import com.taotao.cloud.goods.application.dto.brand.result.BrandResult;
import com.taotao.cloud.goods.application.acl.dto.sys.req.DictReq;
import com.taotao.cloud.goods.application.acl.dto.sys.res.DictRes;
import com.taotao.cloud.goods.application.repository.BrandQueryRepository;
import com.taotao.cloud.goods.infrastructure.assembler.BrandInfraAssembler;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.BrandMapper;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.BrandPO;
import com.taotao.cloud.goods.infrastructure.persistent.repository.BrandRepository;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 品牌查询仓储实现
 * <p>
 * 实现 BrandQueryRepository 接口，提供品牌的查询持久化操作
 * </p>
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@Service
@RequiredArgsConstructor
public class BrandQueryRepositoryImpl implements BrandQueryRepository {

    private final BrandMapper brandMapper;
    private final SysAclService sysAclService;
    private final BrandRepository brandRepository;
    private final BrandInfraAssembler brandInfraAssembler;

    @Override
    public BrandResult queryById( Long id ) {
//        BrandPO brandPO = brandMapper.selectById(id);

        DictRes dictRes = sysAclService.findByCode(DictReq.builder().code("123").build());

        Optional<BrandPO> brandPOOptional = brandRepository.findById(id);

        brandRepository.test();

        return brandInfraAssembler.toResult(brandMapper.selectById(id));
    }

	@Override
	public List<BrandResult> queryAllAvailable() {
		LambdaQueryWrapper<BrandPO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(BrandPO::getDelFlag, 0);
		List<BrandPO> brandPos = brandMapper.selectList(lambdaQueryWrapper);
		return brandInfraAssembler.toResult(brandPos);
	}
}
