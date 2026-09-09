package com.taotao.cloud.goods.application.flow.flow;

import groovy.util.logging.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class AsyncParallelFlowTree extends ParallelFlowTree implements AsyncFlow {

    public AsyncParallelFlowTree(FlowInfo name, Supplier<FlowEventRecordInfoInterface> flowEventRecordInfoInterface) {
        super(name, flowEventRecordInfoInterface);
    }

    @Override
    public String type() {
        return FlowType.ASYNC_PARALLEL_FLOW_TREE;
    }
}
