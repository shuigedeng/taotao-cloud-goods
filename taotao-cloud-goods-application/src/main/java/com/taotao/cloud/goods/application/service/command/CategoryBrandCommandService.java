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

package com.taotao.cloud.goods.application.service.command;

import com.taotao.boot.ddd.model.application.service.CommandService;
import com.taotao.cloud.goods.application.dto.category.command.CategoryIdBrandCommand;
import com.taotao.cloud.goods.application.dto.category.command.CategoryIdCommand;

import java.util.List;

/**
 * 商品分类品牌业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:59:19
 */
public interface CategoryBrandCommandService extends CommandService {

    /**
     * 通过分类ID删除关联品牌
     *
     * @param categoryIdCommand 品牌ID
     * @return {@link boolean }
     * @since 2022-04-27 16:59:19
     */
    boolean deleteByCategoryId( CategoryIdCommand categoryIdCommand);

    /**
     * 保存分类品牌关系
     *
     * @param categoryIdBrandCommand 分类id
     * @return {@link boolean }
     * @since 2022-04-27 16:59:19
     */
    boolean saveCategoryBrandList( CategoryIdBrandCommand categoryIdBrandCommand );
}
