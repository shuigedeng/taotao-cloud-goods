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

package com.taotao.cloud.goods.interfaces.controller.open;

import com.taotao.boot.common.model.Result;
import com.taotao.boot.security.spring.annotation.NotAuth;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.brand.command.BrandAddCommand;
import com.taotao.cloud.goods.application.dto.brand.command.BrandUpdateCommand;
import com.taotao.cloud.goods.application.dto.brand.result.BrandResult;
import com.taotao.cloud.goods.application.service.command.BrandCommandService;
import com.taotao.cloud.goods.application.service.query.BrandQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/goods/open/zhongrui")
public class ZhongRuiNotifyController extends BusinessController {

	@Operation(summary = "阿里支付状态通知", description = "阿里支付状态通知")
	@RequestLogger
	@PostMapping("/notify")
	public Result<Boolean> notify(@Validated @RequestBody BrandAddCommand brand) {
		return Result.success();
	}
}
