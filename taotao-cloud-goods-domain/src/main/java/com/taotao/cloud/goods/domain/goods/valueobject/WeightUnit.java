package com.taotao.cloud.goods.domain.goods.valueobject;


import com.taotao.boot.ddd.model.domain.ValueObjectEnum;

/**
 * 重量单位
 *
 * @author luohq
 * @date 2023-01-04 16:24
 */
public enum WeightUnit implements ValueObjectEnum<String> {
    //吨
    T("t", "吨"),
    //公斤
    KG("kg", "公斤"),
    //克
    G("g", "克");

    /**
     * 单位值
     */
    private String value;

    /**
     * 单位描述
     */
    private String desc;

    WeightUnit(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    public static WeightUnit of(String value) {
        return ValueObjectEnum.of(value, WeightUnit.class);
    }


	@Override
	public boolean sameValueAs(String other) {
		return false;
	}
}
