package com.taotao.cloud.goods.application.flow.flow;

import java.util.function.Supplier;

public class VirtualFlow extends FlowTree {
    protected Supplier<FlowEventRecordInfoInterface> flowEventRecordInfoInterface;
    private String type;

    public VirtualFlow(FlowInfo name, Supplier<FlowEventRecordInfoInterface> flowEventRecordInfoInterface, String type) {
        super(name, flowEventRecordInfoInterface);
        this.flowEventRecordInfoInterface = flowEventRecordInfoInterface;
        this.type = type;
    }

    @Override
    public Flow search(FlowSearchInfo flowSearchInfo) {
        throw new UnsupportedOperationException("VirtualFlow does not support search");
    }

    @Override
    public Flow firstNode() {
        return this;
    }

    @Override
    public NextFlowInfo nextFlow(FlowContext flowContext) {
        throw new UnsupportedOperationException("VirtualFlow does not support nextFlow");
    }

    @Override
    public String generateRecordId() {
        return flowEventRecordInfoInterface.get().nextRecordId();
    }

    @Override
    public String type() {
        return this.type;
    }
}
