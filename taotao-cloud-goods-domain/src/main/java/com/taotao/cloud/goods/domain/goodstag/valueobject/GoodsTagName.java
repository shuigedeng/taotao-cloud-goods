package com.taotao.cloud.goods.domain.goodstag.valueobject;

import com.taotao.boot.ddd.model.domain.ValueObject;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;

/**
 * 商品名称
 *
 */
public class GoodsTagName implements ValueObject {

    /**
     * 商品名称
     */
    @NotBlank
    @Length(min = 1, max =  120)
    private String value;

    GoodsTagName() {
    }

    GoodsTagName(String value) {
        this.value = value;
        this.validateSelf();
    }

    public static GoodsTagName of(String goodsName) {
        return new GoodsTagName(goodsName);
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
        GoodsTagName goodsName = (GoodsTagName) o;
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
