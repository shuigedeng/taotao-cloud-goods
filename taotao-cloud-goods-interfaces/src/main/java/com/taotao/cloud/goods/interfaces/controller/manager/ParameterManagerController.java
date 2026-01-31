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

import com.taotao.boot.common.model.request.IdCommand;
import com.taotao.boot.common.model.request.IdsCommand;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.own.store.command.ParametersAddCommand;
import com.taotao.cloud.goods.application.service.command.ParametersCommandService;
import com.taotao.cloud.goods.application.service.query.ParametersQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 管理端,分类绑定参数组管理接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-20 16:59:38
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "管理端-参数管理API", description = "管理端-参数管理API")
@RequestMapping("/manager/goods/parameters")
public class ParameterManagerController extends BusinessController {

	/**
	 * 参数服务
	 */
	private final ParametersQueryService parametersQueryService;

	private final ParametersCommandService parametersCommandService;

	@Operation(summary = "添加参数", description = "添加参数")
	@RequestLogger("添加参数添加参数")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/save")
	public Result<Boolean> save( @RequestBody ParametersAddCommand parametersDTO ) {
//		Parameters parameters = ParametersConvert.INSTANCE.convert(parametersDTO);
//		return Result.success(parametersService.save(parameters));
		return null;
	}

	@Operation(summary = "编辑参数", description = "编辑参数")
	@RequestLogger("编辑参数")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/update")
	public Result<Boolean> update( @RequestBody ParametersAddCommand parametersDTO,
		@PathVariable Long id ) {
//		Parameters parameters = ParametersConvert.INSTANCE.convert(parametersDTO);
//		parameters.setId(id);
//		return Result.success(parametersService.updateParameter(parameters));
		return null;
	}

	@Operation(summary = "根据id删除参数", description = "根据id删除参数")
	@RequestLogger("根据id删除参数")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/del")
	public Result<Boolean> delById( @RequestBody IdCommand idCommand ) {
//		return Result.success(parametersService.removeById(id));
		return null;
	}
}
