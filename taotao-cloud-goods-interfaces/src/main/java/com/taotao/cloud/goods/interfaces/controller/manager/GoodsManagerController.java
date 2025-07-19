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

import com.taotao.boot.common.model.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.goods.api.enums.GoodsAuthEnum;
import com.taotao.cloud.goods.api.enums.GoodsStatusEnum;
import com.taotao.cloud.goods.application.dto.goods.clientobject.GoodsCO;
import com.taotao.cloud.goods.application.dto.goods.clientobject.GoodsSkuParamsCO;
import com.taotao.cloud.goods.application.dto.goods.cmmond.GoodsCreateCommand;
import com.taotao.cloud.goods.application.service.command.GoodsCommandService;
import com.taotao.cloud.goods.application.service.command.GoodsSkuCommandService;
import com.taotao.cloud.goods.application.service.query.GoodsQueryService;
import com.taotao.cloud.goods.application.service.query.GoodsSkuQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 管理端,商品管理接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-20 16:59:38
 */
@AllArgsConstructor
@Validated
@RestController
@Tag(name = "管理端-商品管理API", description = "管理端-商品管理API")
@RequestMapping("/goods/manager/goods")
public class GoodsManagerController extends BusinessController {

    /** 商品服务 */
    private final GoodsQueryService goodsQueryService;

    private final GoodsCommandService goodsCommandService;

    /** 规格商品服务 */
    private final GoodsSkuQueryService goodsSkuQueryService;

    private final GoodsSkuCommandService goodsSkuCommandService;

    @Operation(summary = "管理员上架商品", description = "管理员上架商品")
    @Parameters({
        @Parameter(
                name = "parentId",
                required = true,
                description = "父ID 0-最上级id",
                in = ParameterIn.PATH),
    })
    @RequestLogger("管理员上架商品")
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping
    public Result<GoodsCO> createGoods(@RequestBody GoodsCreateCommand goodsCreateCommand) {
        return Result.success(this.goodsCommandService.createGoods(goodsCreateCommand));
    }

    //    @Operation(summary = "分页获取", description = "分页获取")
    //    @Parameters({
    //            @Parameter(name = "parentId", required = true, description = "父ID 0-最上级id", in =
    // ParameterIn.PATH),
    //    })
    //    @RequestLogger("分页获取")
    //    @PreAuthorize("hasAuthority('dept:tree:data')")
    //    @GetMapping(value = "/page")
    //    public Result<PageResult<GoodsCO>> getByPage(@Validated GoodsPageQry goodsPageQuery) {
    //        IPage<GoodsPO> goodsPage = goodsQueryService.goodsQueryPage(goodsPageQuery);
    //        return Result.success(MpUtils.convertMybatisPage(goodsPage, GoodsCO.class));
    //    }

    // @Operation(summary = "分页获取商品列表", description = "分页获取商品列表")
    // @Parameters({
    //        @Parameter(name = "parentId", required = true, description = "父ID 0-最上级id", in =
    // ParameterIn.PATH),
    // })
    // @RequestLogger("分页获取商品列表")
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/sku/page")
    // public Result<PageResult<GoodsSkuCO>> getSkuByPage(@Validated GoodsPageQry goodsPageQuery) {
    //    IPage<GoodsSkuPO> goodsSkuPage = goodsSkuQueryService.goodsSkuQueryPage(goodsPageQuery);
    //    return Result.success(MpUtils.convertMybatisPage(goodsSkuPage,
    // GoodsSkuConvert.INSTANCE::convert));
    // }

    //    @Operation(summary = "分页获取待审核商品", description = "分页获取待审核商品")
    //    @Parameters({
    //            @Parameter(name = "parentId", required = true, description = "父ID 0-最上级id", in =
    // ParameterIn.PATH),
    //    })
    //    @RequestLogger("分页获取待审核商品")
    //    @PreAuthorize("hasAuthority('dept:tree:data')")
    //    @GetMapping(value = "/auth/page")
    //    public Result<PageResult<GoodsCO>> getAuthPage(@Validated GoodsPageQry goodsPageQuery) {
    //        goodsPageQuery.setAuthFlag(GoodsAuthEnum.TOBEAUDITED.name());
    //        IPage<GoodsPO> goodsPage = goodsQueryService.goodsQueryPage(goodsPageQuery);
    //        return Result.success(MpUtils.convertMybatisPage(goodsPage, GoodsCO.class));
    //    }

    @Operation(summary = "管理员下架商品", description = "管理员下架商品")
    @Parameters({
        @Parameter(
                name = "parentId",
                required = true,
                description = "父ID 0-最上级id",
                in = ParameterIn.PATH),
    })
    @RequestLogger("管理员下架商品")
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PutMapping(value = "/{goodsId}/under")
    public Result<Boolean> underGoods(
            @PathVariable Long goodsId,
            @NotEmpty(message = "下架原因不能为空") @RequestParam String reason) {
        List<Long> goodsIds = List.of(goodsId);
        return Result.success(
                goodsCommandService.managerUpdateGoodsMarketAble(
                        goodsIds, GoodsStatusEnum.DOWN, reason));
    }

    @Operation(summary = "管理员审核商品", description = "管理员审核商品")
    @Parameters({
        @Parameter(
                name = "parentId",
                required = true,
                description = "父ID 0-最上级id",
                in = ParameterIn.PATH),
    })
    @RequestLogger("管理员审核商品")
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PutMapping(value = "{goodsIds}/auth")
    public Result<Boolean> auth(@PathVariable List<Long> goodsIds, @RequestParam String authFlag) {
        // 校验商品是否存在
        return Result.success(
                goodsCommandService.auditGoods(goodsIds, GoodsAuthEnum.valueOf(authFlag)));
    }

    @Operation(summary = "管理员上架商品", description = "管理员上架商品")
    @Parameters({
        @Parameter(
                name = "parentId",
                required = true,
                description = "父ID 0-最上级id",
                in = ParameterIn.PATH),
    })
    @RequestLogger("管理员上架商品")
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PutMapping(value = "/{goodsId}/up")
    public Result<Boolean> unpGoods(@PathVariable List<Long> goodsId) {
        return Result.success(
                goodsCommandService.updateGoodsMarketAble(goodsId, GoodsStatusEnum.UPPER, ""));
    }

    @Operation(summary = "通过id获取商品详情", description = "通过id获取商品详情")
    @Parameters({
        @Parameter(
                name = "parentId",
                required = true,
                description = "父ID 0-最上级id",
                in = ParameterIn.PATH),
    })
    @RequestLogger("通过id获取商品详情")
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/{id}")
    public Result<GoodsSkuParamsCO> get(@PathVariable Long id) {
        return Result.success(goodsQueryService.getGoodsVO(id));
    }
}
