package com.taotao.cloud.goods.application.flow.flow;

import lombok.Data;

@Data
public class FlowRecordInfo {
    /**
     * businessType
     */
    private String businessType;
    /**
     * businessSubType
     */
    private String businessSubType;
    /**
     * businessCode
     */
    private String businessCode;
    /**
     * recordId
     */
    private String recordId;
    /**
     * status
     */
    private String status;
    /**
     * tradeNo
     */
    private String tradeNo;
    /**
     * 分片键
     */
    private String splitNo;
}
