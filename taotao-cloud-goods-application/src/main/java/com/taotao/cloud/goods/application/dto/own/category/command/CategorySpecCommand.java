package com.taotao.cloud.goods.application.dto.own.category.command;

import io.soabase.recordbuilder.core.RecordBuilder;

/**
 * CategorySpecCommand 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/31
 */
@RecordBuilder
public record CategorySpecCommand(
	Long categoryId, String[] categorySpecs) {

}
