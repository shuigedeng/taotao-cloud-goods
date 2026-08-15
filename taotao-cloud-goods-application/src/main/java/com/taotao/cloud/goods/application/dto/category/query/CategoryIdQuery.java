package com.taotao.cloud.goods.application.dto.category.query;

import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * 分类IDQuery
 *
 * @return 记录
 * @since 2022.03
 */
@RecordBuilder
public record CategoryIdQuery(
	@Schema(description = "分类id")
	@NotNull(message = "分类id不能为空")
	Long categoryId) {

}

