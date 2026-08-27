package com.taotao.cloud.goods.application.dto.goods.command;

import com.taotao.boot.common.model.ddd.types.Command;
import io.soabase.recordbuilder.core.RecordBuilder;

/**
 * AuthCommand 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/31
 */
@RecordBuilder
public record UpdateGoodsBuyCountCommand(Long goodsId, int buyCount) implements Command {


}
