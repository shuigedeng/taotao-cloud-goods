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

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taotao.boot.common.enums.ResultEnum;
import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.webagg.service.impl.BaseSuperServiceImpl;
import com.taotao.cloud.goods.application.dto.goods.cmmond.GoodsParamsAddCmd;
import com.taotao.cloud.goods.application.dto.goods.cmmond.GoodsParamsItemAddCmd;
import com.taotao.cloud.goods.application.service.GoodsCommandService;
import com.taotao.cloud.goods.application.service.ParametersQueryService;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.ParametersMapper;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.GoodsPO;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.ParametersPO;
import com.taotao.cloud.goods.infrastructure.persistent.repository.cls.ParametersRepository;
import com.taotao.cloud.goods.infrastructure.persistent.repository.inf.IParametersRepository;
import com.taotao.cloud.stream.framework.rocketmq.RocketmqSendCallbackBuilder;
import com.taotao.cloud.stream.framework.rocketmq.tags.GoodsTagsEnum;
import com.taotao.cloud.stream.properties.RocketmqCustomProperties;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.dromara.hutool.core.text.CharSequenceUtil;
import org.dromara.hutool.json.JSONUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商品参数业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:52
 */
@AllArgsConstructor
@Service
public class ParametersQueryServiceImpl
	extends
	BaseSuperServiceImpl<ParametersPO, Long, ParametersMapper, ParametersRepository, IParametersRepository>
	implements ParametersQueryService {

	/**
	 * 商品服务
	 */
	private final GoodsCommandService goodsService;

	private final RocketmqCustomProperties rocketmqCustomProperties;
	private final RocketMQTemplate rocketMQTemplate;



	@Override
	public List<ParametersPO> queryParametersByCategoryId(Long categoryId) {
		QueryWrapper<ParametersPO> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("category_id", categoryId);
		return this.list(queryWrapper);
	}

	/**
	 * 更新商品参数信息
	 *
	 * @param goodsParamsAddCmdList 商品参数项列表
	 * @param parametersPO          参数信息
	 */
	private void setGoodsItemDTOList(List<GoodsParamsAddCmd> goodsParamsAddCmdList,
									 ParametersPO parametersPO) {
		for (GoodsParamsAddCmd goodsParamsAddCmd : goodsParamsAddCmdList) {
			List<GoodsParamsItemAddCmd> goodsParamsItemAddCmdList = goodsParamsAddCmd.getGoodsParamsItemAddCmdList()
				.stream()
				.filter(i -> i.paramId() != null && i.paramId().equals(parametersPO.getId()))
				.toList();
			for (GoodsParamsItemAddCmd goodsParamsItemAddCmd : goodsParamsItemAddCmdList) {
				this.setGoodsItemDTO(goodsParamsItemAddCmd, parametersPO);
			}
		}
	}

	/**
	 * 更新商品参数详细信息
	 *
	 * @param goodsParamsItemAddCmd 商品参数项信息
	 * @param parametersPO          参数信息
	 */
	private void setGoodsItemDTO(GoodsParamsItemAddCmd goodsParamsItemAddCmd, ParametersPO parametersPO) {
		if (goodsParamsItemAddCmd.paramId().equals(parametersPO.getId())) {
			goodsParamsItemAddCmd.paramId(parametersPO.getId());
			goodsParamsItemAddCmd.paramName(parametersPO.paramName());
			goodsParamsItemAddCmd.required(parametersPO.required());
			goodsParamsItemAddCmd.isIndex(parametersPO.isIndex());
			goodsParamsItemAddCmd.sort(parametersPO.sort());
			if (CharSequenceUtil.isNotEmpty(parametersPO.options())
				&& CharSequenceUtil.isNotEmpty(goodsParamsItemAddCmd.paramValue())
				&& !parametersPO.options().contains(goodsParamsItemAddCmd.paramValue())) {
				if (parametersPO.options().contains(",")) {
					goodsParamsItemAddCmd.paramValue(parametersPO
						.options()
						.substring(0, parametersPO.options().indexOf(",")));
				} else {
					goodsParamsItemAddCmd.paramValue(parametersPO.options());
				}
			}
		}
	}
}
