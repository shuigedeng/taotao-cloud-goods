package com.taotao.cloud.goods.infrastructure.adapter;

import com.taotao.cloud.goods.application.adapter.GoodsBatchPort;
import com.taotao.cloud.goods.domain.valobj.GoodsStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class GoodsBatchPortImpl implements GoodsBatchPort {
	@Override
	public void batchModifyGoodsStatus(Collection<Long> goodsIds, GoodsStatusEnum goodsStatus) {

	}
}
