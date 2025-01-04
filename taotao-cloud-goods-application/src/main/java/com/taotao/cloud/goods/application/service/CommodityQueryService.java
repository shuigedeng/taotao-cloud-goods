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
import com.taotao.boot.webagg.service.BaseSuperService;
import com.taotao.cloud.goods.application.dto.commodity.clientobject.CommoditySkuCO;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.CommodityPO;
import java.util.List;

/**
 * 直播商品业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:59:47
 */
public interface CommodityQueryService extends BaseSuperService<CommodityPO, Long> {


	/**
	 * 查询微信小程序直播商品审核状态
	 *
	 * @return {@link boolean }
	 * @since 2022-04-27 16:59:47
	 */
	boolean getGoodsWareHouse();

	/**
	 * 查看直播商品分页
	 *
	 * @param PageQuery   分页
	 * @param name        商品名称
	 * @param auditStatus 审核状态
	 * @return {@link IPage }<{@link CommoditySkuCO }>
	 * @since 2022-04-27 16:59:47
	 */
	IPage<CommoditySkuCO> commodityList(PageQuery PageQuery,
		String name, String auditStatus);
}
