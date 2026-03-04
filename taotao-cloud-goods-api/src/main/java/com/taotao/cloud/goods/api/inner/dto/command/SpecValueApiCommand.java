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

package com.taotao.cloud.goods.api.inner.dto.command;

import com.taotao.boot.common.model.ddd.types.Command;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 规格值
 */
@RecordBuilder
public record SpecValueApiCommand(@Schema(description = "规格项名字") String specName,
								  @Schema(description = "规格值") String specValue,
								  @Schema(description = "该规格是否有图片，1 有 0 没有") Integer specType,
								  @Schema(description = "规格的图片") List<SpecImages> specImage) implements Command {

	@Serial
	private static final long serialVersionUID = -4433579132929428572L;

	/**
	 * SpecImages
	 *
	 * @author shuigedeng
	 * @version 2026.04
	 * @since 2025-12-19 09:30:45
	 */
	@RecordBuilder
	public record SpecImages(String url, String name, String status) implements Serializable {

		@Serial
		private static final long serialVersionUID = 1816357809660916086L;

	}
}
