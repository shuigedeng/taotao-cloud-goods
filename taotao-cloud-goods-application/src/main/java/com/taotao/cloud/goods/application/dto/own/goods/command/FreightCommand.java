package com.taotao.cloud.goods.application.dto.own.goods.command;

import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * FreightCommand 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/31
 */
@RecordBuilder
public record FreightCommand(@Schema(description = "商品id") List<Long> goodsId, Long templateId) {



}
