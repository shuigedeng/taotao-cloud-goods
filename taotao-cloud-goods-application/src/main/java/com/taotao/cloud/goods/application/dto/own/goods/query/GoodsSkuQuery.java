package com.taotao.cloud.goods.application.dto.own.goods.query;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * GoodsSkuQuery 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/31
 */
@Data
public class GoodsSkuQuery {

	private Long goodsId;

	private Long skuId;
}
