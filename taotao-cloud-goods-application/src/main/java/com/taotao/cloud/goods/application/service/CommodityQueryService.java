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
import com.taotao.boot.common.model.PageQuery;
import com.taotao.boot.web.base.service.BaseSuperService;
import com.taotao.cloud.goods.application.command.commodity.dto.CommodityAddCmd;
import com.taotao.cloud.goods.application.command.commodity.dto.clientobject.CommoditySkuCO;
import com.taotao.cloud.goods.infrastructure.persistent.po.CommodityPO;
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
	 * 添加直播商品
	 *
	 * @param commodityPO 直播商品列表
	 * @return {@link boolean }
	 * @since 2022-04-27 16:59:47
	 */
	boolean addCommodity(List<CommodityAddCmd> commodityAddCmdList);

	/**
	 * 删除直播商品
	 *
	 * @param goodsId 直播商品ID
	 * @return {@link boolean }
	 * @since 2022-04-27 16:59:47
	 */
	boolean deleteCommodity(Long goodsId);

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
