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

package com.taotao.cloud.goods.application.dto.own.goods.query;

import com.taotao.boot.common.model.ddd.query.PageQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 商品分页查询
 *
 * @author shuigedeng
 * @since 2022-11-27 19:07
 */
@RecordBuilder
public record GoodsPageQuery(@Schema(description = "商品名称") String goodsName, PageQuery page,
							 @Schema(description = "商品分类ID") Long categoryId,
							 @Schema(description = "起始商品价格") BigDecimal startGoodsPrice,
							 @Schema(description = "终止商品价格") BigDecimal endGoodsPrice,
							 @Schema(description = "商品状态(10已上架, 20已下架)") Integer goodsStatus,
							 @Schema(description = "过期时间（在此之前过期）") LocalDate beforeExpirationDate,
							 @Schema(description = "起始创建时间") LocalDateTime createTimeStart,
							 @Schema(description = "结束创建时间") LocalDateTime createTimeEnd) {

}
