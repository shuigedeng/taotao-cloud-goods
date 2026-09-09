package com.taotao.cloud.goods.application.flow.flow;

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
