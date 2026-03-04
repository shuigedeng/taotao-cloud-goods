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

package com.taotao.cloud.goods.api.inner.dto.response;

import com.taotao.boot.common.model.ddd.types.MarkerResponse;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.io.Serial;

/**
 * 商品属性索引
 *
 * @param type 属性参数：0->规格；1->参数
 * @param nameId 属性名称
 * @param name 属性名称
 * @param valueId 属性值
 * @param value 属性值
 * @param sort 排序
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:18:03
 */
@RecordBuilder
public record EsGoodsAttributeCommandApiResponse(Integer type, String nameId, String name, String valueId, String value,
												 Integer sort)  implements MarkerResponse {

	@Serial
	private  static final long serialVersionUID = 4018042777559970062L;

}
