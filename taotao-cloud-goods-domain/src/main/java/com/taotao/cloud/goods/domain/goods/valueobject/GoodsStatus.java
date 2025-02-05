package com.taotao.cloud.goods.domain.goods.valueobject;


import com.taotao.boot.ddd.model.domain.ValueObjectEnum;

/**
 * 商品状态
 *
 */
public enum GoodsStatus implements ValueObjectEnum<Integer> {
    //10 - 已上架
    SHELVED(10, "已上架"),
    //20 - 已下架
    UNSHELVED(20, "已下架");

    /**
     * 状态编码
     */
    private Integer value;
    /**
     * 状态描述
     */
    private String desc;

    GoodsStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public static GoodsStatus of(Integer value) {
        return ValueObjectEnum.of(value, GoodsStatus.class);
    }


	@Override
	public boolean sameValueAs(Integer other) {
		return false;
	}
}
