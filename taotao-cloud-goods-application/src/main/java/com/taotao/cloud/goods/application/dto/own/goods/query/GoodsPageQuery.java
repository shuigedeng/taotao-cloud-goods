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

import com.taotao.boot.common.model.request.PageQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.ToString;

/**
 * 商品分页查询
 *
 * @author luohq
 * @date 2022-11-27 19:07
 */
@Setter
@Getter
@ToString(callSuper = true)
public class GoodsPageQuery extends PageQuery {
	@Schema(description = "商品名称")
    private String goodsName;

	@Schema(description = "商品分类ID")
    private Long categoryId;

	@Schema(description = "起始商品价格")
    private BigDecimal startGoodsPrice;

	@Schema(description = "终止商品价格")
    private BigDecimal endGoodsPrice;

	@Schema(description = "商品状态(10已上架, 20已下架)")
    private Integer goodsStatus;

	@Schema(description = "过期时间（在此之前过期）")
    private LocalDate beforeExpirationDate;

	@Schema(description = "起始创建时间")
    private LocalDateTime createTimeStart;

	@Schema(description = "结束创建时间")
    private LocalDateTime createTimeEnd;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName( String goodsName ) {
		this.goodsName = goodsName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId( Long categoryId ) {
		this.categoryId = categoryId;
	}

	public BigDecimal getStartGoodsPrice() {
		return startGoodsPrice;
	}

	public void setStartGoodsPrice( BigDecimal startGoodsPrice ) {
		this.startGoodsPrice = startGoodsPrice;
	}

	public BigDecimal getEndGoodsPrice() {
		return endGoodsPrice;
	}

	public void setEndGoodsPrice( BigDecimal endGoodsPrice ) {
		this.endGoodsPrice = endGoodsPrice;
	}

	public Integer getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus( Integer goodsStatus ) {
		this.goodsStatus = goodsStatus;
	}

	public LocalDate getBeforeExpirationDate() {
		return beforeExpirationDate;
	}

	public void setBeforeExpirationDate( LocalDate beforeExpirationDate ) {
		this.beforeExpirationDate = beforeExpirationDate;
	}

	public LocalDateTime getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart( LocalDateTime createTimeStart ) {
		this.createTimeStart = createTimeStart;
	}

	public LocalDateTime getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd( LocalDateTime createTimeEnd ) {
		this.createTimeEnd = createTimeEnd;
	}
}
