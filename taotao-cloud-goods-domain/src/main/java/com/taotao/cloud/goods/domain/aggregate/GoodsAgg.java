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

package com.taotao.cloud.goods.domain.aggregate;

import com.taotao.boot.ddd.model.domain.AggregateRoot;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.boot.ddd.model.val.Price;
import com.taotao.cloud.goods.domain.entity.Category;
import com.taotao.cloud.goods.domain.entity.Tag;
import com.taotao.cloud.goods.domain.event.GoodsCreateEvent;
import com.taotao.cloud.goods.domain.valobj.GoodsName;
import com.taotao.cloud.goods.domain.valobj.GoodsSpec;
import com.taotao.cloud.goods.domain.valobj.GoodsStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * GoodsAgg
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
public class GoodsAgg extends AggregateRoot<BizId> {

    /**
     * 商品ID
     */
    @NotNull
    private BizId id;

    /**
     * 商品标签集合
     */
    private List<Tag> tags;

    /**
     * 商品所属分类
     */
    private @NotNull Category category;

    /**
     * 商品名称
     */
    @NotNull
    private GoodsName goodsName;

    /**
     * 商品描述
     */
    @NotNull
    private GoodsSpec goodsSpec;

    /**
     * 商品价格
     */
    @NotNull
    private Price goodsPrice;

    /**
     * 商品状态
     */
    @NotNull
    private GoodsStatus goodsStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    private GoodsAgg() {
    }

    /**
     * 初始创建商品信息
     *
     * @param id 商品ID
     * @param category 商品所属分类ID
     * @param goodsName 商品名称
     * @param goodsSpec 商品描述
     * @param goodsPrice 商品价格
     * @param goodsStatus 商品状态
     */
    public GoodsAgg(
            BizId id,
            @NotNull Category category,
            GoodsName goodsName,
            GoodsSpec goodsSpec,
            Price goodsPrice,
            GoodsStatus goodsStatus,
            List<Tag> tagIds ) {
        this.id = id;
        this.category = category;
        this.goodsName = goodsName;
        this.goodsSpec = goodsSpec;
        this.goodsPrice = goodsPrice;
        this.goodsStatus = goodsStatus;
        this.tags = tagIds;
        this.createTime = LocalDateTime.now();
        this.updateTime = this.createTime;
        this.validateSelf();
    }

    public GoodsAgg(
            BizId id,
            List<Tag> tagIds,
            @NotNull Category category,
            GoodsName goodsName,
            GoodsSpec goodsSpec,
            Price goodsPrice,
            GoodsStatus goodsStatus,
            LocalDateTime createTime,
            LocalDateTime updateTime ) {
        this.id = id;
        this.tags = tagIds;
        this.category = category;
        this.goodsName = goodsName;
        this.goodsSpec = goodsSpec;
        this.goodsPrice = goodsPrice;
        this.goodsStatus = goodsStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.validateSelf();
    }

    /**
     * 修改基础信息
     *
     * @param categoryId 分类ID
     * @param goodsName 商品名称
     * @param goodsSpec 商品规格
     * @param goodsPrice 商品价格
     */
    public void modifyBasicInfo(
            @NotNull Category categoryId,
            GoodsName goodsName,
            GoodsSpec goodsSpec,
            Price goodsPrice,
            List<Tag> tagIds ) {
        this.category = categoryId;
        this.goodsName = goodsName;
        this.goodsSpec = goodsSpec;
        this.goodsPrice = goodsPrice;
        this.tags = tagIds;
        this.validateSelf();
    }

	public void create(){
		GoodsCreateEvent goodsCreateEvent = new GoodsCreateEvent();
		goodsCreateEvent.setName("");
		publishEvent(goodsCreateEvent);
	}

    /**
     * 上架商品
     */
    public void shelve() {
        this.goodsStatus = GoodsStatus.SHELVED;
    }

    /**
     * 下架商品
     */
    public void unshelve() {
        this.goodsStatus = GoodsStatus.UNSHELVED;
    }

    public BizId getId() {
        return id;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public @NotNull Category getCategory() {
        return category;
    }

    public GoodsName getGoodsName() {
        return goodsName;
    }

    public GoodsSpec getGoodsSpec() {
        return goodsSpec;
    }

    public Price getGoodsPrice() {
        return goodsPrice;
    }

    public GoodsStatus getGoodsStatus() {
        return goodsStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GoodsAgg goods = (GoodsAgg) o;
        return Objects.equals(id, goods.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Goods{"
                + "id="
                + id
                + ", tags="
                + tags
                + ", categoryId="
                + category
                + ", goodsName="
                + goodsName
                + ", goodsSpec="
                + goodsSpec
                + ", goodsPrice="
                + goodsPrice
                + ", goodsStatus="
                + goodsStatus
                + ", createTime="
                + createTime
                + ", updateTime="
                + updateTime
                + '}';
    }
}
