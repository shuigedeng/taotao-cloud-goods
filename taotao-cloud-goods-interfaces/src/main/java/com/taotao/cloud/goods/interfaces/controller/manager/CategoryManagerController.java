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

package com.taotao.cloud.goods.interfaces.controller.manager;

import com.taotao.boot.common.model.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.category.result.CategoryTreeResult;
import com.taotao.cloud.goods.application.dto.category.command.CategoryAddCommand;
import com.taotao.cloud.goods.application.service.command.CategoryCommandService;
import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import com.taotao.cloud.goods.application.service.query.CategoryQueryService;
import com.taotao.cloud.goods.application.service.query.GoodsQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端,商品分类接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:16:38
 */
@AllArgsConstructor
@Validated
@RestController
@Tag(name = "管理端-商品分类管理API", description = "管理端-商品分类管理API")
@RequestMapping("/goods/manager/category")
@CacheConfig(cacheNames = "category")
public class CategoryManagerController extends BusinessController {

    /** 分类服务 */
    private final CategoryQueryService categoryQueryService;

    private final CategoryCommandService categoryCommandService;

    /** 商品服务 */
    private final GoodsQueryService goodsQueryService;

    private final GoodsCommandService goodsCommandService;

    //    @Operation(summary = "查询某分类下的全部子分类列表", description = "查询某分类下的全部子分类列表")
    //    @Parameters({
    //            @Parameter(name = "parentId", required = true, description = "父ID 0-最上级id", in =
    // ParameterIn.PATH),
    //    })
    //    @RequestLogger
    //    @PreAuthorize("hasAuthority('dept:tree:data')")
    //    @GetMapping(value = "/{parentId}/children/all")
    //    public Result<List<CategoryCO>> childrenList(@PathVariable Long parentId) {
    //        List<CategoryPO> categories = this.categoryQueryService.childrenList(parentId);
    //        return Result.success(CategoryAssembler.INSTANCE.convert(categories));
    //    }

    @Operation(summary = "查询全部分类列表", description = "查询全部分类列表")
    @Parameters({
        @Parameter(
                name = "parentId",
                required = true,
                description = "父ID 0-最上级id",
                in = ParameterIn.PATH),
    })
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/children/all")
    public Result<List<CategoryTreeResult>> list() {
        return Result.success(this.categoryQueryService.listAllChildren());
    }

    @Operation(summary = "添加商品分类", description = "添加商品分类")
    @Parameters({
        @Parameter(
                name = "parentId",
                required = true,
                description = "父ID 0-最上级id",
                in = ParameterIn.PATH),
    })
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping
    public Result<Boolean> saveCategory(@Validated @RequestBody CategoryAddCommand category) {
        //// 非顶级分类
        // if (category.getParentId() != null && !Long.valueOf(0).equals(category.getParentId())) {
        //    Category parent = categoryQueryService.getById(category.getParentId());
        //    if (parent == null) {
        //        throw new BusinessException(ResultEnum.CATEGORY_PARENT_NOT_EXIST);
        //    }
        //    if (category.getLevel() >= 4) {
        //        throw new BusinessException(ResultEnum.CATEGORY_BEYOND_THREE);
        //    }
        // }
        // return Result.success(categoryCommandService.saveCategory(category));
        return Result.success(true);
    }

    @Operation(summary = "修改商品分类", description = "修改商品分类")
    @Parameters({
        @Parameter(
                name = "parentId",
                required = true,
                description = "父ID 0-最上级id",
                in = ParameterIn.PATH),
    })
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PutMapping
    public Result<Boolean> updateCategory(@Valid @RequestBody CategoryTreeResult category) {
        // CategoryPO catTemp = categoryQueryService.getById(category.getId());
        // if (catTemp == null) {
        //    throw new BusinessException(ResultEnum.CATEGORY_NOT_EXIST);
        // }
        // return Result.success(categoryCommandService.updateCategory(catTemp));
        return Result.success(true);
    }

    @Operation(summary = "通过id删除分类", description = "通过id删除分类")
    @Parameters({
        @Parameter(
                name = "parentId",
                required = true,
                description = "父ID 0-最上级id",
                in = ParameterIn.PATH),
    })
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delAllByIds(@NotBlank(message = "id不能为空") @PathVariable Long id) {
        // CategoryPO category = new CategoryPO();
        // category.setParentId(id);
        // List<CategoryPO> list = categoryQueryService.findByAllBySortOrder(category);
        // if (list != null && !list.isEmpty()) {
        //    throw new BusinessException(ResultEnum.CATEGORY_HAS_CHILDREN);
        // }
        //
        //// 查询某商品分类的商品数量
        // long count = goodsQueryService.getGoodsCountByCategory(id);
        // if (count > 0) {
        //    throw new BusinessException(ResultEnum.CATEGORY_HAS_GOODS);
        // }
        // return Result.success(categoryCommandService.delete(id));
        return Result.success(true);
    }

    //    @Operation(summary = "后台 禁用/启用 分类", description = "后台 禁用/启用 分类")
    //    @Parameters({
    //            @Parameter(name = "parentId", required = true, description = "父ID 0-最上级id", in =
    // ParameterIn.PATH),
    //    })
    //    @RequestLogger
    //    @PreAuthorize("hasAuthority('dept:tree:data')")
    //    @PutMapping(value = "/disable/{id}")
    //    public Result<Boolean> disable(@PathVariable Long id, @RequestParam Boolean
    // enableOperations) {
    //		GoodsPO category = goodsQueryService.getById(id);
    //        if (category == null) {
    //            throw new BusinessException(ResultEnum.CATEGORY_NOT_EXIST);
    //        }
    //        //return Result.success(categoryCommandService.updateCategoryStatus(id,
    // enableOperations));
    //		return null;
    //    }
}
