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

package com.taotao.cloud.goods.api.feign.dto.response;

import com.taotao.boot.common.enums.PromotionTypeEnum;
import com.taotao.cloud.goods.api.enums.GoodsTypeEnum;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商品索引
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:18:08
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
public class EsGoodsIndexCommandApiResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -6856471777036048874L;

    private Long id;

    /** 商品id */
    private Long goodsId;

    /** 商品名称 */
    private String goodsName;

    /** 商品编号 */
    private String sn;

    /** 卖家id */
    private Long storeId;

    /** 卖家名称 */
    private String storeName;

    /** 销量 */
    private Integer buyCount;

    /** 小图 */
    private String small;

    /** 缩略图 */
    private String thumbnail;

    /** 品牌id */
    private Long brandId;

    /** 品牌名称 */
    private String brandName;

    /** 品牌图片地址 */
    private String brandUrl;

    /** 分类path */
    private String categoryPath;

    /** 分类名称path */
    private String categoryNamePath;

    /** 店铺分类id */
    private String storeCategoryPath;

    /** 店铺分类名称 */
    private String storeCategoryNamePath;

    /** 商品价格 */
    private BigDecimal price;

    /** 促销价 */
    private BigDecimal promotionPrice;

    /** 如果是积分商品需要使用的积分 */
    private Integer point;

    /** 评价数量 */
    private Integer commentNum;

    /** 好评数量 */
    private Integer highPraiseNum;

    /** 好评率 */
    private BigDecimal grade;

    /** 详情 */
    private String intro;

    /** 商品移动端详情 */
    private String mobileIntro;

    /** 是否自营 */
    private Boolean selfOperated;

    /** 是否为推荐商品 */
    private Boolean recommend;

    /** 销售模式 */
    private String salesModel;

    /** 审核状态 */
    private String authFlag;

    /** 卖点 */
    private String sellingPoint;

    /** 上架状态 */
    private String marketEnable;

    /** 商品视频 */
    private String goodsVideo;

    private LocalDateTime releaseTime;

    /**
     * 商品类型
     *
     * @see GoodsTypeEnum
     */
    private String goodsType;

    /** 商品sku基础分数 */
    private Integer skuSource;

    /** 商品属性（参数和规格） */
    private List<EsGoodsAttributeCommandApiResponse> attrList;

    /**
     * 商品促销活动集合JSON，key 为 促销活动类型，value 为 促销活动实体信息
     *
     * @see PromotionTypeEnum value 为 促销活动实体信息
     */
    private String promotionMapJson;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandUrl() {
		return brandUrl;
	}

	public void setBrandUrl(String brandUrl) {
		this.brandUrl = brandUrl;
	}

	public String getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}

	public String getCategoryNamePath() {
		return categoryNamePath;
	}

	public void setCategoryNamePath(String categoryNamePath) {
		this.categoryNamePath = categoryNamePath;
	}

	public String getStoreCategoryPath() {
		return storeCategoryPath;
	}

	public void setStoreCategoryPath(String storeCategoryPath) {
		this.storeCategoryPath = storeCategoryPath;
	}

	public String getStoreCategoryNamePath() {
		return storeCategoryNamePath;
	}

	public void setStoreCategoryNamePath(String storeCategoryNamePath) {
		this.storeCategoryNamePath = storeCategoryNamePath;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getHighPraiseNum() {
		return highPraiseNum;
	}

	public void setHighPraiseNum(Integer highPraiseNum) {
		this.highPraiseNum = highPraiseNum;
	}

	public BigDecimal getGrade() {
		return grade;
	}

	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getMobileIntro() {
		return mobileIntro;
	}

	public void setMobileIntro(String mobileIntro) {
		this.mobileIntro = mobileIntro;
	}

	public Boolean getSelfOperated() {
		return selfOperated;
	}

	public void setSelfOperated(Boolean selfOperated) {
		this.selfOperated = selfOperated;
	}

	public Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}

	public String getSalesModel() {
		return salesModel;
	}

	public void setSalesModel(String salesModel) {
		this.salesModel = salesModel;
	}

	public String getAuthFlag() {
		return authFlag;
	}

	public void setAuthFlag(String authFlag) {
		this.authFlag = authFlag;
	}

	public String getSellingPoint() {
		return sellingPoint;
	}

	public void setSellingPoint(String sellingPoint) {
		this.sellingPoint = sellingPoint;
	}

	public String getMarketEnable() {
		return marketEnable;
	}

	public void setMarketEnable(String marketEnable) {
		this.marketEnable = marketEnable;
	}

	public String getGoodsVideo() {
		return goodsVideo;
	}

	public void setGoodsVideo(String goodsVideo) {
		this.goodsVideo = goodsVideo;
	}

	public LocalDateTime getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(LocalDateTime releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public Integer getSkuSource() {
		return skuSource;
	}

	public void setSkuSource(Integer skuSource) {
		this.skuSource = skuSource;
	}

	public List<EsGoodsAttributeCommandApiResponse> getAttrList() {
		return attrList;
	}

	public void setAttrList(
		List<EsGoodsAttributeCommandApiResponse> attrList) {
		this.attrList = attrList;
	}

	public String getPromotionMapJson() {
		return promotionMapJson;
	}

	public void setPromotionMapJson(String promotionMapJson) {
		this.promotionMapJson = promotionMapJson;
	}
}
