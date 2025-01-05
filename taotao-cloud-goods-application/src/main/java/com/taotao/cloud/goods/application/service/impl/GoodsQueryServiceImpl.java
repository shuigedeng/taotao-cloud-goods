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
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.p6spy.engine.logging.Category;
import com.taotao.boot.cache.redis.repository.RedisRepository;
import com.taotao.boot.common.enums.ResultEnum;
import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.webagg.service.impl.BaseSuperServiceImpl;
import com.taotao.cloud.goods.application.dto.goods.clientobject.GoodsSkuParamsCO;
import com.taotao.cloud.goods.application.dto.goods.clientobject.GoodsSkuSpecGalleryCO;
import com.taotao.cloud.goods.application.dto.goods.cmmond.GoodsAddCmd;
import com.taotao.cloud.goods.application.dto.goods.query.GoodsPageQry;
import com.taotao.cloud.goods.application.manager.GoodsManager;
import com.taotao.cloud.goods.application.service.CategoryCommandService;
import com.taotao.cloud.goods.application.service.GoodsQueryService;
import com.taotao.cloud.goods.application.service.GoodsSkuCommandService;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsMapper;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.GoodsPO;
import com.taotao.cloud.goods.infrastructure.persistent.repository.cls.GoodsRepository;
import com.taotao.cloud.goods.infrastructure.persistent.repository.inf.IGoodsRepository;
import com.taotao.cloud.stream.framework.rocketmq.RocketmqSendCallbackBuilder;
import com.taotao.cloud.stream.framework.rocketmq.tags.GoodsTagsEnum;
import com.taotao.cloud.stream.properties.RocketmqCustomProperties;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.dromara.hutool.core.math.NumberUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.json.JSONUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 商品业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:41
 */
