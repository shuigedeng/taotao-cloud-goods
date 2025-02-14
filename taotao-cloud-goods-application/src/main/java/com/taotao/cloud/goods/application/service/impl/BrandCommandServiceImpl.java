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

import com.taotao.cloud.goods.application.dto.brand.cmmond.BrandAddCmd;
import com.taotao.cloud.goods.application.dto.brand.cmmond.BrandUpdateCmd;
import com.taotao.cloud.goods.application.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品品牌业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:01:19
 */
@Service
@AllArgsConstructor
public class BrandCommandServiceImpl
	implements BrandCommandService {

	/**
	 * 分类品牌绑定服务
	 */
	private final CategoryBrandCommandService categoryBrandCommandService;
	private final CategoryBrandQueryService categoryBrandQueryService;
	/**
	 * 分类服务
	 */
	private final CategoryCommandService categoryCommandService;
	private final CategoryQueryService categoryQueryService;
	/**
	 * 商品服务
	 */
	private final GoodsCommandService goodsCommandService;
	private final GoodsQueryService goodsQueryService;

	@Override
	public boolean deleteBrands(List<Long> ids) {
		return false;
	}

	@Override
	public boolean addBrand(BrandAddCmd brandAddCmd) {
		return false;
	}

	@Override
	public boolean updateBrand(BrandUpdateCmd brandUpdateCmd) {
		return false;
	}

	@Override
	public boolean brandDisable(Long brandId, boolean disable) {
		return false;
	}


//	@Override
//	public boolean addBrand(BrandAddCmd brandDTO) {
//		LambdaQueryWrapper<BrandPO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//		lambdaQueryWrapper.eq(BrandPO::getName, brandDTO.name());
//		if (getOne(lambdaQueryWrapper) != null) {
//			throw new BusinessException(ResultEnum.BRAND_NAME_EXIST_ERROR);
//		}
//		return this.save(BrandAssembler.INSTANCE.convert(brandDTO));
//	}
//
//	@Override
//	public boolean updateBrand(BrandUpdateCmd brandDTO) {
//		this.checkExist(brandDTO.id());
//
//		if (getOne(new LambdaQueryWrapper<BrandPO>()
//			.eq(BrandPO::getName, brandDTO.name())
//			.ne(BrandPO::getId, brandDTO.id()))
//			!= null) {
//			throw new BusinessException(ResultEnum.BRAND_NAME_EXIST_ERROR);
//		}
//
//		return this.updateById(BeanUtils.copy(brandDTO, BrandPO.class));
//	}
//
//	@Override
//	public boolean brandDisable(Long brandId, boolean disable) {
//		BrandPO brand = this.checkExist(brandId);
//		// 如果是要禁用，则需要先判定绑定关系
//		if (disable) {
//			List<Long> ids = new ArrayList<>();
//			ids.add(brandId);
//			checkBind(ids);
//		}
//		brand.setDelFlag(disable);
//		return updateById(brand);
//	}
//
//
//	@Override
//	public boolean deleteBrands(List<Long> ids) {
//		checkBind(ids);
//		return this.removeByIds(ids);
//	}
//
//	/**
//	 * 校验绑定关系
//	 *
//	 * @param brandIds 品牌Ids
//	 */
//	private void checkBind(List<Long> brandIds) {
//		// 分了绑定关系查询
//		List<CategoryBrandPO> categoryBrands = categoryBrandQueryService.getCategoryBrandListByBrandId(
//			brandIds);
//
//		if (!categoryBrands.isEmpty()) {
//			List<Long> categoryIds =
//				categoryBrands.stream().map(CategoryBrandPO::categoryId).toList();
//			throw new BusinessException(
//				ResultEnum.BRAND_USE_DISABLE_ERROR.getCode(),
//				JSONUtil.toJsonStr(categoryQueryService.getCategoryNameByIds(categoryIds)));
//		}
//
//		// 分了商品绑定关系查询
//		List<GoodsPO> goods = goodsQueryService.getByBrandIds(brandIds);
//		if (!goods.isEmpty()) {
//			List<String> goodsNames = goods.stream().map(GoodsPO::goodsName).toList();
//			throw new BusinessException(ResultEnum.BRAND_BIND_GOODS_ERROR.getCode(),
//				JSONUtil.toJsonStr(goodsNames));
//		}
//	}
//
//	/**
//	 * 校验是否存在
//	 *
//	 * @param brandId 品牌ID
//	 * @return 品牌
//	 */
//	private BrandPO checkExist(Long brandId) {
//		BrandPO brand = getById(brandId);
//		if (brand == null) {
//			LogUtils.error("品牌ID为" + brandId + "的品牌不存在");
//			throw new BusinessException(ResultEnum.BRAND_NOT_EXIST);
//		}
//		return brand;
//	}
}
