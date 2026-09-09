package com.taotao.cloud.goods.application.flow.flow;

import java.util.function.Supplier;

public class AsyncSuccessFlowTree extends SuccessFlowTree implements AsyncFlow {
    /**
     * AsyncSuccessFlowTree
     *
     * @param name
     * @param flowEventRecordInfoInterface
     */
    public AsyncSuccessFlowTree(FlowInfo name, Supplier<FlowEventRecordInfoInterface> flowEventRecordInfoInterface) {
        super(name, flowEventRecordInfoInterface);
    }
}
