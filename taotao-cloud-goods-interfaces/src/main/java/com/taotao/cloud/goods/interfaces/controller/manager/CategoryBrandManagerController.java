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

import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.own.category.result.CategoryBrandResult;
import com.taotao.cloud.goods.application.service.command.CategoryBrandCommandService;
import com.taotao.cloud.goods.application.service.query.CategoryBrandQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 管理端,分类品牌接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-20 16:59:38
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "管理端-分类品牌管理API", description = "管理端-分类品牌管理API")
@RequestMapping("/manager/goods/category/brand")
public class CategoryBrandManagerController extends BusinessController {

    /** 规格品牌管理服务 */
    private final CategoryBrandCommandService categoryBrandCommandService;

    private final CategoryBrandQueryService categoryBrandQueryService;

    @Operation(summary = "查询某分类下绑定的品牌信息", description = "查询某分类下绑定的品牌信息")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/query/categoryId")
    public Result<List<CategoryBrandResult>> queryByCategoryId(
            @NotBlank(message = "分类id不能为空") @RequestParam Long categoryId) {
        return Result.success(categoryBrandQueryService.getCategoryBrandList(categoryId));
    }

    @Operation(summary = "保存某分类下绑定的品牌信息", description = "保存某分类下绑定的品牌信息")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/command/category/brands")
    public Result<Boolean> saveCategoryBrand(
            @NotBlank(message = "分类id不能为空") @RequestParam Long categoryId,
            @NotBlank(message = "品牌id列表不能为空") @RequestParam List<Long> categoryBrands) {
        return Result.success(
                categoryBrandCommandService.saveCategoryBrandList(categoryId, categoryBrands));
    }
}
