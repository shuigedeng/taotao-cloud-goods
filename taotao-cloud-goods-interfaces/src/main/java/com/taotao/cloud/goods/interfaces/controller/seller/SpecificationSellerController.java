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
 * 店铺端-规格接口接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-14 20:51:29
 */
@AllArgsConstructor
@Validated
@RestController
@Tag(name = "店铺端-规格API", description = "店铺端-规格API")
@RequestMapping("/goods/seller/goods/specification")
public class SpecificationSellerController extends BusinessController {

    /**
     * 商品规格服务
     */
    private final CategorySpecificationQueryService categorySpecificationQueryService;

    private final CategorySpecificationCommandService categorySpecificationCommandService;
    //
    //	@Operation(summary = "获取分类规格", description = "获取分类规格")
    //	@Parameters({
    //		@Parameter(name = "categoryId", required = true, description = "分类id", in = ParameterIn.PATH),
    //	})
    //	@RequestLogger
    //	@PreAuthorize("hasAuthority('dept:tree:data')")
    //	@GetMapping(value = "/{categoryId}")
    //	public Result<List<SpecificationCO>> getSpecifications(@PathVariable Long categoryId) {
    //		List<SpecificationPO> categorySpecList = categorySpecificationQueryService.getCategorySpecList(
    //			categoryId);
    //		return Result.success(SpecificationAssembler.INSTANCE.convert(categorySpecList));
    //	}
}
