package com.taotao.cloud.goods.application.flow.flow;

import lombok.Data;

@Data
public class FlowHandlerInfo {
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
     * flowEvent
     */
    private String flowEvent;
}
