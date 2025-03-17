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

import com.taotao.cloud.goods.application.service.command.CategoryCommandService;
import com.taotao.cloud.goods.application.service.command.CategorySpecificationCommandService;
import com.taotao.cloud.goods.application.service.command.SpecificationCommandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品规格业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:55
 */
@AllArgsConstructor
@Service
public class SpecificationCommandServiceImpl
	implements SpecificationCommandService {

	/**
	 * 分类-规格绑定服务
	 */
	private final CategorySpecificationCommandService categorySpecificationService;
	/**
	 * 分类服务
	 */
	private final CategoryCommandService categoryService;

	@Override
	public boolean deleteSpecification(List<Long> ids) {
		return false;
	}

	@Override
	public boolean saveCategoryBrand(Long categoryId, String[] categorySpecs) {
		return false;
	}
//
//	@Override
//	public boolean deleteSpecification(List<Long> ids) {
//		boolean result = false;
//		for (Long id : ids) {
//			// 如果此规格绑定分类则不允许删除
//			List<CategorySpecificationPO> list = categorySpecificationService.list(
//				new QueryWrapper<CategorySpecificationPO>().eq("specification_id", id));
//
//			//if (!list.isEmpty()) {
//			//	List<Long> categoryIds = new ArrayList<>();
//			//	list.forEach(item -> categoryIds.add(item.getCategoryId()));
//			//	throw new BusinessException(
//			//		ResultEnum.SPEC_DELETE_ERROR.getCode(),
//			//		JSONUtil.toJsonStr(categoryService.getCategoryNameByIds(categoryIds)));
//			//}
//			// 删除规格
//			result = this.removeById(id);
//		}
//		return result;
//	}
//
//
//	@Override
//	@Transactional
//	public boolean saveCategoryBrand(Long categoryId, String[] categorySpecs) {
//		QueryWrapper<CategorySpecificationPO> queryWrapper = new QueryWrapper<>();
//		queryWrapper.eq("category_id", categoryId);
//		// 删除分类规格绑定信息
//		this.categorySpecificationService.remove(queryWrapper);
//		// 绑定规格信息
//		if (categorySpecs != null && categorySpecs.length > 0) {
//			List<CategorySpecificationPO> categorySpecificationPOS = new ArrayList<>();
//			for (String categorySpec : categorySpecs) {
//				categorySpecificationPOS.add(
//					new CategorySpecificationPO(categoryId, Long.valueOf(categorySpec)));
//			}
//			categorySpecificationService.saveBatch(categorySpecificationPOS);
//		}
//		return true;
//	}
}
