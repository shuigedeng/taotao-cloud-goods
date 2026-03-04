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

import com.taotao.boot.common.model.ddd.types.MarkerResult;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;

/**
 * 商品关联参数的CO
 *
 * @param isIndex @Schema(description = "参数组id") private String groupId;
 */
@RecordBuilder
public record GoodsParamsResult(@Schema(description = "1 输入项   2 选择项") Integer paramType,
								@Schema(description = " 选择项的内容获取值，使用optionList") String options,
								@Schema(description = "是否必填是  1    否   0") Integer required,
								@Schema(description = "是否可索引  1 可以   0不可以") Integer isIndex,
								String[] optionList) implements MarkerResult {

	@Serial
	private static final long serialVersionUID = -4904700751774005326L;

	//    public void setOptionList(String[] optionList) {
//        this.optionList = optionList;
//    }
//
//    public String[] getOptionList() {
//        if (options != null) {
//            return options.replaceAll("\r|\n", "").split(",");
//        }
//        return optionList;
//    }
}
