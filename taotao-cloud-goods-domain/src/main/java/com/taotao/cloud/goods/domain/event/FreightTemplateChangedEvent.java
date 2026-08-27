package com.taotao.cloud.goods.domain.event;

import com.taotao.boot.ddd.model.domain.event.DomainEvent;
import com.taotao.boot.ddd.model.val.BizId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link String}
 */
@Getter
@Setter
public class FreightTemplateChangedEvent extends DomainEvent<BizId> implements Serializable {
	private final BizId id;
	private final String templateId;
	private final String newTemplateId;
	private final String operatorId;
	private final GoodsAggSnapshotEvent goodsAggSnapshotEvent;

	public FreightTemplateChangedEvent( BizId id, String templateId, String newTemplateId, String operatorId,
		GoodsAggSnapshotEvent goodsAggSnapshotEvent) {
		this.id = id;
		this.templateId = templateId;
		this.newTemplateId = newTemplateId;
		this.operatorId = operatorId;
		this.goodsAggSnapshotEvent = goodsAggSnapshotEvent;
	}
}
