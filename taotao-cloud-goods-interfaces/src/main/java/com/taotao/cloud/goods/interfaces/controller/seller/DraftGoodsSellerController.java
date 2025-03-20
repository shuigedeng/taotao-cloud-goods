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

import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.application.service.command.DraftGoodsCommandService;
import com.taotao.cloud.goods.application.service.query.DraftGoodsQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 店铺端,草稿商品接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-14 22:05:35
 */
@AllArgsConstructor
@Validated
@RestController
@Tag(name = "店铺端-草稿商品API", description = "店铺端-草稿商品API")
@RequestMapping("/goods/seller/draft/goods")
public class DraftGoodsSellerController extends BusinessController {

    /** 草稿商品服务 */
    private final DraftGoodsQueryService draftGoodsQueryService;

    private final DraftGoodsCommandService draftGoodsCommandService;

    // @Operation(summary = "分页获取草稿商品列表", description = "分页获取草稿商品列表")
    // @RequestLogger("分页获取草稿商品列表")
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/page")
    // public Result<PageResult<DraftGoodsCO>> getDraftGoodsByPage(DraftGoodsPageQuery draftGoodsPageQuery) {
    //    Long storeId = SecurityUtils.getCurrentUser().getStoreId();
    //    draftGoodsPageQuery.setStoreId(storeId);
    //    IPage<DraftGoods> draftGoods = draftGoodsService.draftGoodsQueryPage(draftGoodsPageQuery);
    //    return Result.success(MpUtils.convertMybatisPage(draftGoods, DraftGoodsCO.class));
    // }
    //
    // @Operation(summary = "获取草稿商品", description = "获取草稿商品")
    // @RequestLogger("获取草稿商品")
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/{id}")
    // public Result<DraftGoodsSkuParamsCO> getDraftGoods(@PathVariable Long id) {
    //    return Result.success(draftGoodsService.getDraftGoods(id));
    // }
    //
    // @Operation(summary = "保存草稿商品", description = "保存草稿商品")
    // @RequestLogger("保存草稿商品")
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping
    // public Result<Boolean> saveDraftGoods(@Validated @RequestBody DraftGoodsSkuParamsDTO draftGoodsSkuParamsDTO) {
    //    Long storeId = SecurityUtils.getCurrentUser().getStoreId();
    //    if (draftGoodsSkuParamsDTO.getStoreId() == null) {
    //        draftGoodsSkuParamsDTO.setStoreId(storeId);
    //    } else if (draftGoodsSkuParamsDTO.getStoreId() != null
    //            && !storeId.equals(draftGoodsSkuParamsDTO.getStoreId())) {
    //        throw new BusinessException(ResultEnum.USER_AUTHORITY_ERROR);
    //    }
    //    return Result.success(draftGoodsService.saveGoodsDraft(draftGoodsSkuParamsDTO));
    // }
    //
    // @Operation(summary = "删除草稿商品", description = "删除草稿商品")
    // @RequestLogger("删除草稿商品")
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @DeleteMapping(value = "/{id}")
    // public Result<Boolean> deleteDraftGoods(@PathVariable Long id) {
    //    draftGoodsService.getDraftGoods(id);
    //    return Result.success(draftGoodsService.deleteGoodsDraft(id));
    // }
}
