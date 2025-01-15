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

package com.taotao.cloud.goods.infrastructure.persistent.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.boot.common.utils.lang.StringUtils;
import com.taotao.boot.webagg.mapper.BaseSuperMapper;
import com.taotao.cloud.goods.infrastructure.dataparam.BrandPageParam;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.BrandPO;

/**
 * 商品品牌数据处理层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-20 16:59:38
 */
public interface BrandMapper extends BaseSuperMapper<BrandPO, Long> {

	default IPage<BrandPO> findBrandPage(BrandPageParam brandPageParam) {
		LambdaQueryWrapper<BrandPO> queryWrapper = new LambdaQueryWrapper<>();

		queryWrapper.like(StringUtils.isNotBlank(brandPageParam.getName()), BrandPO::getName,
			brandPageParam.getName());

		return this.selectPage(queryWrapper, brandPageParam);
	}
}
