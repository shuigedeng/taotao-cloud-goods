package com.taotao.cloud.goods.application.flow.flow.treeflow.info;

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
