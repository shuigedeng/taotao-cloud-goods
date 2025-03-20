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

import com.taotao.cloud.goods.application.dto.category.cmmond.CategoryParameterGroupAddCmd;
import com.taotao.cloud.goods.application.service.command.CategoryParameterGroupCommandService;
import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import com.taotao.cloud.goods.application.service.command.ParametersCommandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 分类绑定参数组接口实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:04
 */
@AllArgsConstructor
@Service
public class CategoryParameterGroupCommandServiceImpl implements CategoryParameterGroupCommandService {

    /**
     * 商品参数服务
     */
    private final ParametersCommandService parametersService;
    /**
     * 商品服务
     */
    private final GoodsCommandService goodsService;

    @Override
    public boolean updateCategoryGroup(CategoryParameterGroupAddCmd categoryParameterGroupAddCmd) {
        return false;
    }

    @Override
    public boolean deleteByCategoryId(Long categoryId) {
        return false;
    }

    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public boolean updateCategoryGroup(CategoryParameterGroupPO categoryParameterGroupPO) {
    //	CategoryParameterGroupPO origin = this.getById(categoryParameterGroupPO.getId());
    //	if (origin == null) {
    //		throw new BusinessException(ResultEnum.CATEGORY_PARAMETER_NOT_EXIST);
    //	}
    //
    //	LambdaQueryWrapper<GoodsPO> queryWrapper = new LambdaQueryWrapper<>();
    //	queryWrapper.select(GoodsPO::getId, GoodsPO::getParams);
    //	queryWrapper.like(GoodsPO::getParams, origin.getId());
    //	List<Map<String, Object>> goodsList = this.goodsService.listMaps(queryWrapper);
    //
    //	for (Map<String, Object> goods : goodsList) {
    //		String params = (String) goods.get("params");
    //		List<GoodsParamsDTO> goodsParamsDTOS = JSONUtil.toList(params, GoodsParamsDTO.class);
    //		List<GoodsParamsDTO> goodsParamsDTOList = goodsParamsDTOS.stream()
    //			.filter(i -> i.getGroupId() != null && i.getGroupId().equals(origin.getId()))
    //			.toList();
    //		for (GoodsParamsDTO goodsParamsDTO : goodsParamsDTOList) {
    //			goodsParamsDTO.setGroupName(categoryParameterGroupPO.getGroupName());
    //		}
    //
    //		this.goodsService.updateGoodsParams(
    //			Long.valueOf(goods.get("id").toString()), JSONUtil.toJsonStr(goodsParamsDTOS));
    //	}
    //
    //	return this.updateById(categoryParameterGroupPO);
    // }

    //	@Override
    //	public boolean updateCategoryGroup(CategoryParameterGroupAddCmd categoryParameterGroupAddCmd) {
    //		return false;
    //	}
    //
    //	@Override
    //	public boolean deleteByCategoryId(Long categoryId) {
    //		return this.baseMapper.delete(new LambdaUpdateWrapper<CategoryParameterGroupPO>()
    //			.eq(CategoryParameterGroupPO::categoryId, categoryId))
    //			> 0;
    //	}

}
