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

import com.taotao.boot.common.model.request.IdCommand;
import com.taotao.boot.common.model.request.IdQuery;
import com.taotao.boot.common.model.request.IdsCommand;
import com.taotao.boot.common.model.result.EmptyResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.security.spring.support.utils.SecurityUtils;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.own.store.command.StoreGoodsLabelAddCommand;
import com.taotao.cloud.goods.application.dto.own.store.command.StoreGoodsLabelEditCommand;
import com.taotao.cloud.goods.application.dto.own.store.result.StoreGoodsLabelInfoResult;
import com.taotao.cloud.goods.application.dto.own.store.result.StoreGoodsLabelResult;
import com.taotao.cloud.goods.application.service.command.StoreGoodsLabelCommandService;
import com.taotao.cloud.goods.application.service.query.StoreGoodsLabelQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商户端,店铺分类接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-14 21:49:55
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "商户端-店铺分类API", description = "商户端-店铺分类API")
@RequestMapping("/seller/goods/label")
public class GoodsLabelSellerController extends BusinessController {

	/**
	 * 店铺分类服务
	 */
	private final StoreGoodsLabelQueryService storeGoodsLabelQueryService;

	private final StoreGoodsLabelCommandService storeGoodsLabelCommandService;

	@Operation(summary = "获取当前店铺商品分类列表", description = "获取当前店铺商品分类列表")
	@RequestLogger("获取当前店铺商品分类列表")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/query/list")
	public Result<List<StoreGoodsLabelResult>> queryList() {
		Long storeId = SecurityUtils.getCurrentUser().getStoreId();
		return Result.success(storeGoodsLabelQueryService.listByStoreId(storeId));
	}

	@Operation(summary = "获取店铺商品分类详情", description = "获取店铺商品分类详情")
	@RequestLogger("获取店铺商品分类详情")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/query")
	public Result<StoreGoodsLabelInfoResult> getStoreGoodsLabel( IdQuery id ) {
//		StoreGoodsLabelPO storeGoodsLabel = storeGoodsLabelQueryService.getById(id);
//		return Result.success(GoodsLabelStoreAssembler.INSTANCE.convert(storeGoodsLabel));
		return null;
	}

	@Operation(summary = "添加店铺商品分类", description = "添加店铺商品分类")
	@RequestLogger("添加店铺商品分类")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/add")
	public Result<EmptyResult> add( @RequestBody StoreGoodsLabelAddCommand storeGoodsLabelDTO ) {
//		StoreGoodsLabelPO storeGoodsLabel =
//			GoodsLabelStoreAssembler.INSTANCE.convert(storeGoodsLabelDTO);
//		return Result.success(storeGoodsLabelCommandService.addStoreGoodsLabel(storeGoodsLabel));
		return Result.empty();
	}

	@Operation(summary = "修改店铺商品分类", description = "修改店铺商品分类")
	@RequestLogger("修改店铺商品分类")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/edit")
	public Result<EmptyResult> edit( @RequestBody StoreGoodsLabelEditCommand storeGoodsLabelDTO ) {
//		StoreGoodsLabelPO storeGoodsLabel =
//			GoodsLabelStoreAssembler.INSTANCE.convert(storeGoodsLabelDTO);
//		storeGoodsLabel.setId(id);
//		return Result.success(storeGoodsLabelCommandService.editStoreGoodsLabel(storeGoodsLabel));
		return Result.empty();
	}

	@Operation(summary = "删除店铺商品分类", description = "删除店铺商品分类")
	@RequestLogger("删除店铺商品分类")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/del")
	public Result<EmptyResult> del( @RequestBody IdCommand id ) {
//		return Result.success(storeGoodsLabelCommandService.removeStoreGoodsLabel(id));
		return Result.empty();
	}
}
