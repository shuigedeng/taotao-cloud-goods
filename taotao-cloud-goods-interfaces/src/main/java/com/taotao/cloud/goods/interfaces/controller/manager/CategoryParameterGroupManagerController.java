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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taotao.boot.common.model.request.IdCommand;
import com.taotao.boot.common.model.result.EmptyResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.own.category.command.CategoryParameterGroupCommand;
import com.taotao.cloud.goods.application.dto.own.goods.query.CategoryIdQuery;
import com.taotao.cloud.goods.application.dto.own.parameter.result.ParameterGroupResult;
import com.taotao.cloud.goods.application.service.command.CategoryParameterGroupCommandService;
import com.taotao.cloud.goods.application.service.command.ParametersCommandService;
import com.taotao.cloud.goods.application.service.query.CategoryParameterGroupQueryService;
import com.taotao.cloud.goods.application.service.query.ParametersQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理端,分类绑定参数组接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-20 16:59:38
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "平台管理端-分类绑定参数组API", description = "平台管理端-分类绑定参数组API")
@RequestMapping("/manager/goods/category/parameters")
public class CategoryParameterGroupManagerController extends BusinessController {

	/**
	 * 商品参数组服务
	 */
	private final ParametersQueryService parametersQueryService;

	private final ParametersCommandService parametersCommandService;

	/**
	 * 分类绑定参数组服务
	 */
	private final CategoryParameterGroupQueryService categoryParameterGroupQueryService;

	private final CategoryParameterGroupCommandService categoryParameterGroupCommandService;

	@Operation(summary = "查询某分类下绑定的参数信息", description = "查询某分类下绑定的参数信息")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/categoryId")
	public Result<List<ParameterGroupResult>> getCategoryParam( CategoryIdQuery categoryIdQuery ) {
//		return Result.success(categoryParameterGroupQueryService.getCategoryParams(categoryId));
		return null;
	}

	@Operation(summary = "保存数据", description = "保存数据")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/save")
	public Result<EmptyResult> save( @RequestBody  CategoryParameterGroupCommand categoryParameterGroup ) {
//		return Result.success(categoryParameterGroupCommandService.save(categoryParameterGroup));
		return Result.empty();
	}

	@Operation(summary = "更新数据", description = "更新数据")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/update")
	public Result<EmptyResult> update( @RequestBody CategoryParameterGroupCommand categoryParameterGroup ) {
//		return
//			Result.success(categoryParameterGroupCommandService.updateById(categoryParameterGroup));
		return Result.empty();
	}

	@Operation(summary = "通过id删除参数组", description = "通过id删除参数组")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/del")
	public Result<EmptyResult> delAllByIds( @RequestBody IdCommand idCommand ) {
//		// 删除参数
//		parametersCommandService.remove(new QueryWrapper<ParametersPO>().eq("group_id", id));
//		// 删除参数组
//		return Result.success(categoryParameterGroupCommandService.removeById(id));
		return Result.empty();
	}
}
