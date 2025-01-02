package com.taotao.cloud.goods.domain.category.valueobject;

import com.taotao.boot.ddd.model.domain.ValueObject;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

/**
 * 分类描述
 *
 * @author luohq
 * @date 2023-01-04 13:21
 */
public class CategoryDesc implements ValueObject {

    /**
     * 分类描述
     */
    @Length(min = 1, max = 512)
    private String value;


    CategoryDesc() {
    }

    CategoryDesc(String value) {
        this.value = value;
        this.validateSelf();
    }

    public static CategoryDesc of(String categoryDesc) {
        return new CategoryDesc(categoryDesc);
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
        CategoryDesc that = (CategoryDesc) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "CategoryDesc{" +
                "value='" + value + '\'' +
                '}';
    }

	@Override
	public boolean sameValueAs(Object other) {
		return false;
	}
}
