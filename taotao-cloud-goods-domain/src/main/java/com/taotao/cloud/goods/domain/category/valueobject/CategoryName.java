package com.taotao.cloud.goods.domain.category.valueobject;

import com.taotao.boot.ddd.model.domain.ValueObject;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

/**
 * 分类名称
 *
 * @author luohq
 * @date 2023-01-04 13:21
 */
public class CategoryName implements ValueObject {

    /**
     * 分类名称
     */
    @NotBlank
    @Length(min = 1, max = 64)
    private String value;

    CategoryName() {
    }

    CategoryName(String value) {
        this.value = value;
        this.validateSelf();
    }

    public static CategoryName of(String categoryName) {
        return new CategoryName(categoryName);
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
        CategoryName that = (CategoryName) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "CategoryName{" +
                "value='" + value + '\'' +
                '}';
    }

	@Override
	public boolean sameValueAs(Object other) {
		return false;
	}
}
