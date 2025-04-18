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

package com.taotao.cloud.goods.application.service.query;

import com.taotao.boot.ddd.model.application.service.QueryService;
import com.taotao.cloud.goods.application.dto.brand.clientobject.BrandCO;
import java.util.List;
import java.util.Map;

/**
 * 商品品牌业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:59:15
 */
public interface BrandQueryService extends QueryService {

    //	IPage<BrandPO> brandsQueryPage(BrandPageQry page);
    //
    //	/**
    //	 * 根据条件分页获取品牌列表
    //	 *
    //	 * @param page 条件参数
    //	 * @return 品牌列表
    //	 */
    //	IPage<BrandPO> getBrandsByPage(BrandPageQry page);
    //
    //	List<BrandPO> getBrandsByCategorys(Long categoryIds);
    //
    //	List<BrandPO> getAllAvailable();
    //
    //
    //	/**
    //	 * 根据分类ID获取品牌列表
    //	 *
    //	 * @param categoryId 分类ID
    //	 * @return 品牌列表
    //	 */
    //	List<BrandPO> getBrandsByCategory(Long categoryId);

    /**
     * 根据分类ID获取品牌列表
     *
     * @param categoryIds 分类ID
     * @return 品牌列表
     */
    List<Map<String, Object>> getBrandsMapsByCategory(List<Long> categoryIds, String columns);

    BrandCO getById(Long id);
}
