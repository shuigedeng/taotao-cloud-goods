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

package com.taotao.cloud.goods.application.dto.own.draft.result;

import com.taotao.boot.ddd.model.application.dto.BaseResult;
import com.taotao.cloud.goods.api.enums.DraftGoodsSaveTypeEnum;
import com.taotao.cloud.goods.api.enums.GoodsStatusEnum;
import com.taotao.cloud.goods.api.enums.GoodsTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.math.BigDecimal;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 草稿商品基础CO
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-14 22:10:24
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "分类品牌CO")
public class DraftGoodsResult extends BaseResult {

    @Serial private static final long serialVersionUID = 1450550797436233753L;

    private Long id;

    /** 商品名称 */
    private String goodsName;

    /** 商品价格 */
    private BigDecimal price;

    /** 品牌id */
    private Long brandId;

    /** 分类path */
    private String categoryPath;

    /** 计量单位 */
    private String goodsUnit;

    /** 卖点 */
    private String sellingPoint;

    /**
     * 上架状态
     *
     * @see GoodsStatusEnum
     */
    private String marketEnable;

    /** 详情 */
    private String intro;

    /** 商品移动端详情 */
    private String mobileIntro;

    /** 购买数量 */
    private Integer buyCount;

    /** 库存 */
    private Integer quantity;

    /** 可用库存 */
    private Integer enableQuantity;

    /** 商品好评率 */
    private BigDecimal grade;

    /** 缩略图路径 */
    private String thumbnail;

    /** 大图路径 */
    private String big;

    /** 小图路径 */
    private String small;

    /** 原图路径 */
    private String original;

    /** 店铺分类路径 */
    private String storeCategoryPath;

    /** 评论数量 */
    private Integer commentNum;

    /** 卖家id */
    private Long storeId;

    /** 卖家名字 */
    private String storeName;

    /** 运费模板id */
    private Long templateId;

    /** 是否自营 */
    private Boolean selfOperated;

    /** 商品视频 */
    private String goodsVideo;

    /** 是否为推荐商品 */
    private Boolean recommend;

    /** 销售模式 */
    private String salesModel;

    /**
     * 草稿商品保存类型
     *
     * @see DraftGoodsSaveTypeEnum
     */
    private String saveType;

    /** 分类名称JSON */
    private String categoryNameJson;

    /** 商品参数JSON */
    private String goodsParamsListJson;

    /** 商品图片JSON */
    private String goodsGalleryListJson;

    /** sku列表JSON */
    private String skuListJson;

    /**
     * 商品类型
     *
     * @see GoodsTypeEnum
     */
    private String goodsType;

    /**
     * 获取
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取
     * @return goodsName
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置
     * @param goodsName
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取
     * @return brandId
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * 设置
     * @param brandId
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * 获取
     * @return categoryPath
     */
    public String getCategoryPath() {
        return categoryPath;
    }

    /**
     * 设置
     * @param categoryPath
     */
    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    /**
     * 获取
     * @return goodsUnit
     */
    public String getGoodsUnit() {
        return goodsUnit;
    }

    /**
     * 设置
     * @param goodsUnit
     */
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    /**
     * 获取
     * @return sellingPoint
     */
    public String getSellingPoint() {
        return sellingPoint;
    }

    /**
     * 设置
     * @param sellingPoint
     */
    public void setSellingPoint(String sellingPoint) {
        this.sellingPoint = sellingPoint;
    }

    /**
     * 获取
     * @return marketEnable
     */
    public String getMarketEnable() {
        return marketEnable;
    }

    /**
     * 设置
     * @param marketEnable
     */
    public void setMarketEnable(String marketEnable) {
        this.marketEnable = marketEnable;
    }

    /**
     * 获取
     * @return intro
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 设置
     * @param intro
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 获取
     * @return mobileIntro
     */
    public String getMobileIntro() {
        return mobileIntro;
    }

    /**
     * 设置
     * @param mobileIntro
     */
    public void setMobileIntro(String mobileIntro) {
        this.mobileIntro = mobileIntro;
    }

    /**
     * 获取
     * @return buyCount
     */
    public Integer getBuyCount() {
        return buyCount;
    }

    /**
     * 设置
     * @param buyCount
     */
    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    /**
     * 获取
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取
     * @return enableQuantity
     */
    public Integer getEnableQuantity() {
        return enableQuantity;
    }

