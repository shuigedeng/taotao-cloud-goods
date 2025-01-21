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
import com.taotao.boot.common.utils.bean.BeanUtils;
import com.taotao.boot.common.utils.lang.StringUtils;
import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.data.mybatis.mybatisplus.MpUtils;
import com.taotao.boot.webagg.service.impl.BaseSuperServiceImpl;
import com.taotao.cloud.goods.application.dto.brand.query.BrandPageQry;
import com.taotao.cloud.goods.application.service.BrandQueryService;
import com.taotao.cloud.goods.application.service.CategoryBrandCommandService;
import com.taotao.cloud.goods.application.service.CategoryCommandService;
import com.taotao.cloud.goods.application.service.GoodsCommandService;
import com.taotao.cloud.goods.infrastructure.dataparam.BrandPageParam;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.BrandMapper;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.BrandPO;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.CategoryBrandPO;
import com.taotao.cloud.goods.infrastructure.persistent.repository.cls.BrandRepository;
import com.taotao.cloud.goods.infrastructure.persistent.repository.inf.IBrandRepository;
import lombok.AllArgsConstructor;
import org.dromara.hutool.json.JSONUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商品品牌业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:01:19
 */
@Service
@AllArgsConstructor
public class BrandQueryServiceImpl extends
	BaseSuperServiceImpl<BrandPO, Long, BrandMapper, BrandRepository, IBrandRepository>
	implements BrandQueryService {

	/**
	 * 分类品牌绑定服务
	 */
	private final CategoryBrandCommandService categoryBrandService;
	/**
	 * 分类服务
	 */
	private final CategoryCommandService categoryService;
	/**
	 * 商品服务
	 */
	private final GoodsCommandService goodsService;

	private final BrandMapper brandMapper;

	@Override
	public IPage<BrandPO> brandsQueryPage(BrandPageQry page) {
		return brandMapper.findBrandPage(BrandPageParam.builder().name(page.name()).pageQuery(page.pageQuery()).build());
	}

	@Override
	public List<BrandPO> getBrandsByCategory(Long categoryId) {
		QueryWrapper<CategoryBrandPO> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("category_id", categoryId);
		List<CategoryBrandPO> list = categoryBrandService.list(queryWrapper);
		if (list != null && !list.isEmpty()) {
			List<Long> collect = list.stream().map(CategoryBrandPO::brandId).toList();
			return this.list(new LambdaQueryWrapper<BrandPO>().in(BrandPO::getId, collect));
		}
		return new ArrayList<>();
	}

	@Override
	public List<Map<String, Object>> getBrandsMapsByCategory(List<Long> categoryIds,
															 String columns) {
		return null;
	}

	@Override
	public List<BrandPO> getBrandsByCategorys(Long categoryIds) {
		// Map<String,  List<Brand>> map = this.baseMapper.selectBrandsByCategorysAsMap(categoryIds)

		QueryWrapper<CategoryBrandPO> queryWrapper = new QueryWrapper<>();
		queryWrapper.in("category_id", categoryIds);
		List<CategoryBrandPO> list = categoryBrandService.list(queryWrapper);
		if (list != null && !list.isEmpty()) {
			List<Long> collect = list.stream().map(CategoryBrandPO::brandId).toList();
			return this.list(new LambdaQueryWrapper<BrandPO>().in(BrandPO::getId, collect));
		}
		return new ArrayList<>();
	}





	@Override
	public List<BrandPO> getAllAvailable() {
		LambdaQueryWrapper<BrandPO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(BrandPO::getDelFlag, 0);
		return this.list(lambdaQueryWrapper);
	}

	@Override
	public IPage<BrandPO> getBrandsByPage(BrandPageQry page) {
		return null;
	}



	/**
	 * 校验绑定关系
	 *
	 * @param brandIds 品牌Ids
	 */
	//private void checkBind(List<Long> brandIds) {
	//	// 分了绑定关系查询
	//	List<CategoryBrand> categoryBrands = categoryBrandService.getCategoryBrandListByBrandId(
	//		brandIds);
	//
	//	if (!categoryBrands.isEmpty()) {
	//		List<Long> categoryIds =
	//			categoryBrands.stream().map(CategoryBrand::getCategoryId).toList();
	//		throw new BusinessException(
	//			ResultEnum.BRAND_USE_DISABLE_ERROR.getCode(),
	//			JSONUtil.toJsonStr(categoryService.getCategoryNameByIds(categoryIds)));
	//	}
	//
	//	// 分了商品绑定关系查询
	//	List<Goods> goods = goodsService.getByBrandIds(brandIds);
	//	if (!goods.isEmpty()) {
	//		List<String> goodsNames = goods.stream().map(Goods::getGoodsName).toList();
	//		throw new BusinessException(ResultEnum.BRAND_BIND_GOODS_ERROR.getCode(),
	//			JSONUtil.toJsonStr(goodsNames));
	//	}
	//}

	/**
	 * 校验是否存在
	 *
	 * @param brandId 品牌ID
	 * @return 品牌
	 */
	private BrandPO checkExist(Long brandId) {
		BrandPO brand = getById(brandId);
		if (brand == null) {
			LogUtils.error("品牌ID为" + brandId + "的品牌不存在");
			throw new BusinessException(ResultEnum.BRAND_NOT_EXIST);
		}
		return brand;
	}
}
