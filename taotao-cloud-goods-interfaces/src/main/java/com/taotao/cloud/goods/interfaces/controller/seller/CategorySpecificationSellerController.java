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

package com.taotao.cloud.goods.interfaces.controller.seller;

import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.service.command.CategorySpecificationCommandService;
import com.taotao.cloud.goods.application.service.query.CategorySpecificationQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 店铺端,商品分类规格接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-20 16:59:38
 */
@AllArgsConstructor
@Validated
@RestController
@Tag(name = "店铺端-商品分类规格API", description = "店铺端-商品分类规格API")
@RequestMapping("/goods/seller/category/spec")
public class CategorySpecificationSellerController extends BusinessController {

    /** 商品规格服务 */
    private final CategorySpecificationQueryService categorySpecificationQueryService;

    private final CategorySpecificationCommandService categorySpecificationCommandService;

    //    @Operation(summary = "查询某分类下绑定的规格信息", description = "查询某分类下绑定的规格信息")
    //    @RequestLogger("查询某分类下绑定的规格信息")
    //    @PreAuthorize("hasAuthority('dept:tree:data')")
    //    @GetMapping(value = "/{categoryId}")
    //    public Result<List<SpecificationCO>> getCategorySpec(@PathVariable("categoryId") Long categoryId) {
    //        List<SpecificationPO> categorySpecList =
    // categorySpecificationQueryService.getCategorySpecList(categoryId);
    //
    //        return Result.success(SpecificationAssembler.INSTANCE.convert(categorySpecList));
    //    }
}
