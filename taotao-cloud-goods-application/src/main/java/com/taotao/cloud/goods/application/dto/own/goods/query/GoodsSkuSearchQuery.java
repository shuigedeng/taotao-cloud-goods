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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taotao.boot.ddd.model.application.dto.Query;
import com.taotao.cloud.goods.api.enums.GoodsAuthEnum;
import com.taotao.cloud.goods.api.enums.GoodsSalesModeEnum;
import com.taotao.cloud.goods.api.enums.GoodsStatusEnum;
import com.taotao.cloud.goods.api.enums.GoodsTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import java.util.Date;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @since 2022/6/13
 **/
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSkuSearchQuery extends Query {

    private String id;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private Boolean deleteFlag;

    private static final long serialVersionUID = 6600436187015048097L;

    @Schema(description = "商品id")
    private String goodsId;

    @Schema(description = "规格信息json", hidden = true)
    @JsonIgnore
    private String specs;

    @Schema(description = "规格信息")
    private String simpleSpecs;

    @Schema(description = "配送模版id")
    private String freightTemplateId;

    @Schema(description = "是否是促销商品")
    private Boolean promotionFlag;

    @Schema(description = "促销价")
    private Double promotionPrice;

    @Schema(description = "商品名称")
    private String goodsName;

    @Length(max = 30, message = "商品规格编号太长，不能超过30个字符")
    @Schema(description = "商品编号")
    private String sn;

    @Schema(description = "品牌id")
    private String brandId;

    @Schema(description = "分类path")
    private String categoryPath;

    @Schema(description = "计量单位")
    private String goodsUnit;

    @Schema(description = "卖点")
    private String sellingPoint;

    @Schema(description = "重量")
    @Max(value = 99999999, message = "重量不能超过99999999")
    private Double weight;

    /**
     * @see GoodsStatusEnum
     */
    @Schema(description = "上架状态")
    private String marketEnable;

    @Schema(description = "商品详情")
    private String intro;

    @Max(value = 99999999, message = "价格不能超过99999999")
    @Schema(description = "商品价格")
    private Double price;

    @Max(value = 99999999, message = "成本价格99999999")
    @Schema(description = "成本价格")
    private Double cost;

    @Schema(description = "浏览数量")
    private Integer viewCount;

    @Schema(description = "购买数量")
    private Integer buyCount;

    @Max(value = 99999999, message = "库存不能超过99999999")
    @Schema(description = "库存")
    private Integer quantity;

    @Schema(description = "商品好评率")
    private Double grade;

    @Schema(description = "缩略图路径")
    private String thumbnail;

    @Schema(description = "大图路径")
    private String big;

    @Schema(description = "小图路径")
    private String small;

    @Schema(description = "原图路径")
    private String original;

    @Schema(description = "店铺分类id")
    private String storeCategoryPath;

    @Schema(description = "评论数量")
    private Integer commentNum;

    @Schema(description = "卖家id")
    private String storeId;

    @Schema(description = "卖家名字")
    private String storeName;

    @Schema(description = "运费模板id")
    private String templateId;

    /**
     * @see GoodsAuthEnum
     */
    @Schema(description = "审核状态")
    private String authFlag;

    @Schema(description = "审核信息")
    private String authMessage;

    @Schema(description = "下架原因")
    private String underMessage;

    @Schema(description = "是否自营")
    private Boolean selfOperated;

    @Schema(description = "商品移动端详情")
    private String mobileIntro;

    @Schema(description = "商品视频")
    private String goodsVideo;

    @Schema(description = "是否为推荐商品", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean recommend;

    /**
     * @see GoodsSalesModeEnum
     */
    @Schema(description = "销售模式", requiredMode = Schema.RequiredMode.REQUIRED)
    private String salesModel;

    /**
     * @see GoodsTypeEnum
     */
    @Schema(description = "商品类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private String goodsType;

    @Schema(description = "商品参数json")
    private String params;

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy( String createBy ) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime( Date createTime ) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy( String updateBy ) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime( Date updateTime ) {
		this.updateTime = updateTime;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag( Boolean deleteFlag ) {
		this.deleteFlag = deleteFlag;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId( String goodsId ) {
		this.goodsId = goodsId;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs( String specs ) {
		this.specs = specs;
	}

	public String getSimpleSpecs() {
		return simpleSpecs;
	}

	public void setSimpleSpecs( String simpleSpecs ) {
		this.simpleSpecs = simpleSpecs;
	}

	public String getFreightTemplateId() {
		return freightTemplateId;
	}

	public void setFreightTemplateId( String freightTemplateId ) {
		this.freightTemplateId = freightTemplateId;
	}

	public Boolean getPromotionFlag() {
		return promotionFlag;
	}

	public void setPromotionFlag( Boolean promotionFlag ) {
		this.promotionFlag = promotionFlag;
	}

	public Double getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice( Double promotionPrice ) {
		this.promotionPrice = promotionPrice;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName( String goodsName ) {
		this.goodsName = goodsName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn( String sn ) {
		this.sn = sn;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId( String brandId ) {
		this.brandId = brandId;
	}

	public String getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath( String categoryPath ) {
		this.categoryPath = categoryPath;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit( String goodsUnit ) {
		this.goodsUnit = goodsUnit;
	}

	public String getSellingPoint() {
		return sellingPoint;
	}

	public void setSellingPoint( String sellingPoint ) {
		this.sellingPoint = sellingPoint;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight( Double weight ) {
		this.weight = weight;
	}

	public String getMarketEnable() {
		return marketEnable;
	}

	public void setMarketEnable( String marketEnable ) {
		this.marketEnable = marketEnable;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro( String intro ) {
		this.intro = intro;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice( Double price ) {
		this.price = price;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost( Double cost ) {
		this.cost = cost;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount( Integer viewCount ) {
		this.viewCount = viewCount;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount( Integer buyCount ) {
		this.buyCount = buyCount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity( Integer quantity ) {
		this.quantity = quantity;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade( Double grade ) {
		this.grade = grade;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail( String thumbnail ) {
		this.thumbnail = thumbnail;
	}

	public String getBig() {
		return big;
	}

	public void setBig( String big ) {
		this.big = big;
	}

	public String getSmall() {
		return small;
	}

	public void setSmall( String small ) {
		this.small = small;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal( String original ) {
		this.original = original;
	}

	public String getStoreCategoryPath() {
		return storeCategoryPath;
	}

	public void setStoreCategoryPath( String storeCategoryPath ) {
		this.storeCategoryPath = storeCategoryPath;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum( Integer commentNum ) {
		this.commentNum = commentNum;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId( String storeId ) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName( String storeName ) {
		this.storeName = storeName;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId( String templateId ) {
		this.templateId = templateId;
	}

	public String getAuthFlag() {
		return authFlag;
	}

	public void setAuthFlag( String authFlag ) {
		this.authFlag = authFlag;
	}

	public String getAuthMessage() {
		return authMessage;
	}

	public void setAuthMessage( String authMessage ) {
		this.authMessage = authMessage;
	}

	public String getUnderMessage() {
		return underMessage;
	}

	public void setUnderMessage( String underMessage ) {
		this.underMessage = underMessage;
	}

	public Boolean getSelfOperated() {
		return selfOperated;
	}

	public void setSelfOperated( Boolean selfOperated ) {
		this.selfOperated = selfOperated;
	}

	public String getMobileIntro() {
		return mobileIntro;
	}

	public void setMobileIntro( String mobileIntro ) {
		this.mobileIntro = mobileIntro;
	}

	public String getGoodsVideo() {
		return goodsVideo;
	}

	public void setGoodsVideo( String goodsVideo ) {
		this.goodsVideo = goodsVideo;
	}

	public Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend( Boolean recommend ) {
		this.recommend = recommend;
	}

	public String getSalesModel() {
		return salesModel;
	}

	public void setSalesModel( String salesModel ) {
		this.salesModel = salesModel;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType( String goodsType ) {
		this.goodsType = goodsType;
	}

	public String getParams() {
		return params;
	}

	public void setParams( String params ) {
		this.params = params;
	}
}
