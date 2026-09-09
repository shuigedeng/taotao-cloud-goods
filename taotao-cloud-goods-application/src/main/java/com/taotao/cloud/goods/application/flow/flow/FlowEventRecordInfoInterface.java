package com.taotao.cloud.goods.application.flow.flow;

import java.util.List;
import java.util.function.Supplier;

public interface FlowEventRecordInfoInterface {

    /**
     * @param parentId 父记录ID
     * @param splitNo 分库编号
     * @return 子流程事件记录列表
     */
    List<FlowEventRecordInfo> getByParentId(String parentId, String splitNo);

    /**
     * @return 下一个记录ID
     */
    String nextRecordId();

    /**
     * @param recordId 记录ID
     * @param splitNo 分库编号
     * @return 流程事件记录
     */
    FlowEventRecordInfo getEventByRecordId(String recordId, String splitNo);

    /**
     * @param flowEventRecordInfo 流程事件记录
     * @return 是否完成成功
     */
    boolean flowComplete(FlowEventRecordInfo flowEventRecordInfo);

    /**
     * @param currentFlowEventRecordInfo 当前流程事件记录
     * @param nextFlowEventRecordInfoL 下一流程事件记录列表
     * @return 是否流转成功
     */
    boolean flow(FlowEventRecordInfo currentFlowEventRecordInfo, List<FlowEventRecordInfo> nextFlowEventRecordInfoL);

	/**
	 * @param failFlowEventRecordInfo 失败记录
	 * @param retryFlowEventRecordInfo 重试记录
	 * @return 是否重试成功
	 */
	boolean failRetry(FlowEventRecordInfo failFlowEventRecordInfo, FlowEventRecordInfo retryFlowEventRecordInfo);

	/**
	 * @param recordId 记录ID
	 * @param splitNo 分库编号
	 * @return 流程记录
	 */
	FlowRecordInfo getFlowRecord(String recordId, String splitNo);

	/**
	 * @param flowEventRecordInfo 流程事件记录
	 * @return 是否完成成功
	 */
	boolean flowEventComplete(FlowEventRecordInfo flowEventRecordInfo);

	/**
	 * 发起流程
	 */
	FlowRecordInfo launch(String recordId,
		String tradeNo,
		String businessType,
		String businessSubType,
		String businessCode,
		String flowDescStr,
		String splitNo,
		Supplier<Boolean> launchSupplier);

	/**
	 * 根据交易号获取流程事件记录列表
	 */
	List<FlowEventRecordInfo> getEventsByTradeNo(String tradeNo, String splitNo);  // 修正拼写

	/**
	 * 获取最新流程事件记录
	 */
	FlowEventRecordInfo getLatestEvent(String mainRecordId,
		String businessType,
		String businessSubType,
		String businessCode,
		String event,
		String splitNo);

	/**
	 * 流程继续开始（状态转换）
	 *
	 * @param currentStatus 当前状态
	 * @param continueStatus 继续后的状态
	 * @param recordId 记录ID
	 * @param splitNo 分库编号
	 * @return 是否成功
	 */
	boolean flowContinueStart(String currentStatus,
		String continueStatus,
		String recordId,
		String splitNo);

	/**
	 * 节点熔断
	 *
	 * @param flowEventRecordInfo 流程事件记录
	 * @return 是否熔断成功
	 */
	boolean flowBreak(FlowEventRecordInfo flowEventRecordInfo);
}
