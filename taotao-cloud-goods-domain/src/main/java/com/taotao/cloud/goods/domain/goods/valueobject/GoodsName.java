package com.taotao.cloud.goods.domain.goods.valueobject;

import com.taotao.boot.ddd.model.domain.ValueObject;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

/**
 * 商品名称
 *
 * @author luohq
 * @date 2022-11-27 18:06
 */
public class GoodsName implements ValueObject {

    /**
     * 商品名称
     */
    @NotBlank
    @Length(min = 1, max =  120)
    private String value;

    GoodsName() {
    }

    GoodsName(String value) {
        this.value = value;
        this.validateSelf();
    }

    public static GoodsName of(String goodsName) {
        return new GoodsName(goodsName);
    }
    public String getValue() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GoodsName goodsName = (GoodsName) o;
        return Objects.equals(value, goodsName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "GoodsName{" +
                "value='" + value + '\'' +
                '}';
    }

	@Override
	public boolean sameValueAs(Object other) {
		return false;
	}
}
