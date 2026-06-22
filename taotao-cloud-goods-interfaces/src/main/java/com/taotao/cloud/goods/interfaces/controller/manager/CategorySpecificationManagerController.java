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
import com.taotao.cloud.goods.application.dto.category.command.AssignCategorySpecCommand;
import com.taotao.cloud.goods.application.dto.category.query.CategoryIdQuery;
import com.taotao.cloud.goods.application.dto.specification.result.SpecificationResult;
import com.taotao.cloud.goods.application.service.command.CategorySpecificationCommandService;
import com.taotao.cloud.goods.application.service.command.SpecificationCommandService;
import com.taotao.cloud.goods.application.service.query.CategorySpecificationQueryService;
import com.taotao.cloud.goods.application.service.query.SpecificationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理端,商品分类规格接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-20 16:59:38
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "平台管理端-商品分类规格API", description = "平台管理端-商品分类规格API")
@RequestMapping("/manager/goods/category/spec")
public class CategorySpecificationManagerController extends BusinessController {

	private final CategorySpecificationQueryService categorySpecificationQueryService;

	private final CategorySpecificationCommandService categorySpecificationCommandService;

	private final SpecificationQueryService specificationQueryService;

	private final SpecificationCommandService specificationCommandService;

	@Operation(summary = "查询某分类下绑定的规格信息", description = "查询某分类下绑定的规格信息")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/category-id")
	public Result<List<SpecificationResult>> getCategorySpec( CategoryIdQuery categoryIdQuery ) {
		List<SpecificationResult> result = categorySpecificationQueryService.queryByCategoryId(categoryIdQuery.categoryId());
		return Result.success(result);
	}

	@Operation(summary = "查询某分类下绑定的规格信息,商品操作使用", description = "查询某分类下绑定的规格信息,商品操作使用")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/goods/category-id")
	public Result<List<SpecificationResult>> getSpec( CategoryIdQuery categoryIdQuery) {
//		return Result.success(specificationQueryService.list());
		return null;
	}

	@Operation(summary = "保存某分类下绑定的规格信息", description = "保存某分类下绑定的规格信息")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/create")
	public Result<Void> create( @RequestBody AssignCategorySpecCommand categorySpecs ) {
//		return Result.success(
//			specificationCommandService.saveCategoryBrand(categoryId, categorySpecs));
		return Result.success();
	}
}
