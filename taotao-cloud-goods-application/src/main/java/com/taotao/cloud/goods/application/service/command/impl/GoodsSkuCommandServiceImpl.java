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

package com.taotao.cloud.goods.application.service.command.impl;

import com.taotao.cloud.goods.application.dto.goods.cmmond.GoodsSkuStockUpdateCmd;
import com.taotao.cloud.goods.application.service.command.GoodsSkuCommandService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 商品sku业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:44
 */
@AllArgsConstructor
@Service
public class GoodsSkuCommandServiceImpl implements GoodsSkuCommandService {
    @Override
    public boolean clearCache(Long skuId) {
        return false;
    }

    @Override
    public boolean updateGoodsSkuStatusByStoreId(
            Long storeId, String marketEnable, String authFlag) {
        return false;
    }

    @Override
    public boolean updateStocks(List<GoodsSkuStockUpdateCmd> goodsSkuStockUpdateCmds) {
        return false;
    }

    @Override
    public boolean updateStock(Long skuId, Integer quantity) {
        return false;
    }

    @Override
    public boolean updateGoodsSkuCommentNum(Long skuId) {
        return false;
    }
    //	@Override
    //	public boolean add(GoodsPO goods, GoodsAddCmd goodsAddCmd) {
    //		return false;
    //	}
    //
    //	@Override
    //	public boolean update(GoodsPO goods, GoodsAddCmd goodsAddCmd) {
    //		return false;
    //	}
    //
    //	@Override
    //	public boolean update(GoodsSkuPO goodsSkuPO) {
    //		return false;
    //	}
    //
    //	@Override
    //	public boolean clearCache(Long skuId) {
    //		return false;
    //	}
    //
    //	@Override
    //	public boolean updateGoodsSkuStatus(GoodsPO goods) {
    //		return false;
    //	}
    //
    //	@Override
    //	public boolean updateGoodsSkuStatusByStoreId(Long storeId, String marketEnable, String
    // authFlag) {
    //		return false;
    //	}
    //
    //	@Override
    //	public boolean updateStocks(List<GoodsSkuStockUpdateCmd> goodsSkuStockUpdateCmds) {
    //		return false;
    //	}
    //
    //	@Override
    //	public boolean updateStock(Long skuId, Integer quantity) {
    //		return false;
    //	}
    //
    //	@Override
    //	public boolean updateGoodsStuck(List<GoodsSkuPO> goodsSkusPOS) {
    //		return false;
    //	}
    //
    //	@Override
    //	public boolean updateGoodsSkuCommentNum(Long skuId) {
    //		return false;
    //	}
    //
    //	@Override
    //	public boolean deleteAndInsertGoodsSkus(List<GoodsSkuPO> goodsSkusPOS) {
    //		return false;
    //	}
    //
    //	@Override
    //	public void renderGoodsSkuList(List<GoodsSkuPO> goodsSkuPOList, GoodsAddCmd goodsAddCmd) {
    //
    //	}

