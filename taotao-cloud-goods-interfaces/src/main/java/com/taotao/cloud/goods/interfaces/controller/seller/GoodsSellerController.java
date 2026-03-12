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

import com.taotao.boot.common.model.ddd.command.IdsCommand;
import com.taotao.boot.common.model.result.EmptyResult;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.own.goods.command.FreightCommand;
import com.taotao.cloud.goods.application.dto.own.goods.command.GoodsOperationCommand;
import com.taotao.cloud.goods.application.dto.own.goods.command.GoodsSkuStockUpdateCommand;
import com.taotao.cloud.goods.application.dto.own.goods.query.GoodsIdQuery;
import com.taotao.cloud.goods.application.dto.own.goods.query.GoodsPageQuery;
import com.taotao.cloud.goods.application.dto.own.goods.result.GoodsResult;
import com.taotao.cloud.goods.application.dto.own.goods.result.GoodsSkuParamsResult;
import com.taotao.cloud.goods.application.dto.own.goods.result.GoodsSkuResult;
import com.taotao.cloud.goods.application.dto.own.goods.result.GoodsSkuSpecGalleryResult;
import com.taotao.cloud.goods.application.dto.own.specification.result.StockWarningResult;
import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import com.taotao.cloud.goods.application.service.command.GoodsSkuCommandService;
import com.taotao.cloud.goods.application.service.query.GoodsQueryService;
import com.taotao.cloud.goods.application.service.query.GoodsSkuQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商户端,商品接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-14 21:09:23
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "商户端-商品API", description = "商户端-商品API")
@RequestMapping("/seller/goods")
public class GoodsSellerController extends BusinessController {

	/**
	 * 商品
	 */
	private final GoodsQueryService goodsQueryService;

	private final GoodsCommandService goodsCommandService;

	/**
	 * 商品sku
	 */
	private final GoodsSkuQueryService goodsSkuQueryService;

	private final GoodsSkuCommandService goodsSkuCommandService;

	//private final FeignStoreDetailApi storeDetailApi;

	@Operation(summary = "分页获取商品列表", description = "分页获取商品列表")
	@RequestLogger("分页获取商品列表")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/query/page")
	public Result<PageResult<GoodsResult>> queryPage( GoodsPageQuery goodsPageQuery ) {
		// 当前登录商家账号
//		Long storeId = SecurityUtils.getCurrentUser().getStoreId();
//		goodsPageQuery.setStoreId(storeId);
//		IPage<Goods> goodsPage = goodsService.goodsQueryPage(goodsPageQuery);
//		return Result.success(MpUtils.convertMybatisPage(goodsPage, GoodsCO.class));
		return null;
	}

	@Operation(summary = "分页获取商品Sku列表", description = "分页获取商品Sku列表")
	@RequestLogger("分页获取商品Sku列表")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/sku/page")
	public Result<PageResult<GoodsSkuResult>> getSkuPage( GoodsPageQuery goodsPageQuery ) {
		// 当前登录商家账号
//		Long storeId = SecurityUtils.getCurrentUser().getStoreId();
//		goodsPageQuery.setStoreId(storeId);
//		IPage<GoodsSku> goodsSkuPage = goodsSkuService.goodsSkuQueryPage(goodsPageQuery);
//		return Result.success(MpUtils.convertMybatisPage(goodsSkuPage, GoodsSkuCO.class));
		return null;
	}

	@Operation(summary = "分页获取库存告警商品列表", description = "分页获取库存告警商品列表")
	@RequestLogger("分页获取库存告警商品列表")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/stock/warning")
	public Result<StockWarningResult> getWarningStock( GoodsPageQuery goodsPageQuery ) {
		// 当前登录商家账号
//		Long storeId = SecurityUtils.getCurrentUser().getStoreId();
//		StoreDetailCO storeDetail = storeDetailApi.getStoreDetailVO(storeId);
//		// 库存预警数量
//		Integer stockWarnNum = storeDetail.getStockWarning();
//		goodsPageQuery.setStoreId(storeId);
//		goodsPageQuery.setLeQuantity(stockWarnNum);
//		goodsPageQuery.setMarketEnable(GoodsStatusEnum.UPPER.name());
//		// 商品SKU列表
//		IPage<GoodsSku> goodsSkuPage = goodsSkuService.goodsSkuQueryPage(goodsPageQuery);
//		StockWarningCO stockWarning =
//			new StockWarningCO(stockWarnNum, MpUtils.convertMybatisPage(goodsSkuPage,
//				GoodsSkuCO.class));
//		return Result.success(stockWarning);
		return null;
	}

