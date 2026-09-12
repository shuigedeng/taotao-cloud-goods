package com.taotao.cloud.goods.application.flow.flow.treeflow.channel;

import lombok.Data;

@Data
public class ManualOpResult {  // 修正类名
    private String recordId;
    private String businessType;
    private String businessSubType;
    private String confirmType;
    private String tradeNo;
    private String splitNo;     // 补充
    private String mainRecordId; // 补充

	/**
	 * 列名：business_code
	 * 描述：业务编码
	 */
	private String businessCode;

	/**
	 * 列名：status
	 * 描述：状态(W-等待)
	 */
	private String status;

	/**
	 * @return
	 */
	public boolean rollbackSwitchOn() {
		return false;
	}

	/**
	 * @return
	 */
	public boolean retrySwitchOn() {
		return false;
	}
}
