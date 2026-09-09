package com.taotao.cloud.goods.application.flow.flow;

import lombok.Data;

@Data
public class FlowSearchInfo {
    /**
     * rootFlow
     */
    private FlowInfo rootFlow;

    /**
     * currentFlow
     */
    private FlowInfo currentFlow;
}
