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

import com.taotao.cloud.goods.application.dto.commodity.cmmond.CommodityAddCmd;
import com.taotao.cloud.goods.application.service.CommodityCommandService;
import com.taotao.cloud.goods.application.service.GoodsSkuCommandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 直播商品业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:18
 */
@AllArgsConstructor
@Service
public class CommodityCommandServiceImpl
	implements CommodityCommandService {

	//private final WechatLivePlayerUtil wechatLivePlayerUtil;

	private final GoodsSkuCommandService goodsSkuService;

	@Override
	public boolean addCommodity(List<CommodityAddCmd> commodityAddCmdList) {
		return false;
	}

	@Override
	public boolean deleteCommodity(Long goodsId) {
		return false;
	}

	//@Override
	//@Transactional(rollbackFor = Exception.class)
	//public boolean addCommodity(List<CommodityPO> commodityPOList) {
	//	Long storeId = SecurityUtils.getCurrentUser().getStoreId();
	//	for (CommodityPO commodityPO : commodityPOList) {
	//		// 检测直播商品
	//		checkCommodity(commodityPO);
	//		commodityPO.setStoreId(storeId);
	//
	//		// 添加直播商品
	//		JSONObject json = wechatLivePlayerUtil.addGoods(commodityPO);
	//		if (!"0".equals(json.getStr("errcode"))) {
	//			log.error(json.getStr("errmsg"));
	//			throw new BusinessException(ResultEnum.COMMODITY_ERROR);
	//		}
	//
	//		commodityPO.setLiveGoodsId(Convert.toLong(json.getStr("goodsId")));
	//		commodityPO.setAuditId(json.getLong("auditId"));
	//		// 默认为待审核状态
	//		commodityPO.setAuditStatus("0");
	//		this.save(commodityPO);
	//	}
	//	return true;
	//}
	//
	//private void checkCommodity(CommodityPO commodityPO) {
	//	// 商品是否审核通过
	//	GoodsSkuPO goodsSkuPO = goodsSkuService.getById(commodityPO.getSkuId());
	//	if (!goodsSkuPO.getIsAuth().equals(GoodsAuthEnum.PASS.name())) {
	//		throw new BusinessException(goodsSkuPO.getGoodsName() + " 未审核通过，不能添加直播商品");
	//	}
	//
	//	// 是否已添加规格商品
	//	if (this.count(
	//		new LambdaQueryWrapper<CommodityPO>().eq(CommodityPO::getSkuId, commodityPO.getSkuId()))
	//		> 0) {
	//		throw new BusinessException(goodsSkuPO.getGoodsName() + " 已添加规格商品，无法重复增加");
	//	}
	//}
	//
	//@Override
	//public boolean deleteCommodity(Long goodsId) {
	//	SecurityUser currentUser = SecurityUtils.getCurrentUser();
	//	if (currentUser == null
	//		|| (currentUser.getType().equals(UserEnum.STORE.getCode())
	//		&& currentUser.getStoreId() == null)) {
	//		throw new BusinessException(ResultEnum.USER_AUTHORITY_ERROR);
	//	}
	//
	//	JSONObject json = wechatLivePlayerUtil.deleteGoods(goodsId);
	//	if ("0".equals(json.getStr("errcode"))) {
	//		return this.remove(new LambdaQueryWrapper<CommodityPO>()
	//			.eq(CommodityPO::getLiveGoodsId, goodsId)
	//			.eq(CommodityPO::getStoreId, SecurityUtils.getCurrentUser().getStoreId()));
	//	}
	//	return false;
	//}
	//
	//@Override
	//@Transactional(rollbackFor = Exception.class)
	//public boolean getGoodsWareHouse() {
	//	// 查询审核中的商品
	//	List<String> goodsIdList = this.baseMapper.getAuditCommodity();
	//	if (!goodsIdList.isEmpty()) {
	//		// 同步状态
	//		JSONObject json = wechatLivePlayerUtil.getGoodsWareHouse(goodsIdList);
	//		// 修改状态
	//		List<CommodityDTO> commodityDTOList = JSONUtil.toList((JSONArray) json.get("goods"),
	//			CommodityDTO.class);
	//		for (CommodityDTO commodityDTO : commodityDTOList) {
	//			// 修改审核状态
	//			this.update(new LambdaUpdateWrapper<CommodityPO>()
	//				.eq(CommodityPO::getLiveGoodsId, commodityDTO.getGoodsId())
	//				.set(CommodityPO::getAuditStatus, commodityDTO.getAuditStatus()));
	//		}
	//	}
	//	return true;
	//}
	//
	//@Override
	//public IPage<CommoditySkuCO> commodityList(PageQuery PageQuery, String name,
	//										   String auditStatus) {
	//	SecurityUser currentUser = SecurityUtils.getCurrentUser();
	//
	//	return this.baseMapper.commodityCOList(
	//		PageQuery.buildMpPage(),
	//		new QueryWrapper<CommoditySkuCO>()
	//			.like(name != null, "c.name", name)
	//			.eq(auditStatus != null, "c.audit_status", auditStatus)
	//			.eq(
	//				currentUser.getType().equals(UserEnum.STORE.getCode()),
	//				"c.store_id",
	//				currentUser.getStoreId())
	//			.orderByDesc("create_time"));
	//}
}
