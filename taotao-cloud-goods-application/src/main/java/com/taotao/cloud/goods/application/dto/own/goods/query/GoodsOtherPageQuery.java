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
import com.taotao.cloud.goods.api.enums.GoodsAuthEnum;
import com.taotao.cloud.goods.api.enums.GoodsStatusEnum;
import com.taotao.cloud.goods.api.enums.GoodsTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 商品查询参数
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:33:15
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class GoodsOtherPageQuery extends PageQuery {

    @Serial private static final long serialVersionUID = 2544015852728566887L;

    @Schema(description = "商品编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long goodsId;

    @Schema(description = "商品名称")
    private String goodsName;

    @Schema(description = "商品编号")
    private String id;

    @Schema(description = "商家ID")
    private Long storeId;

    @Schema(description = "卖家名字")
    private String storeName;

    @Schema(description = "价格,可以为范围，如10_1000")
    private String price;

    @Schema(description = "分类path")
    private String categoryPath;

    @Schema(description = "店铺分类id")
    private String storeCategoryPath;

    @Schema(description = "是否自营")
    private Boolean selfOperated;

    /**
     * @see GoodsStatusEnum
     */
    @Schema(description = "上下架状态")
    private String marketEnable;

    /**
     * @see GoodsAuthEnum
     */
    @Schema(description = "审核状态")
    private String authFlag;

    @Schema(description = "库存数量")
    private Integer leQuantity;

    @Schema(description = "库存数量")
    private Integer geQuantity;

    @Schema(description = "是否为推荐商品")
    private Boolean recommend;

    /**
     * @see GoodsTypeEnum
     */
    @Schema(description = "商品类型")
    private String goodsType;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId( Long goodsId ) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName( String goodsName ) {
		this.goodsName = goodsName;
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId( Long storeId ) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName( String storeName ) {
		this.storeName = storeName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice( String price ) {
		this.price = price;
	}

	public String getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath( String categoryPath ) {
		this.categoryPath = categoryPath;
	}

	public String getStoreCategoryPath() {
		return storeCategoryPath;
	}

	public void setStoreCategoryPath( String storeCategoryPath ) {
		this.storeCategoryPath = storeCategoryPath;
	}

	public Boolean getSelfOperated() {
		return selfOperated;
	}

	public void setSelfOperated( Boolean selfOperated ) {
		this.selfOperated = selfOperated;
	}

	public String getMarketEnable() {
		return marketEnable;
	}

	public void setMarketEnable( String marketEnable ) {
		this.marketEnable = marketEnable;
	}

	public String getAuthFlag() {
		return authFlag;
	}

	public void setAuthFlag( String authFlag ) {
		this.authFlag = authFlag;
	}

	public Integer getLeQuantity() {
		return leQuantity;
	}

	public void setLeQuantity( Integer leQuantity ) {
		this.leQuantity = leQuantity;
	}

	public Integer getGeQuantity() {
		return geQuantity;
	}

	public void setGeQuantity( Integer geQuantity ) {
		this.geQuantity = geQuantity;
	}

	public Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend( Boolean recommend ) {
		this.recommend = recommend;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType( String goodsType ) {
		this.goodsType = goodsType;
	}
}
