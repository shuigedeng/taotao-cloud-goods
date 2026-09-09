package com.taotao.cloud.goods.application.flow.flow;

import lombok.Data;

@Data
public class FlowEventInfo {
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
     * rootFlow
     */
    private String rootFlow;
    /**
     * parentFlow
     */
    private String parentFlow;
    /**
     * currentFlow
     */
    private FlowInfo currentFlow;
    /**
     * type
     */
    private String type;
}
