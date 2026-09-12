package com.taotao.cloud.goods.application.flow.flow.treeflow.context;

import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowEventRecordInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.channel.ManualOpResult;
import lombok.ToString;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

@ToString(callSuper = true)
public class FlowContext<I extends FlowBusinessContext> {
    /**
     * flowComplete
     */
    private boolean flowComplete = false;

    /**
     * businessCode
     */
    private String businessCode;

    /**
     * businessType
     */
    private String businessType;

    /**
     * businessSubType
     */
    private String businessSubType;

    /**
     * tradeNo
     */
    private String tradeNo;

    /**
     * mainRecordId
     */
    private String mainRecordId;

    /**
     * businessContext
     */
    private I businessContext;

	/**
	 * eventStack
	 */
	private Deque<FlowEventRecordInfo> flowEventRecordDeque = new ArrayDeque<>();

	/**
	 * flowEventRecordInfo
	 */
	private FlowEventRecordInfo flowEventRecordInfo;

	/**
	 * 人工操作结果
	 */
	protected ManualOpResult manualOpResult;

	/**
	 * 分片号
	 */
	private String splitNo;

	/**
	 * @param businessCode
	 */
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	/**
	 * @param businessType
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public void setBusinessSubType(String businessSubType) {
		this.businessSubType = businessSubType;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public void setMainRecordId(String mainRecordId) {
		this.mainRecordId = mainRecordId;
	}

	public void setBusinessContext(I businessContext) {
		this.businessContext = businessContext;
	}

	public void setFlowEventRecordDeque(Deque<FlowEventRecordInfo> flowEventRecordDeque) {
		this.flowEventRecordDeque = flowEventRecordDeque;
	}

	public void setFlowEventRecordInfo(FlowEventRecordInfo flowEventRecordInfo) {
		this.flowEventRecordInfo = flowEventRecordInfo;
	}

	public void setManual0pResult(ManualOpResult manualOpResult) {
		this.manualOpResult = manualOpResult;
	}

	public void flowComplete() {
		this.flowComplete = true;
	}

	public boolean flowCompletePredict() {
		return this.flowComplete;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public String getBusinessType() {
		return businessType;
	}

	public String getBusinessSubType() {
		return businessSubType;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public String getMainRecordId() {
		return mainRecordId;
	}

	public Deque<FlowEventRecordInfo> getFlowEventRecordDeque() {
		return flowEventRecordDeque;
	}

	public FlowEventRecordInfo getFlowEventRecordInfo() {
		return flowEventRecordInfo;
	}

	public ManualOpResult getManual0pResult() {
		return manualOpResult;
	}

	public String getSplitNo() {
		return splitNo;
	}

	public void setSplitNo(String splitNo) {
		this.splitNo = splitNo;
	}

	public I getOrSetBusinessContext( Supplier<I> set) {
		if (null == this.businessContext) {
			if (null != set) {
				this.businessContext = set.get();
			}
		}
		return this.businessContext;
	}

	public FlowContext(FlowEventRecordInfo flowEventRecordInfo) {
		this.flowEventRecordInfo = flowEventRecordInfo;
		this.mainRecordId = flowEventRecordInfo.getMainRecordId();
		this.tradeNo = flowEventRecordInfo.getTradeNo();
		this.businessType = flowEventRecordInfo.getBusinessType();
		this.businessSubType = flowEventRecordInfo.getBusinessSubType();
		this.businessCode = flowEventRecordInfo.getBusinessCode();
		this.splitNo = flowEventRecordInfo.getSplitNo();
	}
	public FlowContext(String mainRecordId, String tradeNo, String splitNo) {
		this.mainRecordId = mainRecordId;
		this.tradeNo = tradeNo;
		this.splitNo = splitNo;
	}

	private FlowContext() {
	}
	public FlowContext deepClone() {
		FlowContext clone = new FlowContext();
		clone.setBusinessCode(this.businessCode);
		clone.setTradeNo(this.tradeNo);
		clone.setBusinessType(this.businessType);
		clone.setBusinessSubType(this.businessSubType);
		clone.setMainRecordId(this.mainRecordId);  // 补充
		clone.setFlowEventRecordDeque(this.cloneFlowEventRecordDeque());
		if (null != this.flowEventRecordInfo) {
			clone.setFlowEventRecordInfo(this.flowEventRecordInfo.deepClone());
		}
		clone.splitNo = this.splitNo;
		clone.flowComplete = this.flowComplete;  // 补充
		// 业务上下文可能需要深克隆或共用
		return clone;
	}

	private Deque<FlowEventRecordInfo> cloneFlowEventRecordDeque() {
		if (null == this.flowEventRecordDeque || this.flowEventRecordDeque.isEmpty()) {
			return new ArrayDeque<>();
		}
		Deque<FlowEventRecordInfo> clone = new ArrayDeque<>();
		for (FlowEventRecordInfo eventRecordInfo : this.flowEventRecordDeque) {
			clone.push(eventRecordInfo.deepClone());
		}
		return clone;
	}

	/**
	 * 成功
	 */
	public boolean eventIsSuccess() {
		return null != this.flowEventRecordInfo && this.flowEventRecordInfo.isSuccess();
	}

	/**
	 * 失败
	 */
	public boolean eventIsFail() {
		return null != this.flowEventRecordInfo && this.flowEventRecordInfo.isFail();
	}

	/**
	 * 处理中
	 */
	public boolean eventIsProcess() {
		return null == this.flowEventRecordInfo || this.flowEventRecordInfo.isProcessing();
	}
}
