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

package com.taotao.cloud.goods.application.dto.draft.query;

import com.taotao.cloud.goods.api.enums.DraftGoodsSaveTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 草稿商品搜索对象
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-14 22:07:42
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class DraftGoodsPageQry {

    @Serial
    private static final long serialVersionUID = -1057830772267228050L;

    /**
     * @see DraftGoodsSaveTypeEnum
     */
    @Schema(description = "草稿商品保存类型")
    private String saveType;

    /**
     * 获取
     * @return saveType
     */
    public String getSaveType() {
        return saveType;
    }

    /**
     * 设置
     * @param saveType
     */
    public void setSaveType(String saveType) {
        this.saveType = saveType;
    }
}
