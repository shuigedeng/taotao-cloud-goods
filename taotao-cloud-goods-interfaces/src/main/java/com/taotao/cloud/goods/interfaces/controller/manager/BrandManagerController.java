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

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.taotao.boot.common.model.ddd.command.IdsCommand;
import com.taotao.boot.common.model.ddd.query.IdQuery;
import com.taotao.boot.common.model.result.EmptyResult;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.idempotent.annotation.Idempotent;
import com.taotao.boot.ratelimit.ratelimitguava.GuavaLimit;
import com.taotao.boot.ratelimit.ratelimitguava.Limit;
import com.taotao.boot.security.spring.annotation.NotAuth;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.own.brand.command.BrandAddCommand;
import com.taotao.cloud.goods.application.dto.own.brand.command.BrandUpdateCommand;
import com.taotao.cloud.goods.application.dto.own.brand.query.BrandPageQuery;
import com.taotao.cloud.goods.application.dto.own.brand.result.BrandResult;
import com.taotao.cloud.goods.application.dto.own.category.command.BrandDisableCommand;
import com.taotao.cloud.goods.application.service.command.BrandCommandService;
import com.taotao.cloud.goods.application.service.query.BrandQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 平台管理端-品牌API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:16:20
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "平台管理端-品牌API", description = "平台管理端-品牌API")
@RequestMapping("/manager/goods/brand")
public class BrandManagerController extends BusinessController {

	/**
	 * 品牌
	 */
	private final BrandCommandService brandCommandService;

	private final BrandQueryService brandQueryService;

	@Operation(summary = "通过id获取", description = "通过id获取")
	@RequestLogger
	@NotAuth
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query")
	public Result<BrandResult> queryById( IdQuery idQuery ) {
		BrandResult brandCo = brandQueryService.getById(idQuery.getId());
		return Result.success(brandCo);
	}

	@Operation(summary = "获取所有可用品牌", description = "获取所有可用品牌")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/available")
	public Result<List<BrandResult>> queryAvailable() {
//		List<BrandPO> list = brandQueryService.getAllAvailable();
//		return Result.success(BrandAssembler.INSTANCE.convert(list));
		return null;
	}

	@Operation(summary = "分页获取", description = "分页获取")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/page")
	public Result<PageResult<BrandResult>> queryPage( BrandPageQuery page ) {
//		IPage<BrandPO> brandPage = brandQueryService.brandsQueryPage(page);
//		return Result.success(MpUtils.convertMybatisPage(brandPage, BrandCO.class));
		return null;
	}

	@Operation(summary = "新增品牌", description = "新增品牌")
	@RequestLogger
	@NotAuth
	@Idempotent(perFix = "test")
	@Limit(key = "limitTest", period = 10, count = 3)
	@GuavaLimit
	@SentinelResource("test")
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/save")
	public Result<EmptyResult> save( @RequestBody BrandAddCommand brand ) {
		boolean result = brandCommandService.addBrand(brand);
		return Result.empty();
	}

	@Operation(summary = "更新品牌", description = "更新品牌")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/update")
	public Result<EmptyResult> updateById( @RequestBody BrandUpdateCommand brand ) {
		boolean result = brandCommandService.updateBrand(brand);
		return Result.empty();
	}

	@Operation(summary = "后台禁用品牌", description = "后台禁用品牌")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/disable")
	public Result<EmptyResult> disableById( @RequestBody BrandDisableCommand brandDisableCommand ) {
//		return Result.success(brandCommandService.brandDisable(brandId, disable));
		return Result.empty();
	}

	@Operation(summary = "批量删除", description = "批量删除")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/dels")
	public Result<EmptyResult> delByIds( @RequestBody IdsCommand idsCommand ) {
		brandCommandService.deleteBrands(idsCommand.getIds());
		return Result.empty();
	}
}
