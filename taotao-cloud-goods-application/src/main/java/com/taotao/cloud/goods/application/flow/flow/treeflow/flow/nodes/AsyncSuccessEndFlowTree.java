package com.taotao.cloud.goods.application.flow.flow.treeflow.flow.nodes;

import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.AsyncFlow;
import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.FlowType;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.service.FlowEventRecordInfoInterface;
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