@Service
@AllArgsConstructor
public class GoodsQueryServiceImpl extends BaseSuperServiceImpl<GoodsPO, Long, GoodsMapper, GoodsRepository, IGoodsRepository>
	implements GoodsQueryService {
	@Override
	public List<GoodsPO> getByBrandIds(List<Long> brandIds) {
		return List.of();
	}

	@Override
	public GoodsSkuParamsCO getGoodsVO(Long goodsId) {
		return null;
	}

	@Override
	public IPage<GoodsPO> goodsQueryPage(GoodsPageQry goodsPageQry) {
		return null;
	}

	@Override
	public List<GoodsPO> queryListByParams(GoodsPageQry goodsPageQry) {
		return List.of();
	}

	@Override
	public Long countStoreGoodsNum(Long storeId) {
		return 0L;
	}
	//private final GoodsManager goodsManager;
	//
	///**
	// * 分类
	// */
	//private final CategoryCommandService categoryService;
	///**
	// * 商品相册
	// */
	//private final GoodsGalleryService goodsGalleryService;
	///**
	// * 商品规格
	// */
	//private final GoodsSkuCommandService goodsSkuService;
	//
	///**
	// * 设置
	// */
	//private final IFeignSettingApi settingApi;
	///**
	// * 店铺详情
	// */
	//private final IFeignStoreApi storeApi;
	///**
	// * 运费模板
	// */
	//private final IFeignFreightTemplateApi freightTemplateApi;
	///**
	// * 会员评价
	// */
	//private final IFeignMemberEvaluationApi memberEvaluationApi;
	///**
	// * rocketMq
	// */
	//private final RocketMQTemplate rocketMQTemplate;
	///**
	// * rocketMq配置
	// */
	//private final RocketmqCustomProperties rocketmqCustomProperties;
	//
	//private final RedisRepository redisRepository;
	//
	//@Override
	//public List<GoodsPO> getByBrandIds(List<Long> brandIds) {
	//	LambdaQueryWrapper<GoodsPO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
	//	lambdaQueryWrapper.in(GoodsPO::getBrandId, brandIds);
	//	return list(lambdaQueryWrapper);
	//}
	//
	//@Override
	//public GoodsSkuParamsCO getGoodsVO(Long goodsId) {
	//	return null;
	//}
	//
	//@Override
	//public IPage<GoodsPO> goodsQueryPage(GoodsPageQry goodsPageQry) {
	//	return null;
	//}
	//
	//@Override
	//public List<GoodsPO> queryListByParams(GoodsPageQry goodsPageQry) {
	//	return List.of();
	//}
	//
	//
	//@Override
	//public final Long getGoodsCountByCategory(Long categoryId) {
	//	QueryWrapper<GoodsPO> queryWrapper = Wrappers.query();
	//	queryWrapper.like("category_path", categoryId);
	//	queryWrapper.eq("delete_flag", false);
	//	return this.count(queryWrapper);
	//}
	//
	//
	//
	//@Override
	//public GoodsSkuParamsCO getGoodsCO(Long goodsId) {
	//	// 缓存获取，如果没有则读取缓存
	//	GoodsSkuParamsCO goodsSkuParamsCO =
	//		(GoodsSkuParamsCO) redisRepository.get(CachePrefix.GOODS.getPrefix() + goodsId);
	//	if (goodsSkuParamsCO != null) {
	//		return goodsSkuParamsCO;
	//	}
	//
	//	// 查询商品信息
	//	GoodsPO goods = this.getById(goodsId);
	//	if (goods == null) {
	//		LogUtils.error("商品ID为" + goodsId + "的商品不存在");
	//		throw new BusinessException(ResultEnum.GOODS_NOT_EXIST);
	//	}
	//
	//	// 赋值
	//	goodsSkuParamsCO = GoodsConvert.INSTANCE.convert(goods);
	//	// 商品id
	//	goodsSkuParamsCO.setId(goods.getId());
	//	// 商品相册
	//	List<GoodsGallery> galleryList = goodsGalleryService.goodsGalleryList(goodsId);
	//	goodsSkuParamsCO.setGoodsGalleryList(galleryList.stream()
	//		.filter(Objects::nonNull)
	//		.map(GoodsGallery::getOriginal)
	//		.toList());
	//
	//	// 商品sku赋值
	//	List<GoodsSkuSpecGalleryCO> goodsListByGoodsId = goodsSkuService.getGoodsListByGoodsId(goodsId);
	//	if (goodsListByGoodsId != null && !goodsListByGoodsId.isEmpty()) {
	//		goodsSkuParamsCO.setSkuList(goodsListByGoodsId);
	//	}
	//
	//	// 商品分类名称赋值
	//	String categoryPath = goods.getCategoryPath();
	//	String[] strArray = categoryPath.split(",");
	//	List<Category> categories = categoryService.listByIds(Arrays.asList(strArray));
	//	goodsSkuParamsCO.setCategoryName(categories.stream()
	//		.filter(Objects::nonNull)
	//		.map(Category::getName)
	//		.toList());
	//
	//	// 参数非空则填写参数
	//	if (StrUtil.isNotEmpty(goods.getParams())) {
	//		goodsSkuParamsCO.setGoodsParamsDTOList(JSONUtil.toList(goods.getParams(), GoodsParamsDTO.class));
	//	}
	//
	//	redisRepository.set(CachePrefix.GOODS.getPrefix() + goodsId, goodsSkuParamsCO);
	//	return goodsSkuParamsCO;
	//}
	//
	//@Override
	//public IPage<GoodsPO> goodsQueryPage(GoodsPageQuery goodsPageQuery) {
	//	return this.page(goodsPageQuery.buildMpPage(), goodsManager.goodsQueryWrapper(goodsPageQuery));
	//}
	//
	//@Override
	//public List<GoodsPO> queryListByParams(GoodsPageQuery goodsPageQuery) {
	//	return this.list(goodsManager.goodsQueryWrapper(goodsPageQuery));
	//}
	//
	//// @Override
	//// @Transactional(rollbackFor = Exception.class)
	//// public boolean updateStoreDetail(Store store) {
	////	UpdateWrapper updateWrapper = new UpdateWrapper<>()
	////		.eq("store_id", store.getId())
	////		.set("store_name", store.getStoreName())
	////		.set("self_operated", store.getSelfOperated());
	////	this.update(updateWrapper);
	////	goodsSkuService.update(updateWrapper);
	////	return true;
	//// }
	//
	//@Override
	//public Long countStoreGoodsNum(Long storeId) {
	//	return this.count(new LambdaQueryWrapper<GoodsPO>()
	//		.eq(GoodsPO::getStoreId, storeId)
	//		// .eq(Goods::getAuthFlag, GoodsAuthEnum.PASS.name())
	//		.eq(GoodsPO::getMarketEnable, GoodsStatusEnum.UPPER.name()));
	//}


}
