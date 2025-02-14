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
import com.taotao.boot.ddd.model.application.service.QueryService;
import com.taotao.boot.webagg.service.BaseSuperService;
import com.taotao.cloud.goods.application.dto.specification.query.SpecificationPageQry;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.SpecificationPO;

import java.util.List;

/**
 * 规格业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:01:01
 */
public interface SpecificationQueryService extends QueryService {


	/**
	 * 分页查询
	 *
	 * @param specificationPageQry 查询条件
	 * @return {@link IPage }<{@link SpecificationPO }>
	 * @since 2022-04-27 17:01:01
	 */
	IPage<SpecificationPO> getPage(SpecificationPageQry specificationPageQry);

}
