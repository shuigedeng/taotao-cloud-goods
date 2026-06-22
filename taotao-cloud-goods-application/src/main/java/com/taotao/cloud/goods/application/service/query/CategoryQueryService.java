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
import com.taotao.cloud.goods.application.dto.category.result.CategoryResult;
import com.taotao.cloud.goods.application.dto.category.result.CategoryTreeResult;
import java.util.List;
import java.util.Map;

/**
 * 商品分类业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:59:28
 */
public interface CategoryQueryService extends QueryService {

    /**
     * 根据父id获取子分类列表
     *
     * @param parentId 分类父ID
     * @return 商品分类列表
     */
    List<CategoryResult> childrenList(Long parentId);

    /**
     * 获取分类
     *
     * @param id 分类id
     * @return com.taotao.cloud.goods.application.model.entity.Category
     * @author shuigedeng
     * @since 2023-12-05 15:19
     */
    //	CategoryPO queryCategoryById(String id);
    //
    //	/**
    //	 * 根据分类id集合获取所有分类根据层级排序
    //	 *
    //	 * @param ids 分类ID集合
    //	 * @return 商品分类列表
    //	 */
    //	List<CategoryPO> listByIdsOrderByLevel(List<Long> ids);

    /**
     * 根据分类id集合获取所有分类根据层级排序
     *
     * @param ids 分类ID集合
     * @return 商品分类列表
     */
    List<Map<String, Object>> queryMapsByIdsOrderByLevel(List<String> ids, String columns);

    /**
     * 获取分类树
     *
     * @return 分类树
     */
    List<CategoryTreeResult> queryCategoryTree();

    /**
     * 查询所有的分类，父子关系
     *
     * @param parentId 分类父ID
     * @return 所有的分类，父子关系
     */
    List<CategoryTreeResult> queryCategoryTreeByParentId(Long parentId);

    /**
     * 查询所有的分类，父子关系 数据库获取
     *
     * @return 所有的分类，父子关系
     */
    List<CategoryTreeResult> queryCategoryTreeResult();

    /**
     * 获取指定分类的分类名称
     *
     * @param ids 指定分类id集合
     * @return 分类名称集合
     */
    List<String> queryCategoryNameByIds(List<Long> ids);

    /**
     * 获取商品分类list
     *
     * @param category 分类
     * @return 商品分类list
     */
    //	List<CategoryPO> findByAllBySortOrder(CategorySearchQry category);

    /**
     * 获取商家经营类目
     *
     * @param categories 经营范围
     * @return 分类VO列表
     */
    List<CategoryTreeResult> queryStoreCategory(String[] categories);

    /**
     * 获取一级分类列表 用于商家入驻选择
     *
     * @return 分类列表
     */
    //	List<CategoryPO> firstCategory();

}
