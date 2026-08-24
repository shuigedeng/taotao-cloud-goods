package com.taotao.cloud.goods.application.flow.precredit;

import com.taotao.cloud.goods.application.dto.goods.result.GoodsResult;
import lombok.Data;

@Data
public class PreCreditContext {
	private boolean credit;
	private GoodsResult goodsResult;
}
