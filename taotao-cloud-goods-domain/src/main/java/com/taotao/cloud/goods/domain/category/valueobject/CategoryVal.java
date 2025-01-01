package com.taotao.cloud.goods.domain.category.valueobject;

import com.taotao.boot.ddd.model.domain.ValueObject;

public class CategoryVal implements ValueObject<CategoryVal> {

	@Override
	public boolean sameValueAs(CategoryVal other) {
		return false;
	}
}
