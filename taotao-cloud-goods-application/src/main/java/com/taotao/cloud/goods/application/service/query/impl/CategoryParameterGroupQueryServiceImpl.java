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

package com.taotao.cloud.goods.application.service.query.impl;

import com.taotao.cloud.goods.application.dto.parameter.clientobject.ParameterGroupCO;
import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import com.taotao.cloud.goods.application.service.command.ParametersCommandService;
import com.taotao.cloud.goods.application.service.query.CategoryParameterGroupQueryService;
import java.util.List;
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
public class CategoryParameterGroupQueryServiceImpl implements CategoryParameterGroupQueryService {

    /**
     * 商品参数服务
     */
    private final ParametersCommandService parametersService;
    /**
     * 商品服务
     */
    private final GoodsCommandService goodsService;

    @Override
    public List<ParameterGroupCO> getCategoryParams(Long categoryId) {
        //// 根据id查询参数组
        // List<CategoryParameterGroupPO> groups = this.getCategoryGroup(categoryId);
        //// 查询参数
        // List<Parameters> params = parametersService.queryParametersByCategoryId(categoryId);
        //// 组合参数vo
        // return convertParamList(groups, params);
        return null;
    }

    //	@Override
    //	public List<CategoryParameterGroupPO> getCategoryGroup(Long categoryId) {
    //		QueryWrapper<CategoryParameterGroupPO> queryWrapper = new QueryWrapper<>();
    //		queryWrapper.eq("category_id", categoryId);
    //		return this.list(queryWrapper);
    //	}

    /**
     * 拼装参数组和参数的返回值
     *
     * @param groupList 参数组list
     * @param paramList 商品参数list
     * @return 参数组和参数的返回值
     */
    // public List<ParameterGroupCO> convertParamList(List<CategoryParameterGroupPO> groupList, List<Parameters>
    // paramList) {
    //	Map<Long, List<Parameters>> map = new HashMap<>(paramList.size());
    //	for (Parameters param : paramList) {
    //		List<Parameters> list = map.get(param.getGroupId());
    //		if (list == null) {
    //			list = new ArrayList<>();
    //		}
    //		list.add(param);
    //		map.put(param.getGroupId(), list);
    //	}
    //
    //	List<ParameterGroupCO> resList = new ArrayList<>();
    //	for (CategoryParameterGroupPO group : groupList) {
    //		ParameterGroupCO groupVo = new ParameterGroupCO();
    //		groupVo.setGroupId(group.getId());
    //		groupVo.setGroupName(group.getGroupName());
    //		groupVo.setParams(
    //			map.get(group.getId()) == null
    //				? new ArrayList<>()
    //				: ParametersConvert.INSTANCE.convert(map.get(group.getId())));
    //		resList.add(groupVo);
    //	}
    //	return resList;
    // }
}