    /**
     * 设置
     * @param enableQuantity
     */
    public void setEnableQuantity(Integer enableQuantity) {
        this.enableQuantity = enableQuantity;
    }

    /**
     * 获取
     * @return grade
     */
    public BigDecimal getGrade() {
        return grade;
    }

    /**
     * 设置
     * @param grade
     */
    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    /**
     * 获取
     * @return thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 设置
     * @param thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 获取
     * @return big
     */
    public String getBig() {
        return big;
    }

    /**
     * 设置
     * @param big
     */
    public void setBig(String big) {
        this.big = big;
    }

    /**
     * 获取
     * @return small
     */
    public String getSmall() {
        return small;
    }

    /**
     * 设置
     * @param small
     */
    public void setSmall(String small) {
        this.small = small;
    }

    /**
     * 获取
     * @return original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * 设置
     * @param original
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * 获取
     * @return storeCategoryPath
     */
    public String getStoreCategoryPath() {
        return storeCategoryPath;
    }

    /**
     * 设置
     * @param storeCategoryPath
     */
    public void setStoreCategoryPath(String storeCategoryPath) {
        this.storeCategoryPath = storeCategoryPath;
    }

    /**
     * 获取
     * @return commentNum
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * 设置
     * @param commentNum
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * 获取
     * @return storeId
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * 设置
     * @param storeId
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * 获取
     * @return storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * 设置
     * @param storeName
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * 获取
     * @return templateId
     */
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * 设置
     * @param templateId
     */
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    /**
     * 获取
     * @return selfOperated
     */
    public Boolean getSelfOperated() {
        return selfOperated;
    }

    /**
     * 设置
     * @param selfOperated
     */
    public void setSelfOperated(Boolean selfOperated) {
        this.selfOperated = selfOperated;
    }

    /**
     * 获取
     * @return goodsVideo
     */
    public String getGoodsVideo() {
        return goodsVideo;
    }

    /**
     * 设置
     * @param goodsVideo
     */
    public void setGoodsVideo(String goodsVideo) {
        this.goodsVideo = goodsVideo;
    }

    /**
     * 获取
     * @return recommend
     */
    public Boolean getRecommend() {
        return recommend;
    }

    /**
     * 设置
     * @param recommend
     */
    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    /**
     * 获取
     * @return salesModel
     */
    public String getSalesModel() {
        return salesModel;
    }

    /**
     * 设置
     * @param salesModel
     */
    public void setSalesModel(String salesModel) {
        this.salesModel = salesModel;
    }

    /**
     * 获取
     * @return saveType
     */
    public String getSaveType() {
        return saveType;
    }

    /**
     * 设置
     * @param saveType
     */
    public void setSaveType(String saveType) {
        this.saveType = saveType;
    }

    /**
     * 获取
     * @return categoryNameJson
     */
    public String getCategoryNameJson() {
        return categoryNameJson;
    }

    /**
     * 设置
     * @param categoryNameJson
     */
    public void setCategoryNameJson(String categoryNameJson) {
        this.categoryNameJson = categoryNameJson;
    }

    /**
     * 获取
     * @return goodsParamsListJson
     */
    public String getGoodsParamsListJson() {
        return goodsParamsListJson;
    }

    /**
     * 设置
     * @param goodsParamsListJson
     */
    public void setGoodsParamsListJson(String goodsParamsListJson) {
        this.goodsParamsListJson = goodsParamsListJson;
    }

    /**
     * 获取
     * @return goodsGalleryListJson
     */
    public String getGoodsGalleryListJson() {
        return goodsGalleryListJson;
    }

    /**
     * 设置
     * @param goodsGalleryListJson
     */
    public void setGoodsGalleryListJson(String goodsGalleryListJson) {
        this.goodsGalleryListJson = goodsGalleryListJson;
    }

    /**
     * 获取
     * @return skuListJson
     */
    public String getSkuListJson() {
        return skuListJson;
    }

    /**
     * 设置
     * @param skuListJson
     */
    public void setSkuListJson(String skuListJson) {
        this.skuListJson = skuListJson;
    }

    /**
     * 获取
     * @return goodsType
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * 设置
     * @param goodsType
     */
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }
}
