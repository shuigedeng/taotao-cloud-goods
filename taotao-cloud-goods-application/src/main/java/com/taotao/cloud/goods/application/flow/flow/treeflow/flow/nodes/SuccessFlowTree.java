package com.taotao.cloud.goods.application.flow.flow.treeflow.flow.nodes;

import com.taotao.cloud.goods.application.flow.flow.treeflow.context.FlowContext;
import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.Flow;
import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.FlowType;
import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.SuccessFlow;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowEventRecordInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.NextFlowInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.service.FlowEventRecordInfoInterface;
import com.taotao.cloud.goods.application.flow.flow.treeflow.status.BusinessFlowStatus;

import java.util.Objects;
import java.util.function.Supplier;

public class SuccessFlowTree extends FlowTree implements SuccessFlow {

    public SuccessFlowTree(FlowInfo name, Supplier<FlowEventRecordInfoInterface> flowEventRecordInfoInterface) {
        super(name, flowEventRecordInfoInterface);
    }

    @Override
    public NextFlowInfo nextFlow(FlowContext flowContext) {
        Objects.requireNonNull(flowContext);
        FlowEventRecordInfo flowEventRecordInfo = flowContext.getFlowEventRecordInfo();
        if (null == flowEventRecordInfo) {
            throw new IllegalArgumentException("SuccessFlowTree.nextFlow, flowEventRecordInfo is null");
        }
        if (BusinessFlowStatus.S.equals(flowEventRecordInfo.getStatus())) {
            return super.nextFlow(flowContext);
        }
        return new NextFlowInfo().noEnd();
    }

    @Override
    public String type() {
        return FlowType.SUCCESS_FLOW_TREE;
    }

    @Override
    public Flow getFlow() {
        return this;
    }
}
