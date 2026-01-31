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

import com.taotao.boot.common.model.request.IdQuery;
import com.taotao.boot.common.model.request.IdsCommand;
import com.taotao.boot.common.model.result.EmptyResult;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.dto.own.draft.command.DraftGoodsSkuParamsSaveCommand;
import com.taotao.cloud.goods.application.dto.own.draft.query.DraftGoodsPageQuery;
import com.taotao.cloud.goods.application.dto.own.draft.result.DraftGoodsResult;
import com.taotao.cloud.goods.application.dto.own.draft.result.DraftGoodsSkuParamsResult;
import com.taotao.cloud.goods.application.service.command.DraftGoodsCommandService;
import com.taotao.cloud.goods.application.service.query.DraftGoodsQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商户端,草稿商品接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-14 22:05:35
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "商户端-草稿商品API", description = "商户端-草稿商品API")
@RequestMapping("/seller/goods/draft/goods")
public class DraftGoodsSellerController extends BusinessController {

	/**
	 * 草稿商品服务
	 */
	private final DraftGoodsQueryService draftGoodsQueryService;

	private final DraftGoodsCommandService draftGoodsCommandService;

	@Operation(summary = "分页获取草稿商品列表", description = "分页获取草稿商品列表")
	@RequestLogger("分页获取草稿商品列表")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/page")
	public Result<PageResult<DraftGoodsResult>> queryPage( DraftGoodsPageQuery draftGoodsPageQuery ) {
//        Long storeId = SecurityUtils.getCurrentUser().getStoreId();
//        draftGoodsPageQuery.setStoreId(storeId);
//        IPage<DraftGoods> draftGoods = draftGoodsService.draftGoodsQueryPage(draftGoodsPageQuery);
//        return Result.success(MpUtils.convertMybatisPage(draftGoods, DraftGoodsCO.class));
		return null;
	}

	@Operation(summary = "获取草稿商品", description = "获取草稿商品")
	@RequestLogger("获取草稿商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query")
	public Result<DraftGoodsSkuParamsResult> query( IdQuery idQuery ) {
//        return Result.success(draftGoodsService.getDraftGoods(id));
		return null;
	}

	@Operation(summary = "保存草稿商品", description = "保存草稿商品")
	@RequestLogger("保存草稿商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/command/save")
	public Result<EmptyResult> save( @RequestBody DraftGoodsSkuParamsSaveCommand draftGoodsSkuParamsDTO ) {
//        Long storeId = SecurityUtils.getCurrentUser().getStoreId();
//        if (draftGoodsSkuParamsDTO.getStoreId() == null) {
//            draftGoodsSkuParamsDTO.setStoreId(storeId);
//        } else if (draftGoodsSkuParamsDTO.getStoreId() != null
//                && !storeId.equals(draftGoodsSkuParamsDTO.getStoreId())) {
//            throw new BusinessException(ResultEnum.USER_AUTHORITY_ERROR);
//        }
//        return Result.success(draftGoodsService.saveGoodsDraft(draftGoodsSkuParamsDTO));
		return Result.empty();
	}

	@Operation(summary = "删除草稿商品", description = "删除草稿商品")
	@RequestLogger("删除草稿商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/command/dels")
	public Result<EmptyResult> dels( @RequestBody IdsCommand id ) {
//        draftGoodsService.getDraftGoods(id);
//        return Result.success(draftGoodsService.deleteGoodsDraft(id));
		return Result.empty();
	}
}
