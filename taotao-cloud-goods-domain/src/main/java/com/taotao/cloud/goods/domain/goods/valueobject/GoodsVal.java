package com.taotao.cloud.goods.domain.goods.valueobject;

import com.taotao.boot.ddd.model.domain.ValueObject;
import com.taotao.cloud.goods.domain.category.valueobject.ContentVal;

public class GoodsVal  implements ValueObject<GoodsVal> {

	@Override
	public boolean sameValueAs(GoodsVal other) {
		return false;
	}
}