	@Operation(summary = "通过id获取", description = "通过id获取")
	@RequestLogger("通过id获取")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/goodsId")
	public Result<GoodsSkuParamsResult> queryByGoodsId( GoodsIdQuery goodsIdQuery ) {
//		return Result.success(goodsService.getGoodsVO(goodsId));
		return null;
	}

	@Operation(summary = "新增商品", description = "新增商品")
	@RequestLogger("新增商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/save")
	public Result<EmptyResult> save( @RequestBody GoodsOperationCommand goodsOperationDTO ) {
//		return Result.success(goodsService.addGoods(goodsOperationDTO));
		return Result.empty();
	}

	@Operation(summary = "修改商品", description = "修改商品")
	@RequestLogger("修改商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/update")
	public Result<EmptyResult> update( @RequestBody GoodsOperationCommand goodsOperationDTO ) {
//		return Result.success(goodsService.editGoods(goodsOperationDTO, goodsId));
		return Result.empty();
	}

	@Operation(summary = "下架商品", description = "下架商品")
	@RequestLogger("下架商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/under")
	public Result<EmptyResult> under( @RequestBody IdsCommand idsCommand ) {
//		return Result.success(goodsService.updateGoodsMarketAble(goodsId, GoodsStatusEnum.DOWN,
//			"商家下架"));
		return Result.empty();
	}

	@Operation(summary = "上架商品", description = "上架商品")
	@RequestLogger("上架商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/up")
	public Result<EmptyResult> up( @RequestBody IdsCommand idsCommand ) {
//		return Result.success(goodsService.updateGoodsMarketAble(goodsId, GoodsStatusEnum.UPPER,
//			""));
		return Result.empty();
	}

	@Operation(summary = "删除商品", description = "删除商品")
	@RequestLogger("删除商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/dels")
	public Result<EmptyResult> dels( @RequestBody IdsCommand idsCommand) {
//		return Result.success(goodsService.deleteGoods(goodsIds));
		return Result.empty();
	}

	@Operation(summary = "设置商品运费模板", description = "设置商品运费模板")
	@RequestLogger("设置商品运费模板")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/freight")
	public Result<EmptyResult> freight( @RequestBody FreightCommand freightCommand) {
//		return Result.success(goodsService.freight(goodsId, templateId));
		return Result.empty();
	}

	@Operation(summary = "根据goodsId分页获取商品规格列表", description = "根据goodsId分页获取商品规格列表")
	@RequestLogger("根据goodsId分页获取商品规格列表")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/sku/list")
	public Result<List<GoodsSkuSpecGalleryResult>> getSkuByList( GoodsIdQuery goodsIdQuery ) {
//		Long storeId = SecurityUtils.getCurrentUser().getStoreId();
//		return Result.success(goodsSkuService.getGoodsSkuVOList(goodsSkuService.list(new
//			LambdaQueryWrapper<GoodsSku>()
//			.eq(GoodsSku::getGoodsId, goodsId)
//			.eq(GoodsSku::getStoreId, storeId))));
		return null;
	}

	@Operation(summary = "修改商品库存", description = "修改商品库存")
	@RequestLogger("修改商品库存")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/update/stocks")
	public Result<EmptyResult> updateStocks( @RequestBody List<GoodsSkuStockUpdateCommand> updateStockList ) {
//		Long storeId = SecurityUtils.getCurrentUser().getStoreId();
//		// 获取商品skuId集合
//		List<Long> goodsSkuIds =
//			updateStockList.stream().map(GoodsSkuStockDTO::getSkuId).toList();
//		// 根据skuId集合查询商品信息
//		List<GoodsSku> goodsSkuList = goodsSkuService.list(new LambdaQueryWrapper<GoodsSku>()
//			.in(GoodsSku::getId, goodsSkuIds)
//			.eq(GoodsSku::getStoreId, storeId));
//		// 过滤不符合当前店铺的商品
//		List<Long> filterGoodsSkuIds =
//			goodsSkuList.stream().map(GoodsSku::getId).toList();
//		List<GoodsSkuStockDTO> collect = updateStockList.stream()
//			.filter(i -> filterGoodsSkuIds.contains(i.getSkuId()))
//			.toList();
//		return Result.success(goodsSkuService.updateStocks(collect));
		return Result.empty();
	}
}
