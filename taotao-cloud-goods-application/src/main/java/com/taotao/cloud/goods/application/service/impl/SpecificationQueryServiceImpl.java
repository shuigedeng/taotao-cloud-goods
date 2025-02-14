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

import com.taotao.cloud.goods.application.service.CategoryCommandService;
import com.taotao.cloud.goods.application.service.CategorySpecificationCommandService;
import com.taotao.cloud.goods.application.service.SpecificationQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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



//	@Override
//	public IPage<SpecificationPO> getPage(SpecificationPageQry specificationPageQry) {
//		//LambdaQueryWrapper<SpecificationPO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//		//lambdaQueryWrapper.like(
//		//	StringUtils.isNotEmpty(specificationPageQry.getSpecName()),
//		//	SpecificationPO::getSpecName,
//		//	specificationPageQry.getSpecName());
//		//return this.page(specificationPageQry.buildMpPage(), lambdaQueryWrapper);
//		return null;
//	}

}
