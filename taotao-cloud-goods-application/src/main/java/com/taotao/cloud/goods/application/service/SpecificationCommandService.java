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

package com.taotao.cloud.goods.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.goods.application.command.specification.dto.SpecificationPageQry;
import com.taotao.cloud.goods.infrastructure.persistent.po.SpecificationPO;
import com.taotao.boot.web.base.service.BaseSuperService;
import java.util.List;

/**
 * 规格业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:01:01
 */
public interface SpecificationCommandService extends BaseSuperService<SpecificationPO, Long> {

	/**
	 * 删除规格
	 *
	 * @param ids 规格ID
	 * @return {@link boolean }
	 * @since 2022-04-27 17:01:01
	 */
	boolean deleteSpecification(List<Long> ids);

	/**
	 * 分页查询
	 *
	 * @param specificationPageQry 查询条件
	 * @return {@link IPage }<{@link SpecificationPO }>
	 * @since 2022-04-27 17:01:01
	 */
	IPage<SpecificationPO> getPage(SpecificationPageQry specificationPageQry);

	boolean saveCategoryBrand(Long categoryId, String[] categorySpecs);
}
