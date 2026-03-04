package com.taotao.cloud.goods.application.dto.own.goods.query;

import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
@RecordBuilder
public record GoodsIdQuery(@Schema(description = "商品id") @NotNull(message = "商品id不能为空") Long goodsId) {

}
