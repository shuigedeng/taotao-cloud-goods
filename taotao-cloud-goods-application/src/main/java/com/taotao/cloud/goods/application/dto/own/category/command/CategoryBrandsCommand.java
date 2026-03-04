package com.taotao.cloud.goods.application.dto.own.category.command;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

/**
 * CategoryBrandsCommand 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/31
 */
@RecordBuilder
public record CategoryBrandsCommand(Long categoryId, List<Long> categoryBrands) {

}
