package com.taotao.cloud.goods.application.flow.flow;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class AsyncFlowTree extends FlowTree implements AsyncFlow {
    /**
     * @param name
     * @param flowEventRecordInfoInterface
     */
    public AsyncFlowTree(FlowInfo name, Supplier<FlowEventRecordInfoInterface> flowEventRecordInfoInterface) {
        super(name, flowEventRecordInfoInterface);
    }
}
