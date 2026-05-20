package com.taotao.cloud.goods.application.dto.goods.command;

import com.taotao.boot.common.model.ddd.types.Command;
import com.taotao.cloud.goods.common.enums.GoodsAuthEnum;
import com.taotao.cloud.goods.common.enums.GoodsStatusEnum;
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
public record MarketAbleGoodsCommand(List<Long> goodsIds, GoodsStatusEnum goodsStatusEnum, String underReason) implements Command {

	public MarketAbleGoodsCommand {
		// 设置默认值
		if (goodsStatusEnum == null) {
			goodsStatusEnum = GoodsStatusEnum.UPPER; // 或其他默认枚举值
		}
		if (underReason == null || underReason.isBlank()) {
			underReason = ""; // 或默认原因文本
		}
	}

}
