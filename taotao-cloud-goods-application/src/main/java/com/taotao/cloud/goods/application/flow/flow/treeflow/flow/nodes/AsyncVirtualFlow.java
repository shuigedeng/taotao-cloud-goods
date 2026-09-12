package com.taotao.cloud.goods.application.flow.flow.treeflow.flow.nodes;

import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.AsyncFlow;
import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.FlowType;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.service.FlowEventRecordInfoInterface;
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
