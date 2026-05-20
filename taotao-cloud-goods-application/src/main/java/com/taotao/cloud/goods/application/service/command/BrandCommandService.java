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

import com.taotao.boot.common.model.ddd.command.IdsCommand;
import com.taotao.boot.ddd.model.application.service.CommandService;
import com.taotao.cloud.goods.application.dto.brand.command.CreateBrandCommand;
import com.taotao.cloud.goods.application.dto.brand.command.DisableBrandCommand;
import com.taotao.cloud.goods.application.dto.brand.command.UpdateBrandCommand;
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
     * @param idsCommand 品牌id
     */
    void deleteBrands( IdsCommand idsCommand);

    /**
     * 添加品牌
     *
     * @param createBrandCommand 品牌信息
	 */
    void createBrand( CreateBrandCommand createBrandCommand);

    /**
     * 更新品牌
     *
     * @param updateBrandCommand 品牌信息
	 */
    void updateBrand( UpdateBrandCommand updateBrandCommand);

    /**
     * 更新品牌是否可用
     *
     * @param disableBrandCommand 品牌ID
	 */
    void disableBrand( DisableBrandCommand disableBrandCommand);
}
