package com.taotao.cloud.goods.domain.event;

import com.taotao.boot.ddd.model.domain.event.DomainEvent;
import com.taotao.boot.ddd.model.event.BaseEvent;
import com.taotao.boot.ddd.model.val.BizId;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link String}
 */
public class FreightTemplateChangedEvent extends DomainEvent<BizId> implements Serializable {
	private final BizId id;
	private final String templateId;
	private final String newTemplateId;
	private final String operatorId;
	private final GoodsAggSnapshot goodsAggSnapshot;

	public FreightTemplateChangedEvent( BizId id, String templateId, String newTemplateId, String operatorId,
		GoodsAggSnapshot goodsAggSnapshot ) {
		this.id = id;
		this.templateId = templateId;
		this.newTemplateId = newTemplateId;
		this.operatorId = operatorId;
		this.goodsAggSnapshot = goodsAggSnapshot;
	}
}
