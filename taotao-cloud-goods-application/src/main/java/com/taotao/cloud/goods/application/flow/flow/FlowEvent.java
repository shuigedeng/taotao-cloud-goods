package com.taotao.cloud.goods.application.flow.flow;

import lombok.Data;

@Data
public class FlowEvent<T extends FlowEventInfo> {
    /**
     * FlowEventInfo
     */
    private T flowEventInfo;
    /**
     * Flow
     */
    private Flow flow;
}
