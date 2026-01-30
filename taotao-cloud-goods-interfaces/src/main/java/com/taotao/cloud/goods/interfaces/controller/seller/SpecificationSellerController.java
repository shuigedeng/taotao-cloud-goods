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

import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.own.goods.query.CategoryIdQuery;
import com.taotao.cloud.goods.application.dto.own.specification.result.SpecificationResult;
import com.taotao.cloud.goods.application.service.command.CategorySpecificationCommandService;
import com.taotao.cloud.goods.application.service.query.CategorySpecificationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 店铺端-规格接口接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-14 20:51:29
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "店铺端-规格API", description = "店铺端-规格API")
@RequestMapping("/seller/goods/specification")
@RequestLogger
public class SpecificationSellerController extends BusinessController {

	/**
	 * 商品规格服务
	 */
	private final CategorySpecificationQueryService categorySpecificationQueryService;

	private final CategorySpecificationCommandService categorySpecificationCommandService;

	@Operation(summary = "获取分类规格", description = "获取分类规格")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/categoryId")
	public Result<List<SpecificationResult>> queryByCategoryId( CategoryIdQuery categoryIdQuery ) {
//		List<SpecificationPO> categorySpecList =
//			categorySpecificationQueryService.getCategorySpecList(
//				categoryId);
//		return Result.success(SpecificationAssembler.INSTANCE.convert(categorySpecList));
		return null;
	}
}
