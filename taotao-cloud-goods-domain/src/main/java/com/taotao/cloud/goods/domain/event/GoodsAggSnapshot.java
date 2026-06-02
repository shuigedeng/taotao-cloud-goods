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

package com.taotao.cloud.goods.domain.event;

import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.common.utils.lang.StringUtils;
import com.taotao.boot.ddd.model.domain.AggregateRoot;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.boot.ddd.model.val.Price;
import com.taotao.cloud.goods.domain.aggregate.GoodsAgg;
import com.taotao.cloud.goods.domain.entity.Category;
import com.taotao.cloud.goods.domain.entity.Tag;
import com.taotao.cloud.goods.domain.valobj.GoodsName;
import com.taotao.cloud.goods.domain.valobj.GoodsSpec;
import com.taotao.cloud.goods.domain.valobj.GoodsStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * GoodsAgg
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@Data
@Builder
public class GoodsAggSnapshot  {

	/**
	 * 商品ID
	 */
	@NotNull
	private BizId id;

	/**
	 * 商品标签集合
	 */
	private List<Tag> tags;

	/**
	 * 商品所属分类
	 */
	private @NotNull Category category;

	/**
	 * 商品名称
	 */
	@NotNull
	private GoodsName goodsName;

	/**
	 * 商品描述
	 */
	@NotNull
	private GoodsSpec goodsSpec;

	/**
	 * 商品价格
	 */
	@NotNull
	private Price goodsPrice;

	/**
	 * 商品状态
	 */
	@NotNull
	private GoodsStatus goodsStatus;

	private String templateId;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;
	private String goodsNo;
	private Boolean recommend;

//	public static GoodsAggSnapshot from( GoodsAgg goodsAgg){
//		return GoodsAggSnapshot.builder()
//			.id(goodsAgg.getId())
//			.templateId(goodsAgg.getTemplateId())
//			.build();
//	}
}
