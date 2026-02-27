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
import com.taotao.boot.common.model.request.IdQuery;
import com.taotao.boot.common.model.request.IdsCommand;
import com.taotao.boot.common.model.result.EmptyResult;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.data.mybatis.mybatisplus.MpUtils;
import com.taotao.boot.security.spring.annotation.NotAuth;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.api.enums.GoodsAuthEnum;
import com.taotao.cloud.goods.api.enums.GoodsStatusEnum;
import com.taotao.cloud.goods.application.dto.own.goods.command.AuthCommand;
import com.taotao.cloud.goods.application.dto.own.goods.command.GoodsCreateCommand;
import com.taotao.cloud.goods.application.dto.own.goods.command.GoodsParamsAddCommand;
import com.taotao.cloud.goods.application.dto.own.goods.command.UnderCommand;
import com.taotao.cloud.goods.application.dto.own.goods.query.GoodsPageQuery;
import com.taotao.cloud.goods.application.dto.own.goods.result.GoodsResult;
import com.taotao.cloud.goods.application.dto.own.goods.result.GoodsSkuParamsResult;
import com.taotao.cloud.goods.application.dto.own.goods.result.GoodsSkuResult;
import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import com.taotao.cloud.goods.application.service.command.GoodsSkuCommandService;
import com.taotao.cloud.goods.application.service.query.GoodsQueryService;
import com.taotao.cloud.goods.application.service.query.GoodsSkuQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理端,商品管理接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-20 16:59:38
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "平台管理端-商品API", description = "平台管理端-商品API")
@RequestMapping("/manager/goods")
public class GoodsManagerController extends BusinessController {

	/**
	 * 商品服务
	 */
	private final GoodsQueryService goodsQueryService;

	private final GoodsCommandService goodsCommandService;

	/**
	 * 规格商品服务
	 */
	private final GoodsSkuQueryService goodsSkuQueryService;

	private final GoodsSkuCommandService goodsSkuCommandService;

	@Operation(summary = "分页获取商品列表", description = "分页获取商品列表")
	@RequestLogger("分页获取商品列表")
	@NotAuth
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/page")
	public Result<PageResult<GoodsResult>> getByPage( @Validated GoodsPageQuery goodsPageQuery ) {
//		IPage<GoodsPO> goodsPage = goodsQueryService.goodsQueryPage(goodsPageQuery);
//		return Result.success(MpUtils.convertMybatisPage(goodsPage, GoodsCO.class));
		return null;
	}

	@Operation(summary = "分页获取商品SKU列表", description = "分页获取商品SKU列表")
	@RequestLogger("分页获取商品SKU列表")
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/sku/page")
	public Result<PageResult<GoodsSkuResult>> getSkuByPage( GoodsPageQuery goodsPageQuery ) {
//		IPage<GoodsSkuPO> goodsSkuPage = goodsSkuQueryService.goodsSkuQueryPage(goodsPageQuery);
//		return Result.success(MpUtils.convertMybatisPage(goodsSkuPage,
//			GoodsSkuConvert.INSTANCE::convert));
		return null;
	}
	@Operation(summary = "分页获取待审核商品", description = "分页获取待审核商品")
	@RequestLogger("分页获取待审核商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/auth/page")
	public Result<PageResult<GoodsResult>> getAuthPage(  GoodsPageQuery goodsPageQuery ) {
//		goodsPageQuery.setAuthFlag(GoodsAuthEnum.TOBEAUDITED.name());
//		IPage<GoodsPO> goodsPage = goodsQueryService.goodsQueryPage(goodsPageQuery);
//		return Result.success(MpUtils.convertMybatisPage(goodsPage, GoodsCO.class));
		return null;
	}
	@Operation(summary = "管理员上架商品", description = "管理员上架商品")
	@RequestLogger("管理员上架商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/create")
	public Result<GoodsResult> create( @RequestBody GoodsCreateCommand goodsCreateCommand ) {
		return Result.success(this.goodsCommandService.createGoods(goodsCreateCommand));
	}


	@Operation(summary = "管理员下架商品", description = "管理员下架商品")
	@RequestLogger("管理员下架商品")
	@NotAuth
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/under")
	public Result<EmptyResult> underGoods( @RequestBody UnderCommand underCommand){
//		return Result.success(
//			goodsCommandService.managerUpdateGoodsMarketAble(
//				goodsIds, GoodsStatusEnum.DOWN, reason));
		return Result.empty();
	}

	@Operation(summary = "管理员下架商品xxx", description = "管理员下架商品")
	@RequestLogger("管理员下架商品xxx")
	@NotAuth
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/underxx")
	public Result<EmptyResult> underGoodsxx( @RequestBody GoodsParamsAddCommand underCommand){
//		return Result.success(
//			goodsCommandService.managerUpdateGoodsMarketAble(
//				goodsIds, GoodsStatusEnum.DOWN, reason));
		return Result.empty();
	}

	@Operation(summary = "管理员审核商品", description = "管理员审核商品")
	@RequestLogger("管理员审核商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/auth")
	public Result<EmptyResult> auth( @RequestBody AuthCommand authCommand) {
		// 校验商品是否存在
//		return Result.success(
//			goodsCommandService.auditGoods(goodsIds, GoodsAuthEnum.valueOf(authFlag)));
		return Result.empty();
	}

	@Operation(summary = "管理员上架商品", description = "管理员上架商品")
	@RequestLogger("管理员上架商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/up")
	public Result<EmptyResult> unpGoods( @RequestBody IdsCommand idsCommand ) {
		goodsCommandService.updateGoodsMarketAble(idsCommand.getIds(), GoodsStatusEnum.UPPER, "");
		return Result.empty();
	}

	@Operation(summary = "通过id获取商品详情", description = "通过id获取商品详情")
	@RequestLogger("通过id获取商品详情")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query")
	public Result<GoodsSkuParamsResult> get( IdQuery idQuery ) {
		return Result.success(goodsQueryService.getGoodsVO(idQuery.getId()));
	}
}
