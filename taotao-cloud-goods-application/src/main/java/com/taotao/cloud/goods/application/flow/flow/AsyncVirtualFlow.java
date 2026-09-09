package com.taotao.cloud.goods.application.flow.flow;

import groovy.util.logging.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class AsyncVirtualFlow extends VirtualFlow implements AsyncFlow {

    public AsyncVirtualFlow(FlowInfo name, Supplier<FlowEventRecordInfoInterface> flowEventRecordInfoService, String type) {
        super(name, flowEventRecordInfoService, type);
    }

    @Override
    public String type() {
        return FlowType.ASYNC_VIRTUAL_FLOW;
    }


}
