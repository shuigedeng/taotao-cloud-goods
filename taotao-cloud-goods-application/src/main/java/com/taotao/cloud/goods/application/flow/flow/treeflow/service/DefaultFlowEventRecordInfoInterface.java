package com.taotao.cloud.goods.application.flow.flow.treeflow.service;

import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowEventRecordInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowRecordInfo;

import java.util.List;
import java.util.function.Supplier;

/**
 * DefaultFlowEventRecordInfoInterface 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/9/12
 */
public class DefaultFlowEventRecordInfoInterface implements FlowEventRecordInfoInterface{

	@Override
	public List<FlowEventRecordInfo> getByParentId( String parentId, String splitNo ) {
		return List.of();
	}

	@Override
	public String nextRecordId() {
		return "";
	}

	@Override
	public FlowEventRecordInfo getEventByRecordId( String recordId, String splitNo ) {
		return null;
	}

	@Override
	public boolean flowComplete( FlowEventRecordInfo flowEventRecordInfo ) {
		return false;
	}

	@Override
	public boolean flow( FlowEventRecordInfo currentFlowEventRecordInfo,
		List<FlowEventRecordInfo> nextFlowEventRecordInfoL ) {
		return false;
	}

	@Override
	public boolean failRetry( FlowEventRecordInfo failFlowEventRecordInfo,
		FlowEventRecordInfo retryFlowEventRecordInfo ) {
		return false;
	}

	@Override
	public FlowRecordInfo getFlowRecord( String recordId, String splitNo ) {
		return null;
	}

	@Override
	public boolean flowEventComplete( FlowEventRecordInfo flowEventRecordInfo ) {
		return false;
	}

	@Override
	public FlowRecordInfo launch( String recordId, String tradeNo, String businessType, String businessSubType,
		String businessCode, String flowDescStr, String splitNo, Supplier<Boolean> launchSupplier ) {
		return null;
	}

	@Override
	public List<FlowEventRecordInfo> getEventsByTradeNo( String tradeNo, String splitNo ) {
		return List.of();
	}

	@Override
	public FlowEventRecordInfo getLatestEvent( String mainRecordId, String businessType, String businessSubType,
		String businessCode, String event, String splitNo ) {
		return null;
	}

	@Override
	public boolean flowContinueStart( String currentStatus, String continueStatus, String recordId, String splitNo ) {
		return false;
	}

	@Override
	public boolean flowBreak( FlowEventRecordInfo flowEventRecordInfo ) {
		return false;
	}
}
