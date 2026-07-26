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
import com.taotao.boot.webagg.entity.BasePO;
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
import org.hibernate.Hibernate;

/** 小程序直播商品表 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = CommodityPO.TABLE_NAME,
        indexes = {
            @Index(name = "idx_create_time", columnList = "`create_time`"),
        })
@TableName(CommodityPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = CommodityPO.TABLE_NAME)
public class CommodityPO extends BasePO<CommodityPO> {

    public static final String TABLE_NAME = "ttc_commodity";

    /** 图片 */
    @Column(name = "`goods_image`", columnDefinition = "varchar(255) not null comment '图片'")
    private String goodsImage;

    /** 商品名称 */
    @Column(name = "`name`", columnDefinition = "varchar(255) not null comment '商品名称'")
    private String name;

    /**
     * 1：一口价（只需要传入price，price2不传）
     *
     * <p>2：价格区间（price字段为左边界，price2字段为右边界，price和price2必传）
     *
     * <p>3：显示折扣价（price字段为原价，price2字段为现价， price和price2必传
     */
    @Column(
            name = "price_type",
            columnDefinition =
                    "int not null comment '1：一口价（只需要传入price，price2不传）"
                            + "2：价格区间（price字段为左边界，price2字段为右边界，price和price2必传）"
                            + "3：显示折扣价（price字段为原价，price2字段为现价， price和price2必传'")
    private Integer priceType;

    /** 价格 */
    @Column(name = "`price`", columnDefinition = "decimal(10,2) not null comment '价格'")
    private BigDecimal price;

    /** 价格2 */
    @Column(name = "`price2`", columnDefinition = "decimal(10,2) not null comment '价格2'")
    private BigDecimal price2;

    /** 商品详情页的小程序路径 */
    @Column(name = "`url`", columnDefinition = "varchar(255) not null comment '商品详情页的小程序路径'")
    private String url;

    /** 微信程序直播商品ID */
    @Column(name = "`live_goods_id`", columnDefinition = "bigint not null comment '微信程序直播商品ID'")
    private Long liveGoodsId;

    /** 审核单ID */
    @Column(name = "`audit_id`", columnDefinition = "bigint not null comment '审核单ID'")
    private Long auditId;

    /** 审核状态 */
    @Column(name = "`audit_status`", columnDefinition = "varchar(255) not null comment '审核状态'")
    private String auditStatus;

    /** 店铺ID */
    @Column(name = "`store_id`", columnDefinition = "bigint not null comment '店铺ID'")
    private Long storeId;

    /** 商品ID */
    @Column(name = "`goods_id`", columnDefinition = "bigint not null comment '商品ID'")
    private Long goodsId;

    /** skuId */
    @Column(name = "`sku_id`", columnDefinition = "bigint not null comment 'skuId'")
    private Long skuId;

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getGoodsImage() {
        return goodsImage;
    }

    /**
     * 设置
     *
     * @param goodsImage goodsImage
     * @return 无返回值
     * @since 2022.03
     */

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getName() {
        return name;
    }

    /**
     * 设置
     *
     * @param name name
     * @return 无返回值
     * @since 2022.03
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */

    public Integer getPriceType() {
        return priceType;
    }

    /**
     * 设置
     *
     * @param priceType priceType
     * @return 无返回值
     * @since 2022.03
     */

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
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
     * @return BigDecimal
     * @since 2022.03
     */

    public BigDecimal getPrice2() {
        return price2;
    }

    /**
     * 设置
     *
     * @param price2 price2
     * @return 无返回值
     * @since 2022.03
     */

    public void setPrice2(BigDecimal price2) {
        this.price2 = price2;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getUrl() {
        return url;
    }

    /**
     * 设置
     *
     * @param url url
     * @return 无返回值
     * @since 2022.03
     */

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */

    public Long getLiveGoodsId() {
        return liveGoodsId;
    }

    /**
     * 设置
     *
     * @param liveGoodsId liveGoodsId
     * @return 无返回值
     * @since 2022.03
     */

    public void setLiveGoodsId(Long liveGoodsId) {
        this.liveGoodsId = liveGoodsId;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */

    public Long getAuditId() {
        return auditId;
    }

    /**
     * 设置
     *
     * @param auditId auditId
     * @return 无返回值
     * @since 2022.03
     */

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置
     *
     * @param auditStatus auditStatus
     * @return 无返回值
     * @since 2022.03
     */

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
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
     * @return 结果数量
     * @since 2022.03
     */

    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置
     *
     * @param goodsId goodsId
     * @return 无返回值
     * @since 2022.03
     */

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */

    public Long getSkuId() {
        return skuId;
    }

    /**
     * 设置
     *
     * @param skuId skuId
     * @return 无返回值
     * @since 2022.03
     */

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        CommodityPO commodityPO = (CommodityPO) o;
        return getId() != null && Objects.equals(getId(), commodityPO.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
