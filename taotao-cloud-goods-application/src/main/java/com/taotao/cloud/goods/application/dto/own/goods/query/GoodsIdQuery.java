package com.taotao.cloud.goods.application.dto.own.goods.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GoodsIdQuery {

	@Schema(description = "商品id", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull(message = "商品id不能为空")
	private Long goodsId;

}
