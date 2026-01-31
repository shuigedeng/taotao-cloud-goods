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

package com.taotao.cloud.goods.application.dto.own.goods.result;

import com.taotao.boot.ddd.model.application.dto.BaseResult;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * CustomWordsResult
 *
 * @author shuigedeng
 * @version 2026.03
 * @since 2025-12-19 09:30:45
 */
@Setter
@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class CustomWordsResult extends BaseResult {

    private Long id;

    /**
     * 分词名称
     */
    private String name;

    /**
     * 是否禁用
     */
    private Integer disabled;

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled( Integer disabled ) {
		this.disabled = disabled;
	}
}
