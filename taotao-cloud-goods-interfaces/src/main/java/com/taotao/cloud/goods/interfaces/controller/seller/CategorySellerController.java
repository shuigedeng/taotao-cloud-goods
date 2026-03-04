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

import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.own.category.result.CategoryBrandResult;
import com.taotao.cloud.goods.application.dto.own.category.result.CategoryTreeResult;
import com.taotao.cloud.goods.application.dto.own.goods.query.CategoryIdQuery;
import com.taotao.cloud.goods.application.service.command.CategoryBrandCommandService;
import com.taotao.cloud.goods.application.service.command.CategoryCommandService;
import com.taotao.cloud.goods.application.service.query.CategoryBrandQueryService;
import com.taotao.cloud.goods.application.service.query.CategoryQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商户端,商品分类接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:17:12
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "商户端-商品分类API", description = "商户端-商品分类API")
@RequestMapping("/seller/goods/category/store")
public class CategorySellerController extends BusinessController {

	/**
	 * 分类服务
	 */
	private final CategoryQueryService categoryQueryService;

	private final CategoryCommandService categoryCommandService;

	/**
	 * 分类品牌服务
	 */
	private final CategoryBrandQueryService categoryBrandQueryService;

	private final CategoryBrandCommandService categoryBrandCommandService;

	//private final FeignStoreDetailApi storeDetailApi;

	@Operation(summary = "获取店铺经营的分类", description = "获取店铺经营的分类")
	@RequestLogger("获取店铺经营的分类")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/category/all")
	public Result<List<CategoryTreeResult>> queryCategoryAll() {
//        Long storeId = SecurityUtils.getCurrentUser().getStoreId();
//        // 获取店铺经营范围
//        String goodsManagementCategory =
//     storeDetailApi.getStoreDetailVO(storeId).getGoodsManagementCategory();
//        return
//     Result.success(this.categoryQueryService.getStoreCategory(goodsManagementCategory.split(",")));
		return null;
	}

	@Operation(summary = "获取所选分类关联的品牌信息", description = "获取所选分类关联的品牌信息")
	@RequestLogger("获取所选分类关联的品牌信息")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/categoryId/brands")
	public Result<List<CategoryBrandResult>> queryBrandsByCategoryId( CategoryIdQuery categoryIdQuery ) {
		return Result.success(this.categoryBrandQueryService.getCategoryBrandList(categoryIdQuery.categoryId()));
	}
}
