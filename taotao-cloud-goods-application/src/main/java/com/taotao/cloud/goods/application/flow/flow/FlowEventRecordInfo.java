package com.taotao.cloud.goods.application.flow.flow;

import lombok.Data;
import lombok.ToString;

@ToString(callSuper = true)
@Data
public class FlowEventRecordInfo extends FlowEventInfo {
    /**
     * tradeNo
     */
    private String tradeNo;
    /**
     * mainRecordId
     */
    private String mainRecordId;
    /**
     * parentRecordId
     */
    private String parentRecordId;
    /**
     * recordId
     */
    private String recordId;
    /**
     * status
     */
    private String status;
    /**
     * failCode
     */
    private String failCode;
    /**
     * failMsg
     */
    private String failMsg;

	/**
	 * 扩展信息
	 */
	private String extInfo;

	/**
	 * node_status
	 */
	private String nodeStatus;

	/**
	 * node_fail_code
	 */
	private String nodeFailCode;

	/**
	 * node_fail_msg
	 */
	private String nodeFailMsg;

	/**
	 * splitNo
	 */
	private String splitNo;

	/**
	 * @return
	 */
	public FlowEventRecordInfo deepClone() {
		FlowEventRecordInfo clone = new FlowEventRecordInfo();
		clone.setTradeNo(this.tradeNo);
		clone.setParentRecordId(this.parentRecordId);
		clone.setRecordId(this.recordId);
		clone.setStatus(this.status);
		clone.setBusinessType(this.getBusinessType());
		clone.setBusinessSubType(this.getBusinessSubType());
		clone.setBusinessCode(this.getBusinessCode());
		clone.setRootFlow(this.getRootFlow());
		clone.setParentFlow(this.getParentFlow());
		clone.setCurrentFlow(this.getCurrentFlow());
		clone.setType(this.getType());
		clone.setExtInfo(this.extInfo);
		clone.setSplitNo(this.splitNo);

		return clone;
	}

	/**
	 * @return
	 */
	public boolean isComplete() {
		return BusinessFlowStatus.C.equals(this.status)
			|| BusinessFlowStatus.F.equals(this.status)
			|| BusinessFlowStatus.S.equals(this.status);
	}

	/**
	 * @return
	 */
	public boolean isProcessing() {
		return BusinessFlowStatus.P.equals(this.status);
	}

	/**
	 * @return
	 */
	public boolean isSuccess() {
		return BusinessFlowStatus.S.equals(this.status);
	}

	/**
	 * @return
	 */
	public boolean isFail() {
		return BusinessFlowStatus.F.equals(this.status);
	}
}
