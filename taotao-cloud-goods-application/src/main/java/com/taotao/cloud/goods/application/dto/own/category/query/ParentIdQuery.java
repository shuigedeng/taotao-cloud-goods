package com.taotao.cloud.goods.application.dto.own.category.query;

import com.taotao.boot.common.model.ddd.types.Query;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * ParentIdQuery 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/31
 */
@RecordBuilder
@Schema(description = "ParentIdQuery")
public record ParentIdQuery(
	@Schema(description = "父id") @NotNull(message = "父id不能为空") Long parentId
) implements Query {

}
