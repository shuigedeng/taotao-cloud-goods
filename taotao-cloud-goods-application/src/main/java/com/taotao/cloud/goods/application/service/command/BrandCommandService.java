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
import com.taotao.cloud.goods.application.dto.brand.cmmond.BrandAddCmd;
import com.taotao.cloud.goods.application.dto.brand.cmmond.BrandUpdateCmd;
import java.util.List;

/**
 * 商品品牌业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:59:15
 */
public interface BrandCommandService extends CommandService {

    /**
     * 删除品牌
     *
     * @param ids 品牌id
     */
    boolean deleteBrands(List<Long> ids);

    /**
     * 添加品牌
     *
     * @param brandAddCmd 品牌信息
     * @return 添加结果
     */
    boolean addBrand(BrandAddCmd brandAddCmd);

    /**
     * 更新品牌
     *
     * @param brandUpdateCmd 品牌信息
     * @return 更新结果
     */
    boolean updateBrand(BrandUpdateCmd brandUpdateCmd);

    /**
     * 更新品牌是否可用
     *
     * @param brandId 品牌ID
     * @param disable 是否不可用
     * @return 更新结果
     */
    boolean brandDisable(Long brandId, boolean disable);
}
