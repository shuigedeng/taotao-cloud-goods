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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.boot.common.model.request.IdsCommand;
import com.taotao.boot.common.model.result.EmptyResult;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.data.mybatis.mybatisplus.MpUtils;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.own.specification.command.SpecificationAddCommand;
import com.taotao.cloud.goods.application.dto.own.specification.query.SpecificationPageQuery;
import com.taotao.cloud.goods.application.dto.own.specification.result.SpecificationResult;
import com.taotao.cloud.goods.application.service.command.SpecificationCommandService;
import com.taotao.cloud.goods.application.service.query.SpecificationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理端,商品规格接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-20 16:59:38
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "平台管理端-商品规格API", description = "平台管理端-商品规格API")
@RequestMapping("/manager/goods/spec")
public class SpecificationManagerController extends BusinessController {

	/**
	 * 商品规格服务
	 */
	private final SpecificationQueryService specificationQueryService;

	private final SpecificationCommandService specificationCommandService;

	@Operation(summary = "获取所有可用规格", description = "获取所有可用规格")
	@RequestLogger("获取所有可用规格")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/query/all")
	public Result<List<SpecificationResult>> getAll() {
//		List<SpecificationPO> specifications = specificationQueryService.list();
//		return Result.success(SpecificationAssembler.INSTANCE.convert(specifications));
		return null;
	}

	@Operation(summary = "搜索规格", description = "搜索规格")
	@RequestLogger("搜索规格")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/query/page")
	public Result<PageResult<SpecificationResult>> page( SpecificationPageQuery specificationPageQuery ) {
//		IPage<SpecificationPO> specificationPage =
//			specificationQueryService.getPage(specificationPageQuery);
//		return Result.success(MpUtils.convertMybatisPage(specificationPage,
//			SpecificationAssembler.INSTANCE::convert));
		return null;
	}

	@Operation(summary = "保存规格", description = "保存规格")
	@RequestLogger("保存规格")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/save")
	public Result<EmptyResult> save(  @RequestBody SpecificationAddCommand specificationDTO ) {
//		SpecificationPO specification = SpecificationAssembler.INSTANCE.convert(specificationDTO);
//		return Result.success(specificationCommandService.save(specification));
		return Result.empty();
	}

	@Operation(summary = "更改规格", description = "更改规格")
	@RequestLogger("更改规格")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/update")
	public Result<EmptyResult> update( @Valid @RequestBody SpecificationAddCommand specificationDTO ) {
//		SpecificationPO specification = SpecificationAssembler.INSTANCE.convert(specificationDTO);
//		specification.setId(id);
//
//		return Result.success(specificationCommandService.saveOrUpdate(specification));
		return Result.empty();
	}

	@Operation(summary = "批量删除", description = "批量删除")
	@RequestLogger("批量删除")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/dels")
	public Result<EmptyResult> dels( IdsCommand ids ) {
		specificationCommandService.deleteSpecification(ids.getIds());
		return Result.empty();
	}
}
