package com.taotao.cloud.goods.application.dto.category.command;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

/**
 * DisableCommand 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/31
 */
@RecordBuilder
public record CategoryIdBrandCommand(
	Long categoryId, List<Long> brandIds) {

}
