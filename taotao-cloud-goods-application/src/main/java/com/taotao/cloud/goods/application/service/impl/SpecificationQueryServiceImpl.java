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

package com.taotao.cloud.goods.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.boot.common.enums.ResultEnum;
import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.common.utils.lang.StringUtils;
import com.taotao.boot.webagg.service.impl.BaseSuperServiceImpl;
import com.taotao.cloud.goods.application.dto.specification.query.SpecificationPageQry;
import com.taotao.cloud.goods.application.service.CategoryCommandService;
import com.taotao.cloud.goods.application.service.CategorySpecificationCommandService;
import com.taotao.cloud.goods.application.service.SpecificationQueryService;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.SpecificationMapper;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.CategorySpecificationPO;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.SpecificationPO;
import com.taotao.cloud.goods.infrastructure.persistent.repository.cls.SpecificationRepository;
import com.taotao.cloud.goods.infrastructure.persistent.repository.inf.ISpecificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品规格业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:55
 */
@AllArgsConstructor
@Service
public class SpecificationQueryServiceImpl
	implements SpecificationQueryService {

	/**
	 * 分类-规格绑定服务
	 */
	private final CategorySpecificationCommandService categorySpecificationService;
	/**
	 * 分类服务
	 */
	private final CategoryCommandService categoryService;



	@Override
	public IPage<SpecificationPO> getPage(SpecificationPageQry specificationPageQry) {
		//LambdaQueryWrapper<SpecificationPO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		//lambdaQueryWrapper.like(
		//	StringUtils.isNotEmpty(specificationPageQry.getSpecName()),
		//	SpecificationPO::getSpecName,
		//	specificationPageQry.getSpecName());
		//return this.page(specificationPageQry.buildMpPage(), lambdaQueryWrapper);
		return null;
	}

}
