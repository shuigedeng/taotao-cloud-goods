package com.taotao.cloud.goods.application.dto.own.goods.command;

import com.taotao.boot.common.model.ddd.types.Command;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

/**
 * AuthCommand 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/31
 */
@RecordBuilder
public record AuthCommand(List<Long> goodsIds, String authFlag) implements Command {


}
