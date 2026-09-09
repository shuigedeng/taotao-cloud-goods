package com.taotao.cloud.goods.application.flow.flow;

import groovy.util.logging.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class AsyncSuccessEndFlowTree extends SuccessEndFlowTree implements AsyncFlow {

    public AsyncSuccessEndFlowTree(FlowInfo name, Supplier<FlowEventRecordInfoInterface> flowEventRecordInfoInterface) {
        super(name, flowEventRecordInfoInterface);
    }

    @Override
    public String type() {
        return FlowType.ASYNC_SUCCESS_END_FLOW_TREE;
    }
}
