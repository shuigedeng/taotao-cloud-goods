package com.taotao.cloud.goods.application.dto.own.goods.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * FreightCommand 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/31
 */
@Data
public class FreightCommand {
	@Schema(description = "商品id", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<Long> goodsId;

	private Long templateId;
}
