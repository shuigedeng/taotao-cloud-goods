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
import com.taotao.boot.common.model.ddd.query.IdQuery;
import com.taotao.boot.common.model.ddd.command.IdsCommand;
import com.taotao.boot.common.model.ddd.query.PageQuery;
import com.taotao.boot.common.model.result.EmptyResult;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.data.mybatis.mybatisplus.MpUtils;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.own.goods.command.GoodsUnitCommand;
import com.taotao.cloud.goods.application.dto.own.goods.result.GoodsUnitResult;
import com.taotao.cloud.goods.application.service.command.GoodsUnitCommandService;
import com.taotao.cloud.goods.application.service.query.GoodsUnitQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 管理端,商品计量单位接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-20 16:59:38
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "平台管理端-商品计量单位API", description = "平台管理端-商品计量单位API")
@RequestMapping("/manager/goods/goods/unit")
public class GoodsUnitManagerController extends BusinessController {

	/**
	 * 商品计量服务
	 */
	private final GoodsUnitQueryService goodsUnitQueryService;

	private final GoodsUnitCommandService goodsUnitCommandService;

	@Operation(summary = "分页获取商品计量单位", description = "分页获取商品计量单位")
	@RequestLogger("分页获取商品计量单位")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/page")
	public Result<PageResult<GoodsUnitResult>> getByPage( PageQuery pageQuery ) {
//		IPage<GoodsUnit> page = goodsUnitService.page(pageQuery.buildMpPage());
//		return Result.success(MpUtils.convertMybatisPage(page, GoodsUnit.class));
		return null;
	}

	@Operation(summary = "获取商品计量单位", description = "获取商品计量单位")
	@RequestLogger("获取商品计量单位")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/query")
	public Result<GoodsUnitResult> getById( IdQuery idQuery ) {
//		return Result.success(goodsUnitService.getById(id));
		return null;
	}

	@Operation(summary = "添加商品计量单位", description = "添加商品计量单位")
	@RequestLogger("添加商品计量单位")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/save")
	public Result<EmptyResult> save( @RequestBody GoodsUnitCommand goodsUnit ) {
//		return Result.success(goodsUnitService.save(goodsUnit));
		return Result.empty();
	}

	@Operation(summary = "编辑商品计量单位", description = "编辑商品计量单位")
	@RequestLogger("编辑商品计量单位")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/commmand/update")
	public Result<EmptyResult> update( @RequestBody GoodsUnitCommand goodsUnit ) {
//		return Result.success(goodsUnitService.updateById(goodsUnit));
		return Result.empty();
	}

	@Operation(summary = "删除商品计量单位", description = "删除商品计量单位")
	@RequestLogger("删除商品计量单位")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/commnad/dels")
	public Result<EmptyResult> dels( @RequestBody IdsCommand idsCommand ) {
//		return Result.success(goodsUnitService.removeByIds(ids));
		return Result.empty();
	}
}
