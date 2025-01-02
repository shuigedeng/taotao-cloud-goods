package com.taotao.cloud.goods.domain.goods.aggregate;

import com.google.shopping.type.Price;
import com.taotao.boot.ddd.model.domain.AggregateRoot;
import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.cloud.goods.domain.goods.valueobject.GoodsName;
import com.taotao.cloud.goods.domain.goods.valueobject.GoodsSpec;
import com.taotao.cloud.goods.domain.goods.valueobject.GoodsStatus;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class GoodsAgg extends AggregateRoot<BizId> {

	/**
	 * 商品ID
	 */
	@NotNull
	private BizId id;

	/**
	 * 商品标签集合
	 */
	private Set<BizId> tagIds;

	/**
	 * 商品所属分类
	 */
	@NotNull
	private BizId categoryId;

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
	 * @param id          商品ID
	 * @param categoryId  商品所属分类ID
	 * @param goodsName   商品名称
	 * @param goodsSpec   商品描述
	 * @param goodsPrice  商品价格
	 * @param goodsStatus 商品状态
	 */
	GoodsAgg(BizId id, BizId categoryId, GoodsName goodsName, GoodsSpec goodsSpec, Price goodsPrice,
		GoodsStatus goodsStatus, Set<BizId> tagIds) {
		this.id = id;
		this.categoryId = categoryId;
		this.goodsName = goodsName;
		this.goodsSpec = goodsSpec;
		this.goodsPrice = goodsPrice;
		this.goodsStatus = goodsStatus;
		this.tagIds = tagIds;
		this.createTime = LocalDateTime.now();
		this.updateTime = this.createTime;
		this.validateSelf();
	}

	public GoodsAgg(BizId id, Set<BizId> tagIds, BizId categoryId, GoodsName goodsName,
		GoodsSpec goodsSpec, Price goodsPrice, GoodsStatus goodsStatus, LocalDateTime createTime,
		LocalDateTime updateTime) {
		this.id = id;
		this.tagIds = tagIds;
		this.categoryId = categoryId;
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
	 * @param goodsName  商品名称
	 * @param goodsSpec  商品规格
	 * @param goodsPrice 商品价格
	 */
	public void modifyBasicInfo(BizId categoryId, GoodsName goodsName, GoodsSpec goodsSpec,
		Price goodsPrice, Set<BizId> tagIds) {
		this.categoryId = categoryId;
		this.goodsName = goodsName;
		this.goodsSpec = goodsSpec;
		this.goodsPrice = goodsPrice;
		this.tagIds = tagIds;
		this.validateSelf();
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

	public Set<BizId> getTagIds() {
		return tagIds;
	}

	public BizId getCategoryId() {
		return categoryId;
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
	public boolean equals(Object o) {
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
		return "Goods{" +
			"id=" + id +
			", tags=" + tagIds +
			", categoryId=" + categoryId +
			", goodsName=" + goodsName +
			", goodsSpec=" + goodsSpec +
			", goodsPrice=" + goodsPrice +
			", goodsStatus=" + goodsStatus +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			'}';
	}
}
