package com.taotao.cloud.goods.application.flow.flow.treeflow.flow.nodes;

import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.AsyncFlow;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.service.FlowEventRecordInfoInterface;
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
