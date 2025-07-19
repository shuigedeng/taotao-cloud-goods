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

package com.taotao.cloud.goods.infrastructure.persistent.persistence;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.webagg.entity.BaseSuperEntity;
import com.taotao.cloud.goods.api.enums.DraftGoodsSaveTypeEnum;
import com.taotao.cloud.goods.api.enums.GoodsStatusEnum;
import com.taotao.cloud.goods.api.enums.GoodsTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.dromara.hutool.core.text.CharSequenceUtil;
import org.dromara.hutool.http.html.HtmlUtil;
import org.hibernate.Hibernate;

/** 草稿商品表 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
@Entity
@Table(
        name = DraftGoodsPO.TABLE_NAME,
        uniqueConstraints = {
            @UniqueConstraint(name = "uniq_goods_no", columnNames = "goods_no"),
        },
        indexes = {
            @Index(name = "idx_create_date", columnList = "`create_date`"),
        })
@TableName(DraftGoodsPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = DraftGoodsPO.TABLE_NAME)
public class DraftGoodsPO extends BaseSuperEntity<DraftGoodsPO, Long> {

    public static final String TABLE_NAME = "ttc_draft_goods";

    /** 商品名称 */
    @Column(name = "`goods_name`", columnDefinition = "varchar(255) not null comment '商品名称'")
    private String goodsName;

    /** 商品价格 */
    @Column(name = "`price`", columnDefinition = "decimal(10,2) not null comment '商品价格'")
    private BigDecimal price;

    /** 品牌id */
    @Column(name = "`brand_id`", columnDefinition = "bigint not null comment '品牌id'")
    private Long brandId;

    /** 分类path */
    @Column(name = "`category_path`", columnDefinition = "varchar(255) not null comment '分类path'")
    private String categoryPath;

    /** 计量单位 */
    @Column(name = "`goods_unit`", columnDefinition = "varchar(255) not null comment '计量单位'")
    private String goodsUnit;

    /** 卖点 */
    @Column(name = "`selling_point`", columnDefinition = "varchar(255) not null comment '卖点'")
    private String sellingPoint;

    /**
     * 上架状态
     *
     * @see GoodsStatusEnum
     */
    @Column(name = "`market_enable`", columnDefinition = "varchar(255) not null comment '上架状态'")
    private String marketEnable;

    /** 详情 */
    @Column(name = "`intro`", columnDefinition = "mediumtext not null comment '详情'")
    private String intro;

    /** 商品移动端详情 */
    @Column(name = "`mobile_intro`", columnDefinition = "mediumtext not null comment '商品移动端详情'")
    private String mobileIntro;

    /** 购买数量 */
    @Column(name = "`buy_count`", columnDefinition = "int null default 0 comment '购买数量'")
    private Integer buyCount;

    /** 库存 */
    @Column(name = "`quantity`", columnDefinition = "int not null default 0 comment '库存'")
    private Integer quantity;

    /** 可用库存 */
    @Column(name = "`enable_quantity`", columnDefinition = "int not null default 0 comment '可用库存'")
    private Integer enableQuantity;

    /** 商品好评率 */
    @Column(name = "`grade`", columnDefinition = "decimal(10,2) not null comment '商品好评率'")
    private BigDecimal grade;

    /** 缩略图路径 */
    @Column(name = "`thumbnail`", columnDefinition = "varchar(255) not null comment '缩略图路径'")
    private String thumbnail;

    /** 大图路径 */
    @Column(name = "`big`", columnDefinition = "varchar(255) not null comment '大图路径'")
    private String big;

    /** 小图路径 */
    @Column(name = "`small`", columnDefinition = "varchar(255) not null comment '小图路径'")
    private String small;

    /** 原图路径 */
    @Column(name = "`original`", columnDefinition = "varchar(255) not null comment '原图路径'")
    private String original;

    /** 店铺分类路径 */
    @Column(
            name = "`store_category_path`",
            columnDefinition = "varchar(255) not null comment '店铺分类路径'")
    private String storeCategoryPath;

    /** 评论数量 */
    @Column(name = "`comment_num`", columnDefinition = "int default 0 comment '评论数量'")
    private Integer commentNum;

    /** 卖家id */
    @Column(name = "`store_id`", columnDefinition = "bigint not null comment '卖家id'")
    private Long storeId;

    /** 卖家名字 */
    @Column(name = "`store_name`", columnDefinition = "varchar(255) not null comment '卖家名字'")
    private String storeName;

    /** 运费模板id */
    @Column(name = "`template_id`", columnDefinition = "bigint not null comment '运费模板id'")
    private Long templateId;

    /** 是否自营 */
    @Column(
            name = "`self_operated`",
            columnDefinition = "boolean null default false comment '是否自营'")
    private Boolean selfOperated;

    /** 商品视频 */
    @Column(name = "`goods_video`", columnDefinition = "varchar(255) not null comment '商品视频'")
    private String goodsVideo;

    /** 是否为推荐商品 */
    @Column(name = "`recommend`", columnDefinition = "boolean null default false comment '是否为推荐商品'")
    private Boolean recommend;

    /** 销售模式 */
    @Column(name = "`sales_model`", columnDefinition = "varchar(255) not null comment '销售模式'")
    private String salesModel;

    /**
     * 草稿商品保存类型
     *
     * @see DraftGoodsSaveTypeEnum
     */
    @Column(name = "`save_type`", columnDefinition = "varchar(255) not null comment '草稿商品保存类型'")
    private String saveType;

    /** 分类名称JSON */
    @Column(name = "`category_name_json`", columnDefinition = "json not null comment '分类名称JSON'")
    private String categoryNameJson;

    /** 商品参数JSON */
    @Column(
            name = "`goods_params_list_json`",
            columnDefinition = "json not null comment '商品参数JSON'")
    private String goodsParamsListJson;

    /** 商品图片JSON */
    @Column(
            name = "`goods_gallery_list_json`",
            columnDefinition = "json not null comment '商品图片JSON'")
    private String goodsGalleryListJson;

    /** sku列表JSON */
    @Column(name = "`sku_list_json`", columnDefinition = "json not null comment 'sku列表JSON'")
    private String skuListJson;

    /**
     * 商品类型
     *
     * @see GoodsTypeEnum
     */
    @Column(name = "`goods_type`", columnDefinition = "varchar(255) not null comment '商品类型'")
    private String goodsType;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
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

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setMobileIntro(String mobileIntro) {
        this.mobileIntro = mobileIntro;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getEnableQuantity() {
        return enableQuantity;
    }

    public void setEnableQuantity(Integer enableQuantity) {
        this.enableQuantity = enableQuantity;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getStoreCategoryPath() {
        return storeCategoryPath;
    }

    public void setStoreCategoryPath(String storeCategoryPath) {
        this.storeCategoryPath = storeCategoryPath;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
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

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Boolean getSelfOperated() {
        return selfOperated;
    }

    public void setSelfOperated(Boolean selfOperated) {
        this.selfOperated = selfOperated;
    }

    public String getGoodsVideo() {
        return goodsVideo;
    }

    public void setGoodsVideo(String goodsVideo) {
        this.goodsVideo = goodsVideo;
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

    public String getSaveType() {
        return saveType;
    }

    public void setSaveType(String saveType) {
        this.saveType = saveType;
    }

    public String getCategoryNameJson() {
        return categoryNameJson;
    }

    public void setCategoryNameJson(String categoryNameJson) {
        this.categoryNameJson = categoryNameJson;
    }

    public String getGoodsParamsListJson() {
        return goodsParamsListJson;
    }

    public void setGoodsParamsListJson(String goodsParamsListJson) {
        this.goodsParamsListJson = goodsParamsListJson;
    }

    public String getGoodsGalleryListJson() {
        return goodsGalleryListJson;
    }

    public void setGoodsGalleryListJson(String goodsGalleryListJson) {
        this.goodsGalleryListJson = goodsGalleryListJson;
    }

    public String getSkuListJson() {
        return skuListJson;
    }

    public void setSkuListJson(String skuListJson) {
        this.skuListJson = skuListJson;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getIntro() {
        if (CharSequenceUtil.isNotEmpty(intro)) {
            return HtmlUtil.unescape(intro);
        }
        return intro;
    }

    public String getMobileIntro() {
        if (CharSequenceUtil.isNotEmpty(mobileIntro)) {
            return HtmlUtil.unescape(mobileIntro);
        }
        return mobileIntro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        DraftGoodsPO that = (DraftGoodsPO) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
