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
import com.taotao.boot.security.spring.annotation.NotAuth;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.brand.clientobject.BrandCO;
import com.taotao.cloud.goods.application.dto.brand.cmmond.BrandAddCmd;
import com.taotao.cloud.goods.application.dto.brand.cmmond.BrandUpdateCmd;
import com.taotao.cloud.goods.application.service.command.BrandCommandService;
import com.taotao.cloud.goods.application.service.query.BrandQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 管理端-品牌管理API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:16:20
 */
@AllArgsConstructor
@Validated
@RestController
@Tag(name = "管理端-品牌管理API", description = "管理端-品牌管理API")
@RequestMapping("/goods/manager/brand")
public class BrandManagerController extends BusinessController {

    /**
     * 品牌
     */
    private final BrandCommandService brandCommandService;

    private final BrandQueryService brandQueryService;

    @Operation(summary = "通过id获取", description = "通过id获取")
    @Parameters({
        @Parameter(
                name = "parentId",
                required = true,
                description = "父ID 0-最上级id",
                in = ParameterIn.PATH),
    })
    @RequestLogger
    @NotAuth
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/{id}")
    public Result<BrandCO> getById(@NotNull(message = "id不能为空") @PathVariable Long id) {
        BrandCO brandCo = brandQueryService.getById(id);
        return Result.success(brandCo);
    }

    //    @Operation(summary = "获取所有可用品牌", description = "获取所有可用品牌")
    //    @Parameters({
    //            @Parameter(name = "parentId", required = true, description = "父ID 0-最上级id", in =
    // ParameterIn.PATH),
    //    })
    //    @RequestLogger
    //    @PreAuthorize("hasAuthority('dept:tree:data')")
    //    @GetMapping(value = "/all/available")
    //    public Result<List<BrandCO>> getAllAvailable() {
    //        List<BrandPO> list = brandQueryService.getAllAvailable();
    //        return Result.success(BrandAssembler.INSTANCE.convert(list));
    //    }

    //    @Operation(summary = "分页获取", description = "分页获取")
    //    @Parameters({
    //            @Parameter(name = "parentId", required = true, description = "父ID 0-最上级id", in =
    // ParameterIn.PATH),
    //    })
    //    @RequestLogger
    //    @PreAuthorize("hasAuthority('dept:tree:data')")
    //    @GetMapping(value = "/page")
    //    public Result<PageResult<BrandCO>> brandsQueryPage(@Validated BrandPageQry page) {
    //        IPage<BrandPO> brandPage = brandQueryService.brandsQueryPage(page);
    //        return Result.success(MpUtils.convertMybatisPage(brandPage, BrandCO.class));
    //    }

    @Operation(summary = "新增品牌", description = "新增品牌")
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
    public Result<Boolean> save(@Validated @RequestBody BrandAddCmd brand) {
        return Result.success(brandCommandService.addBrand(brand));
    }

    @Operation(summary = "更新品牌", description = "更新品牌")
    @Parameters({
        @Parameter(
                name = "parentId",
                required = true,
                description = "父ID 0-最上级id",
                in = ParameterIn.PATH),
    })
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Long id, @Validated BrandUpdateCmd brand) {
        brand.id(id);
        return Result.success(brandCommandService.updateBrand(brand));
    }

    @Operation(summary = "后台禁用品牌", description = "后台禁用品牌")
    @Parameters({
        @Parameter(
                name = "parentId",
                required = true,
                description = "父ID 0-最上级id",
                in = ParameterIn.PATH),
    })
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PutMapping(value = "/disable/{brandId}")
    public Result<Boolean> disable(@PathVariable Long brandId, @RequestParam Boolean disable) {
        return Result.success(brandCommandService.brandDisable(brandId, disable));
    }

    @Operation(summary = "批量删除", description = "批量删除")
    @Parameters({
        @Parameter(
                name = "parentId",
                required = true,
                description = "父ID 0-最上级id",
                in = ParameterIn.PATH),
    })
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @DeleteMapping(value = "/{ids}")
    public Result<Boolean> delAllByIds(@PathVariable List<Long> ids) {
        return Result.success(brandCommandService.deleteBrands(ids));
    }
}
