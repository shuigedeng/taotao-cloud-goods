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

package com.taotao.cloud.goods.interfaces.controller.notify;


import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.brand.command.CreateBrandCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 回调通知端-支付宝回调接口
 * <p>
 * 提供支付宝支付回调通知的处理接口
 * </p>
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:16:20
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "回调通知端-阿里回调API", description = "回调通知端-阿里回调API")
@RequestMapping("/callback/goods/alipay")
public class AlipayNotifyController extends BusinessController {

	@Operation(summary = "阿里支付状态通知", description = "阿里支付状态通知")
	@RequestLogger
	@PostMapping("/pay")
	public Result<Void> payNotify(@Validated @RequestBody CreateBrandCommand brand) {
		return Result.success();
	}
}
