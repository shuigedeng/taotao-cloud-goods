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
@Accessors(fluent = true)
@Entity
@Table(
        name = CommodityPO.TABLE_NAME,
        uniqueConstraints = {
            @UniqueConstraint(name = "uniq_goods_no", columnNames = "goods_no"),
        },
        indexes = {
            @Index(name = "idx_create_date", columnList = "`create_date`"),
        })
@TableName(CommodityPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = CommodityPO.TABLE_NAME)
public class CommodityPO extends BaseSuperEntity<CommodityPO, Long> {

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
            columnDefinition = "int not null comment '1：一口价（只需要传入price，price2不传）"
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

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice2() {
        return price2;
    }

    public void setPrice2(BigDecimal price2) {
        this.price2 = price2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getLiveGoodsId() {
        return liveGoodsId;
    }

    public void setLiveGoodsId(Long liveGoodsId) {
        this.liveGoodsId = liveGoodsId;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSkuId() {
        return skuId;
    }

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
