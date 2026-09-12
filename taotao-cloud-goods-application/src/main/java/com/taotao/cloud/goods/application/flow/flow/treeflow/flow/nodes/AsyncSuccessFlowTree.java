package com.taotao.cloud.goods.application.flow.flow.treeflow.flow.nodes;

import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.AsyncFlow;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.service.FlowEventRecordInfoInterface;

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
