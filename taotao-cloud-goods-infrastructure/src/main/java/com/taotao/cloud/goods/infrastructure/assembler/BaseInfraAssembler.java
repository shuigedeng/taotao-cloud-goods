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

package com.taotao.cloud.goods.infrastructure.assembler;

import com.taotao.boot.common.model.ddd.types.MarkerAssembler;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.goods.domain.valobj.CategoryDesc;
import com.taotao.cloud.goods.domain.valobj.CategoryName;
import com.taotao.cloud.goods.domain.valobj.GoodsName;

/**
 * IGoodsSkuMapStruct
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:58:21
 */
public interface BaseInfraAssembler extends MarkerAssembler {

	default BizId toBizId( Long id ) {
		return id != null ? BizId.fromNullableValue(id) : null;
	}

	default Long toLong( BizId value ) {
		return value != null ? value.id() : null;
	}

	default String toString( GoodsName value ) {
		return value != null ? value.value() : null;
	}

	default CategoryName toCategoryName( String name ) {
		return name != null ? CategoryName.of(name) : null;
	}

	default CategoryDesc toCategoryDesc( String desc ) {
		return desc != null ? CategoryDesc.of(desc) : null;
	}
}