    // private final GoodsSkuManager goodsSkuManager;
    // private final GoodsManager goodsManager;
    //
    /// **
    // * 分类服务
    // */
    // private final CategoryCommandService categoryService;
    /// **
    // * 商品相册服务
    // */
    // private final GoodsGalleryCommandService goodsGalleryService;
    /// **
    // * 商品服务
    // */
    // private final GoodsCommandService goodsService;
    /// **
    // * 商品索引服务
    // */
    // private final EsGoodsCommandService goodsIndexService;
    //
    /// **
    // * 会员评价服务
    // */
    // private final IFeignMemberEvaluationApi memberEvaluationApi;
    /// **
    // * 促销活动商品服务
    // */
    // private final IFeignPromotionGoodsApi promotionGoodsApi;
    //
    /// **
    // * 缓存服务
    // */
    // private final RedisRepository redisRepository;
    /// **
    // * ApplicationEventPublisher
    // */
    // private final ApplicationEventPublisher applicationEventPublisher;
    //
    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public boolean add(List<Map<String, Object>> skuList, GoodsPO goods) {
    //	// 检查是否需要生成索引
    //	List<GoodsSkuPO> newSkuList;
    //	// 如果有规格
    //	if (skuList != null && !skuList.isEmpty()) {
    //		// 添加商品sku
    //		newSkuList = this.addGoodsSku(skuList, goods);
    //	} else {
    //		throw new BusinessException(ResultEnum.MUST_HAVE_GOODS_SKU);
    //	}
    //
    //	this.updateStock(newSkuList);
    //	if (!newSkuList.isEmpty()) {
    //		generateEs(goods);
    //	}
    //	return true;
    // }
    //
    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public boolean update(List<Map<String, Object>> skuList, GoodsPO goods,
    //					  boolean regeneratorSkuFlag) {
    //	// 是否存在规格
    //	if (skuList == null || skuList.isEmpty()) {
    //		throw new BusinessException(ResultEnum.MUST_HAVE_GOODS_SKU);
    //	}
    //	List<GoodsSkuPO> newSkuList;
    //	// 删除旧的sku信息
    //	if (boolean.TRUE.equals(regeneratorSkuFlag)) {
    //		List<GoodsSkuSpecGalleryCO> goodsListByGoodsId = getGoodsListByGoodsId(goods.getId());
    //		List<Long> oldSkuIds = new ArrayList<>();
    //		// 删除旧索引
    //		for (GoodsSkuSpecGalleryCO goodsSkuSpecGalleryCO : goodsListByGoodsId) {
    //			oldSkuIds.add(goodsSkuSpecGalleryCO.getId());
    //			redisRepository.del(getCacheKeys(goodsSkuSpecGalleryCO.getId()));
    //		}
    //		goodsIndexService.deleteIndexByIds(oldSkuIds);
    //		this.removeByIds(oldSkuIds);
    //		// 删除sku相册
    //		goodsGalleryService.removeByIds(oldSkuIds);
    //		// 添加商品sku
    //		newSkuList = this.addGoodsSku(skuList, goods);
    //
    //		// 发送mq消息
    //		String destination =
    //			rocketmqCustomProperties.getGoodsTopic() + ":" + GoodsTagsEnum.SKU_DELETE.name();
    //		rocketMQTemplate.asyncSend(
    //			destination, JSONUtil.toJsonStr(oldSkuIds),
    //			RocketmqSendCallbackBuilder.commonCallback());
    //	} else {
    //		newSkuList = new ArrayList<>();
    //		for (Map<String, Object> map : skuList) {
    //			GoodsSkuPO sku = new GoodsSkuPO();
    //			// 设置商品信息
    //			goodsInfo(sku, goods);
    //			// 设置商品规格信息
    //			skuInfo(sku, goods, map, null);
    //			newSkuList.add(sku);
    //			// 如果商品状态值不对，则es索引移除
    //			if (goods.getIsAuth().equals(GoodsAuthEnum.PASS.name())
    //				&& goods.getMarketEnable().equals(GoodsStatusEnum.UPPER.name())) {
    //				goodsIndexService.deleteIndexById(sku.getId());
    //				this.clearCache(sku.getId());
    //			}
    //		}
    //		this.updateBatchById(newSkuList);
    //	}
    //	this.updateStock(newSkuList);
    //	if (GoodsAuthEnum.PASS.name().equals(goods.getIsAuth()) && !newSkuList.isEmpty()) {
    //		generateEs(goods);
    //	}
    //
    //	return true;
    // }
    //
    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public boolean update(GoodsSkuPO goodsSkuPO) {
    //	this.updateById(goodsSkuPO);
    //	redisRepository.del(getCacheKeys(goodsSkuPO.getId()));
    //	redisRepository.set(getCacheKeys(goodsSkuPO.getId()), goodsSkuPO);
    //	return true;
    // }
    //
    // @Override
    // public boolean clearCache(Long skuId) {
    //	redisRepository.del(getCacheKeys(skuId));
    //	return true;
    // }
    //
    //
    //
    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public boolean updateGoodsSkuStatus(GoodsPO goods) {
    //	LambdaUpdateWrapper<GoodsSkuPO> updateWrapper = new LambdaUpdateWrapper<>();
    //	updateWrapper.eq(GoodsSkuPO::getGoodsId, goods.getId());
    //	updateWrapper.set(GoodsSkuPO::getMarketEnable, goods.getMarketEnable());
    //	updateWrapper.set(GoodsSkuPO::getIsAuth, goods.getIsAuth());
    //	updateWrapper.set(GoodsSkuPO::getDelFlag, goods.getDelFlag());
    //	boolean update = this.update(updateWrapper);
    //	if (boolean.TRUE.equals(update)) {
    //		List<GoodsSkuPO> goodsSkusPOS = this.getGoodsSkuListByGoodsId(goods.getId());
    //		for (GoodsSkuPO sku : goodsSkusPOS) {
    //			redisRepository.del(getCacheKeys(sku.getId()));
    //			redisRepository.set(getCacheKeys(sku.getId()), sku);
    //		}
    //		if (!goodsSkusPOS.isEmpty()) {
    //			generateEs(goods);
    //		}
    //	}
    //	return true;
    // }
    //
    //
    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public boolean updateStocks(List<GoodsSkuStockUpdateCmd> goodsSkuStockUpdateCmds) {
    //	for (GoodsSkuStockUpdateCmd goodsSkuStockUpdateCmd : goodsSkuStockUpdateCmds) {
    //		this.updateStock(goodsSkuStockUpdateCmd.getSkuId(), goodsSkuStockUpdateCmd.getQuantity());
    //	}
    //	return true;
    // }
    //
    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public boolean updateStock(Long skuId, Integer quantity) {
    //	GoodsSkuPO goodsSkuPO = getGoodsSkuByIdFromCache(skuId);
    //	if (goodsSkuPO != null) {
    //		if (quantity <= 0) {
    //			goodsIndexService.deleteIndexById(goodsSkuPO.getId());
    //		}
    //		goodsSkuPO.setQuantity(quantity);
    //		boolean update = this.update(new LambdaUpdateWrapper<GoodsSkuPO>()
    //			.eq(GoodsSkuPO::getId, skuId)
    //			.set(GoodsSkuPO::getQuantity, quantity));
    //		if (update) {
    //			redisRepository.del(CachePrefix.GOODS.getPrefix() + goodsSkuPO.getGoodsId());
    //		}
    //		redisRepository.set(getCacheKeys(skuId), goodsSkuPO);
    //		redisRepository.set(getStockCacheKey(skuId), quantity);
    //
    //		// 更新商品库存
    //		List<GoodsSkuPO> goodsSkusPOS = new ArrayList<>();
    //		goodsSkusPOS.add(goodsSkuPO);
    //		this.updateGoodsStuck(goodsSkusPOS);
    //	}
    //	return true;
    // }
    //
    //
    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public boolean updateGoodsStuck(List<GoodsSkuPO> goodsSkusPOS) {
    //	// 商品id集合 hashset 去重复
    //	Set<Long> goodsIds = new HashSet<>();
    //	for (GoodsSkuPO sku : goodsSkusPOS) {
    //		goodsIds.add(sku.getGoodsId());
    //	}
    //	// 获取相关的sku集合
    //	LambdaQueryWrapper<GoodsSkuPO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    //	lambdaQueryWrapper.in(GoodsSkuPO::getGoodsId, goodsIds);
    //	List<GoodsSkuPO> goodsSkuPOList = this.list(lambdaQueryWrapper);
    //
    //	// 统计每个商品的库存
    //	for (Long goodsId : goodsIds) {
    //		// 库存
    //		Integer quantity = 0;
    //		for (GoodsSkuPO goodsSkuPO : goodsSkuPOList) {
    //			if (goodsId.equals(goodsSkuPO.getGoodsId())) {
    //				quantity += goodsSkuPO.getQuantity();
    //			}
    //		}
    //		// 保存商品库存结果
    //		goodsService.updateStock(goodsId, quantity);
    //	}
    //	return true;
    // }
    //
    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public boolean updateGoodsSkuCommentNum(Long skuId) {
    //	// 获取商品信息
    //	GoodsSkuPO goodsSkuPO = this.getGoodsSkuByIdFromCache(skuId);
    //
    //	EvaluationPageQuery queryParams = new EvaluationPageQuery();
    //	queryParams.setGrade(EvaluationGradeEnum.GOOD.name());
    //	queryParams.setSkuId(goodsSkuPO.getId());
    //	// 好评数量
    //	long highPraiseNum = memberEvaluationApi.getEvaluationCount(queryParams);
    //
    //	// 更新商品评价数量
    //	goodsSkuPO.setCommentNum(
    //		goodsSkuPO.getCommentNum() != null ? goodsSkuPO.getCommentNum() + 1 : 1);
    //
    //	// todo 此处需要修改 好评率
    //	BigDecimal grade = BigDecimal.valueOf(NumberUtil.mul(
    //		NumberUtil.div(highPraiseNum, goodsSkuPO.getCommentNum().doubleValue(), 2), 100));
    //	goodsSkuPO.setGrade(grade);
    //	// 修改规格
    //	this.update(goodsSkuPO);
    //
    //	// 修改规格索引,发送mq消息
    //	Map<String, Object> updateIndexFieldsMap = EsIndexUtil.getUpdateIndexFieldsMap(
    //		MapUtil.builder(new HashMap<String, Object>())
    //			.put("id", goodsSkuPO.getId())
    //			.build(),
    //		MapUtil.builder(new HashMap<String, Object>())
    //			.put("commentNum", goodsSkuPO.getCommentNum())
    //			.put("highPraiseNum", highPraiseNum)
    //			.put("grade", grade)
    //			.build());
    //	goodsSkuManager.sendUpdateIndexFieldsMap(updateIndexFieldsMap);
    //
    //	// 修改商品的评价数量
    //	goodsService.updateGoodsCommentNum(goodsSkuPO.getGoodsId());
    //	return true;
    // }
    //
    // @Override
    // public void generateEs(GoodsPO goods) {
    //	// 不生成没有审核通过且没有上架的商品
    //	if (!GoodsStatusEnum.UPPER.name().equals(goods.getMarketEnable())
    //		|| !GoodsAuthEnum.PASS.name().equals(goods.getIsAuth())) {
    //		return;
    //	}
    //	applicationEventPublisher.publishEvent(
    //		new GeneratorEsGoodsIndexEvent("生成商品索引事件", goods.getId()));
    // }
    //
    /// **
    // * 修改库存
    // *
    // * @param goodsSkusPOS 商品SKU
    // */
    // private void updateStock(List<GoodsSkuPO> goodsSkusPOS) {
    //	// 总库存数量
    //	Integer quantity = 0;
    //	for (GoodsSkuPO sku : goodsSkusPOS) {
    //		this.updateStock(sku.getId(), sku.getQuantity());
    //		quantity += sku.getQuantity();
    //	}
    //
    //	// 修改商品库存
    //	goodsService.updateStock(goodsSkusPOS.get(0).getGoodsId(), quantity);
    // }
    //
    /// **
    // * 增加sku集合
    // *
    // * @param skuList sku列表
    // * @param goods   商品信息
    // */
    // List<GoodsSkuPO> addGoodsSku(List<Map<String, Object>> skuList, GoodsPO goods) {
    //	List<GoodsSkuPO> skus = new ArrayList<>();
    //	for (Map<String, Object> skuCO : skuList) {
    //		Map<String, Object> resultMap = this.add(skuCO, goods);
    //		GoodsSkuPO goodsSkuPO = (GoodsSkuPO) resultMap.get("goodsSku");
    //		if (goods.getSelfOperated() != null) {
    //			goodsSkuPO.setSelfOperated(goods.getSelfOperated());
    //		}
    //		goodsSkuPO.setGoodsType(goods.getGoodsType());
    //		skus.add(goodsSkuPO);
    //		redisRepository.set(getStockCacheKey(goodsSkuPO.getId()), goodsSkuPO.getQuantity());
    //	}
    //	this.saveBatch(skus);
    //	return skus;
    // }
    //
    /// **
    // * 添加商品规格
    // *
    // * @param map   规格属性
    // * @param goods 商品
    // * @return 规格商品
    // */
    // private Map<String, Object> add(Map<String, Object> map, GoodsPO goods) {
    //	Map<String, Object> resultMap = new HashMap<>(2);
    //	GoodsSkuPO sku = new GoodsSkuPO();
    //
    //	// 商品索引
    //	EsGoodsIndex esGoodsIndex = new EsGoodsIndex();
    //
    //	// 设置商品信息
    //	goodsInfo(sku, goods);
    //	// 设置商品规格信息
    //	skuInfo(sku, goods, map, esGoodsIndex);
    //
    //	// esGoodsIndex.setGoodsSku(sku);
    //	resultMap.put("goodsSku", sku);
    //	resultMap.put("goodsIndex", esGoodsIndex);
    //	return resultMap;
    // }
    //
    /// **
    // * 设置规格商品的商品信息
    // *
    // * @param sku   规格
    // * @param goods 商品
    // */
    // private void goodsInfo(GoodsSkuPO sku, GoodsPO goods) {
    //	// 商品基本信息
    //	sku.setGoodsId(goods.getId());
    //
    //	sku.setSellingPoint(goods.getSellingPoint());
    //	sku.setCategoryPath(goods.getCategoryPath());
    //	sku.setBrandId(goods.getBrandId());
    //	sku.setMarketEnable(goods.getMarketEnable());
    //	sku.setIntro(goods.getIntro());
    //	sku.setMobileIntro(goods.getMobileIntro());
    //	sku.setGoodsUnit(goods.getGoodsUnit());
    //	sku.setGrade(BigDecimal.valueOf(100));
    //	// 商品状态
    //	sku.setIsAuth(goods.getIsAuth());
    //	sku.setSalesModel(goods.getSalesModel());
    //	// 卖家信息
    //	sku.setStoreId(goods.getStoreId());
    //	sku.setStoreName(goods.getStoreName());
    //	sku.setStoreCategoryPath(goods.getStoreCategoryPath());
    //	sku.setFreightTemplateId(goods.getTemplateId());
    //	sku.setRecommend(goods.getRecommend());
    // }
    //
    /// **
    // * 设置商品规格信息
    // *
    // * @param sku          规格商品
    // * @param goods        商品
    // * @param map          规格信息
    // * @param esGoodsIndex 商品索引
    // */
    // private void skuInfo(GoodsSkuPO sku, GoodsPO goods, Map<String, Object> map,
    //					 EsGoodsIndex esGoodsIndex) {
    //	// 规格简短信息
    //	StringBuilder simpleSpecs = new StringBuilder();
    //	// 商品名称
    //	StringBuilder goodsName = new StringBuilder(goods.getGoodsName());
    //	// 规格商品缩略图
    //	String thumbnail = "";
    //	String small = "";
    //	// 规格值
    //	Map<String, Object> specMap = new HashMap<>(16);
    //	// 商品属性
    //	List<EsGoodsAttribute> attributes = new ArrayList<>();
    //
    //	// 获取规格信息
    //	for (Entry<String, Object> spec : map.entrySet()) {
    //		// 保存规格信息
    //		if (("id").equals(spec.getKey())
    //			|| ("sn").equals(spec.getKey())
    //			|| ("cost").equals(spec.getKey())
    //			|| ("price").equals(spec.getKey())
    //			|| ("quantity").equals(spec.getKey())
    //			|| ("weight").equals(spec.getKey())) {
    //		} else {
    //			specMap.put(spec.getKey(), spec.getValue());
    //			if (("images").equals(spec.getKey())) {
    //				// 设置规格商品缩略图
    //				List<Map<String, String>> images = (List<Map<String, String>>) spec.getValue();
    //				if (images == null || images.isEmpty()) {
    //					continue;
    //				}
    //				// 设置规格商品缩略图
    //				// 如果规格没有图片，则用商品图片复盖。有则增加规格图片，放在商品图片集合之前
    //				if (CharSequenceUtil.isNotEmpty(spec.getValue().toString())) {
    //					thumbnail = goodsGalleryService
    //						.getGoodsGallery(images.get(0).get("url"))
    //						.getThumbnail();
    //					small = goodsGalleryService
    //						.getGoodsGallery(images.get(0).get("url"))
    //						.getSmall();
    //				}
    //			} else {
    //				if (spec.getValue() != null) {
    //					// 设置商品名称
    //					goodsName.append(" ").append(spec.getValue());
    //					// 规格简短信息
    //					simpleSpecs.append(" ").append(spec.getValue());
    //				}
    //			}
    //		}
    //	}
    //	// 设置规格信息
    //	sku.setGoodsName(goodsName.toString());
    //	sku.setThumbnail(thumbnail);
    //	sku.setSmall(small);
    //
    //	// 规格信息
    //	sku.setId(Convert.toLong(map.get("id"), null));
    //	sku.setSn(Convert.toStr(map.get("sn")));
    //	sku.setWeight(Convert.toBigDecimal(map.get("weight"), BigDecimal.ZERO));
    //	sku.setPrice(Convert.toBigDecimal(map.get("price"), BigDecimal.ZERO));
    //	sku.setCost(Convert.toBigDecimal(map.get("cost"), BigDecimal.ZERO));
    //	sku.setQuantity(Convert.toInt(map.get("quantity"), 0));
    //	sku.setSpecs(JSONUtil.toJsonStr(specMap));
    //	sku.setSimpleSpecs(simpleSpecs.toString());
    //
    //	if (esGoodsIndex != null) {
    //		// 商品索引
    //		esGoodsIndex.setAttrList(attributes);
    //	}
    // }
    //
    /// **
    // * 添加商品默认图片
    // *
    // * @param origin 图片
    // * @param goods  商品
    // */
    // private void setGoodsGalleryParam(String origin, GoodsPO goods) {
    //	GoodsGallery goodsGallery = goodsGalleryService.getGoodsGallery(origin);
    //	goods.setOriginal(goodsGallery.getOriginal());
    //	goods.setSmall(goodsGallery.getSmall());
    //	goods.setThumbnail(goodsGallery.getThumbnail());
    // }
    //
    /// **
    // * 检查商品信息 如果商品是虚拟商品则无需配置配送模板 如果商品是实物商品需要配置配送模板 判断商品是否存在 判断商品是否需要审核 判断当前用户是否为店铺
    // *
    // * @param goods 商品
    // */
    // public void checkGoods(GoodsPO goods) {
    //	// 判断商品类型
    //	switch (goods.getGoodsType()) {
    //		case "PHYSICAL_GOODS" -> {
    //			if (Long.valueOf(0).equals(goods.getTemplateId())) {
    //				throw new BusinessException(ResultEnum.PHYSICAL_GOODS_NEED_TEMP);
    //			}
    //		}
    //		case "VIRTUAL_GOODS" -> {
    //			if (!Long.valueOf(0).equals(goods.getTemplateId())) {
    //				throw new BusinessException(ResultEnum.VIRTUAL_GOODS_NOT_NEED_TEMP);
    //			}
    //		}
    //		default -> throw new BusinessException(ResultEnum.GOODS_TYPE_ERROR);
    //	}
    //
    //	// 检查商品是否存在--修改商品时使用
    //	if (goods.getId() != null) {
    //		this.checkExist(goods.getId());
    //	} else {
    //		// 评论次数
    //		goods.setCommentNum(0);
    //		// 购买次数
    //		goods.setBuyCount(0);
    //		// 购买次数
    //		goods.setQuantity(0);
    //		// 商品评分
    //		goods.setGrade(BigDecimal.valueOf(100));
    //	}
    //
    //	// 获取商品系统配置决定是否审核
    //	GoodsSettingCO goodsSetting = settingApi.getGoodsSetting(
    //		SettingCategoryEnum.GOODS_SETTING.name());
    //	// 是否需要审核
    //	goods.setAuthFlag(
    //		boolean.TRUE.equals(goodsSetting.getGoodsCheck())
    //			? GoodsAuthEnum.TOBEAUDITED.name()
    //			: GoodsAuthEnum.PASS.name());
    //	// 判断当前用户是否为店铺
    //	if (SecurityUtils.getCurrentUser().getType().equals(UserEnum.STORE.getCode())) {
    //		StoreCO storeDetail = storeApi.getStoreDetail();
    //		if (storeDetail.getSelfOperated() != null) {
    //			goods.setSelfOperated(storeDetail.getSelfOperated());
    //		}
    //		goods.setStoreId(storeDetail.getId());
    //		goods.setStoreName(storeDetail.getStoreName());
    //		goods.setSelfOperated(storeDetail.getSelfOperated());
    //	} else {
    //		throw new BusinessException(ResultEnum.STORE_NOT_LOGIN_ERROR);
    //	}
    // }
}
