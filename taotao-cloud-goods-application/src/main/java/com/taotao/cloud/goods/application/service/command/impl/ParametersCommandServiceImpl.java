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

import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import com.taotao.cloud.goods.application.service.command.ParametersCommandService;
import com.taotao.cloud.stream.properties.RocketmqCustomProperties;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

/**
 * 商品参数业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:52
 */
@AllArgsConstructor
@Service
public class ParametersCommandServiceImpl implements ParametersCommandService {

    /**
     * 商品服务
     */
    private final GoodsCommandService goodsService;

    private final RocketmqCustomProperties rocketmqCustomProperties;
    private final RocketMQTemplate rocketMQTemplate;

    //	@Override
    //	@Transactional(rollbackFor = Exception.class)
    //	public boolean updateParameter(ParametersPO parametersPO) {
    //		ParametersPO origin = this.getById(parametersPO.getId());
    //		if (origin == null) {
    //			throw new BusinessException(ResultEnum.CATEGORY_NOT_EXIST);
    //		}
    //
    //		List<String> goodsIds = new ArrayList<>();
    //		LambdaQueryWrapper<GoodsPO> queryWrapper = new LambdaQueryWrapper<>();
    //		queryWrapper.select(GoodsPO::getId, GoodsPO::params);
    //		queryWrapper.like(GoodsPO::params, parametersPO.groupId());
    //		List<Map<String, Object>> goodsList = this.goodsService.listMaps(queryWrapper);
    //
    //		if (!goodsList.isEmpty()) {
    //			for (Map<String, Object> goods : goodsList) {
    //				String params = (String) goods.get("params");
    //				List<GoodsParamsAddCmd> goodsParamsAddCmds = JSONUtil.toList(params,
    //					GoodsParamsAddCmd.class);
    //				List<GoodsParamsAddCmd> goodsParamsAddCmdList = goodsParamsAddCmds.stream()
    //					.filter(i -> i.getGroupId() != null && i.getGroupId()
    //						.equals(parametersPO.groupId()))
    //					.toList();
    //				this.setGoodsItemDTOList(goodsParamsAddCmdList, parametersPO);
    //				this.goodsService.updateGoodsParams(
    //					Convert.toLong(goods.get("id")), JSONUtil.toJsonStr(goodsParamsAddCmds));
    //				goodsIds.add(goods.get("id").toString());
    //			}
    //
    //			String destination =
    //				rocketmqCustomProperties.getGoodsTopic() + ":"
    //					+ GoodsTagsEnum.UPDATE_GOODS_INDEX.name();
    //			// 发送mq消息
    //			rocketMQTemplate.asyncSend(
    //				destination, JSONUtil.toJsonStr(goodsIds),
    //				RocketmqSendCallbackBuilder.commonCallback());
    //		}
    //		return this.updateById(parametersPO);
    //	}
    //
    //
    //
    //	/**
    //	 * 更新商品参数信息
    //	 *
    //	 * @param goodsParamsAddCmdList 商品参数项列表
    //	 * @param parametersPO          参数信息
    //	 */
    //	private void setGoodsItemDTOList(List<GoodsParamsAddCmd> goodsParamsAddCmdList,
    //									 ParametersPO parametersPO) {
    //		for (GoodsParamsAddCmd goodsParamsAddCmd : goodsParamsAddCmdList) {
    //			List<GoodsParamsItemAddCmd> goodsParamsItemAddCmdList = goodsParamsAddCmd.getGoodsParamsItemAddCmdList()
    //				.stream()
    //				.filter(i -> i.paramId() != null && i.paramId().equals(parametersPO.getId()))
    //				.toList();
    //			for (GoodsParamsItemAddCmd goodsParamsItemAddCmd : goodsParamsItemAddCmdList) {
    //				this.setGoodsItemDTO(goodsParamsItemAddCmd, parametersPO);
    //			}
    //		}
    //	}
    //
    //	/**
    //	 * 更新商品参数详细信息
    //	 *
    //	 * @param goodsParamsItemAddCmd 商品参数项信息
    //	 * @param parametersPO          参数信息
    //	 */
    //	private void setGoodsItemDTO(GoodsParamsItemAddCmd goodsParamsItemAddCmd, ParametersPO parametersPO) {
    //		if (goodsParamsItemAddCmd.paramId().equals(parametersPO.getId())) {
    //			goodsParamsItemAddCmd.paramId(parametersPO.getId());
    //			goodsParamsItemAddCmd.paramName(parametersPO.paramName());
    //			goodsParamsItemAddCmd.required(parametersPO.required());
    //			goodsParamsItemAddCmd.isIndex(parametersPO.isIndex());
    //			goodsParamsItemAddCmd.sort(parametersPO.sort());
    //			if (CharSequenceUtil.isNotEmpty(parametersPO.options())
    //				&& CharSequenceUtil.isNotEmpty(goodsParamsItemAddCmd.paramValue())
    //				&& !parametersPO.options().contains(goodsParamsItemAddCmd.paramValue())) {
    //				if (parametersPO.options().contains(",")) {
    //					goodsParamsItemAddCmd.paramValue(parametersPO
    //						.options()
    //						.substring(0, parametersPO.options().indexOf(",")));
    //				} else {
    //					goodsParamsItemAddCmd.paramValue(parametersPO.options());
    //				}
    //			}
    //		}
    //	}
}
