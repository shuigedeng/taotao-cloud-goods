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

package com.taotao.cloud.goods.interfaces.controller.buyer;

import com.taotao.boot.common.model.ddd.command.IdCommand;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.own.goods.query.EsGoodsSearchQuery;
import com.taotao.cloud.goods.application.dto.own.goods.query.GoodsOtherPageQuery;
import com.taotao.cloud.goods.application.dto.own.goods.query.GoodsSkuQuery;
import com.taotao.cloud.goods.application.dto.own.goods.query.HotwordsQuery;
import com.taotao.cloud.goods.application.dto.own.goods.result.EsGoodsRelatedResult;
import com.taotao.cloud.goods.application.dto.own.goods.result.EsGoodsResult;
import com.taotao.cloud.goods.application.dto.own.goods.result.GoodsResult;
import com.taotao.cloud.goods.application.dto.own.goods.result.GoodsSkuParamsResult;
import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import com.taotao.cloud.goods.application.service.command.GoodsSkuCommandService;
import com.taotao.cloud.goods.application.service.query.EsGoodsQueryService;
import com.taotao.cloud.goods.application.service.query.GoodsQueryService;
import com.taotao.cloud.goods.application.service.query.GoodsSkuQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 买家端,商品接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-20 16:59:38
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "买家端-商品API", description = "买家端-商品API")
@RequestMapping("/buyer/goods")
public class GoodsBuyerController extends BusinessController {

	/**
	 * 商品
	 */
	private final GoodsCommandService goodsCommandService;

	private final GoodsQueryService goodsQueryService;

	/**
	 * 商品SKU
	 */
	private final GoodsSkuCommandService goodsSkuCommandService;

	private final GoodsSkuQueryService goodsSkuQueryService;

	/**
	 * ES商品搜索
	 */
	private final EsGoodsQueryService esGoodsQueryService;

	@RequestLogger
	@Operation(summary = "通过id获取商品信息", description = "通过id获取商品信息")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query")
	public Result<GoodsSkuParamsResult> queryByGoodsId( IdCommand idCommand ) {
		GoodsSkuParamsResult result = goodsQueryService.getGoodsResult(idCommand.getId());
		return Result.success(result);
	}

	@Operation(summary = "通过skuId获取商品信息", description = "通过skuId获取商品信息")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/goodsId/skuId")
	//@PageViewPoint(type = PageViewEnum.SKU, id = "#id")
	public Result<Map<String, Object>> querySkuById( GoodsSkuQuery goodsSkuQuery) {
//		Map<String, Object> map = goodsSkuQueryService.getGoodsSkuDetail(goodsId, skuId);
//		return Result.success(map);
		return null;
	}

	@Operation(summary = "获取商品分页列表", description = "获取商品分页列表")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/query/page")
	public Result<PageResult<GoodsResult>> queryPage( GoodsOtherPageQuery goodsPageQuery ) {
		//IPage<GoodsPO> goodsPage = goodsQueryService.goodsQueryPage(goodsPageQuery);
		//return Result.success(MpUtils.convertMybatisPage(goodsPage, GoodsCO.class));
		return null;
	}

	@Operation(summary = "从ES中获取商品信息", description = "从ES中获取商品信息")
	@RequestLogger
	@GetMapping("/query/es")
	public Result<PageResult<EsGoodsResult>> queryEs( EsGoodsSearchQuery esGoodsSearchQuery ) {
//		SearchPage<EsGoodsIndex> esGoodsIndices = esGoodsQueryService.searchGoods(goodsSearchParams);
//		return Result.success(esGoodsIndices);
		return null;
	}

	@Operation(summary = "从ES中获取相关商品品牌名称，分类名称及属性", description = "从ES中获取相关商品品牌名称，分类名称及属性")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/query/es/related")
	public Result<EsGoodsRelatedResult> queryEsRelated( EsGoodsSearchQuery esGoodsSearchQuery ) {
		// pageVO.setNotConvert(true);
//		EsGoodsRelatedInfo selector = esGoodsQueryService.getSelector(esGoodsSearchQuery);
//		return Result.success(selector);
		return null;
	}

	@Operation(summary = "获取热门关键词", description = "获取热门关键词")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/query/hotwords")
	public Result<List<String>> queryHotwords( HotwordsQuery hotwordsQuery ) {
		// List<String> hotWords = esGoodsQueryService.getHotWords(count);
		return Result.success(new ArrayList<>());
	}
}
