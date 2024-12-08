package com.taotao.cloud.goods.domain.dept.event;


import com.taotao.boot.ddd.model.domain.DomainEvent;
import com.taotao.cloud.goods.domain.dept.entity.DeptEntity;

public class DeptRecalledEvent extends DomainEvent<Long> {

	public DeptRecalledEvent(DeptEntity message) {
	}

	@Override
	protected void generatorId() {

	}
}
