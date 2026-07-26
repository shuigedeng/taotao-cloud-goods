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

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.webagg.entity.BasePO;
import com.taotao.cloud.goods.common.enums.DraftGoodsSaveTypeEnum;
import com.taotao.cloud.goods.common.enums.GoodsStatusEnum;
import com.taotao.cloud.goods.common.enums.GoodsTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

/** 草稿商品表 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = DraftGoodsPO.TABLE_NAME,
        indexes = {
            @Index(name = "idx_create_time", columnList = "`create_time`"),
        })
@TableName(DraftGoodsPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = DraftGoodsPO.TABLE_NAME)
public class DraftGoodsPO extends BasePO<DraftGoodsPO> {

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

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置
     *
     * @param goodsName goodsName
     * @return 无返回值
     * @since 2022.03
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取
     *
     * @return BigDecimal
     * @since 2022.03
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置
     *
     * @param price price
     * @return 无返回值
     * @since 2022.03
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * 设置
     *
     * @param brandId brandId
     * @return 无返回值
     * @since 2022.03
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getCategoryPath() {
        return categoryPath;
    }

    /**
     * 设置
     *
     * @param categoryPath categoryPath
     * @return 无返回值
     * @since 2022.03
     */
    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getGoodsUnit() {
        return goodsUnit;
    }

    /**
     * 设置
     *
     * @param goodsUnit goodsUnit
     * @return 无返回值
     * @since 2022.03
     */
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getSellingPoint() {
        return sellingPoint;
    }

    /**
     * 设置
     *
     * @param sellingPoint sellingPoint
     * @return 无返回值
     * @since 2022.03
     */
    public void setSellingPoint(String sellingPoint) {
        this.sellingPoint = sellingPoint;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getMarketEnable() {
        return marketEnable;
    }

    /**
     * 设置
     *
     * @param marketEnable marketEnable
     * @return 无返回值
     * @since 2022.03
     */
    public void setMarketEnable(String marketEnable) {
        this.marketEnable = marketEnable;
    }

    /**
     * 设置
     *
     * @param intro intro
     * @return 无返回值
     * @since 2022.03
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 设置
     *
     * @param mobileIntro mobileIntro
     * @return 无返回值
     * @since 2022.03
     */
    public void setMobileIntro(String mobileIntro) {
        this.mobileIntro = mobileIntro;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getBuyCount() {
        return buyCount;
    }

    /**
     * 设置
     *
     * @param buyCount buyCount
     * @return 无返回值
     * @since 2022.03
     */
    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置
     *
     * @param quantity quantity
     * @return 无返回值
     * @since 2022.03
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getEnableQuantity() {
        return enableQuantity;
    }

    /**
     * 设置
     *
     * @param enableQuantity enableQuantity
     * @return 无返回值
     * @since 2022.03
     */
    public void setEnableQuantity(Integer enableQuantity) {
        this.enableQuantity = enableQuantity;
    }

    /**
     * 获取
     *
     * @return BigDecimal
     * @since 2022.03
     */
    public BigDecimal getGrade() {
        return grade;
    }

    /**
     * 设置
     *
     * @param grade grade
     * @return 无返回值
     * @since 2022.03
     */
    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 设置
     *
     * @param thumbnail thumbnail
     * @return 无返回值
     * @since 2022.03
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getBig() {
        return big;
    }

    /**
     * 设置
     *
     * @param big big
     * @return 无返回值
     * @since 2022.03
     */
    public void setBig(String big) {
        this.big = big;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getSmall() {
        return small;
    }

    /**
     * 设置
     *
     * @param small small
     * @return 无返回值
     * @since 2022.03
     */
    public void setSmall(String small) {
        this.small = small;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getOriginal() {
        return original;
    }

    /**
     * 设置
     *
     * @param original original
     * @return 无返回值
     * @since 2022.03
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getStoreCategoryPath() {
        return storeCategoryPath;
    }

    /**
     * 设置
     *
     * @param storeCategoryPath storeCategoryPath
     * @return 无返回值
     * @since 2022.03
     */
    public void setStoreCategoryPath(String storeCategoryPath) {
        this.storeCategoryPath = storeCategoryPath;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * 设置
     *
     * @param commentNum commentNum
     * @return 无返回值
     * @since 2022.03
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * 设置
     *
     * @param storeId storeId
     * @return 无返回值
     * @since 2022.03
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * 设置
     *
     * @param storeName storeName
     * @return 无返回值
     * @since 2022.03
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * 设置
     *
     * @param templateId templateId
     * @return 无返回值
     * @since 2022.03
     */
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    /**
     * 获取
     *
     * @return 是否成功
     * @since 2022.03
     */
    public Boolean getSelfOperated() {
        return selfOperated;
    }

    /**
     * 设置
     *
     * @param selfOperated selfOperated
     * @return 无返回值
     * @since 2022.03
     */
    public void setSelfOperated(Boolean selfOperated) {
        this.selfOperated = selfOperated;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getGoodsVideo() {
        return goodsVideo;
    }

    /**
     * 设置
     *
     * @param goodsVideo goodsVideo
     * @return 无返回值
     * @since 2022.03
     */
    public void setGoodsVideo(String goodsVideo) {
        this.goodsVideo = goodsVideo;
    }

    /**
     * 获取
     *
     * @return 是否成功
     * @since 2022.03
     */
    public Boolean getRecommend() {
        return recommend;
    }

    /**
     * 设置
     *
     * @param recommend recommend
     * @return 无返回值
     * @since 2022.03
     */
    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getSalesModel() {
        return salesModel;
    }

    /**
     * 设置
     *
     * @param salesModel salesModel
     * @return 无返回值
     * @since 2022.03
     */
    public void setSalesModel(String salesModel) {
        this.salesModel = salesModel;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getSaveType() {
        return saveType;
    }

    /**
     * 设置
     *
     * @param saveType saveType
     * @return 无返回值
     * @since 2022.03
     */
    public void setSaveType(String saveType) {
        this.saveType = saveType;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getCategoryNameJson() {
        return categoryNameJson;
    }

    /**
     * 设置
     *
     * @param categoryNameJson categoryNameJson
     * @return 无返回值
     * @since 2022.03
     */
    public void setCategoryNameJson(String categoryNameJson) {
        this.categoryNameJson = categoryNameJson;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getGoodsParamsListJson() {
        return goodsParamsListJson;
    }

    /**
     * 设置
     *
     * @param goodsParamsListJson goodsParamsListJson
     * @return 无返回值
     * @since 2022.03
     */
    public void setGoodsParamsListJson(String goodsParamsListJson) {
        this.goodsParamsListJson = goodsParamsListJson;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getGoodsGalleryListJson() {
        return goodsGalleryListJson;
    }

    /**
     * 设置
     *
     * @param goodsGalleryListJson goodsGalleryListJson
     * @return 无返回值
     * @since 2022.03
     */
    public void setGoodsGalleryListJson(String goodsGalleryListJson) {
        this.goodsGalleryListJson = goodsGalleryListJson;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getSkuListJson() {
        return skuListJson;
    }

    /**
     * 设置
     *
     * @param skuListJson skuListJson
     * @return 无返回值
     * @since 2022.03
     */
    public void setSkuListJson(String skuListJson) {
        this.skuListJson = skuListJson;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * 设置
     *
     * @param goodsType goodsType
     * @return 无返回值
     * @since 2022.03
     */
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getIntro() {
        if (CharSequenceUtil.isNotEmpty(intro)) {
            return HtmlUtil.unescape(intro);
        }
        return intro;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
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
