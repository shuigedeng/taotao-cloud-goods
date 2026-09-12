package com.taotao.cloud.goods.application.flow.flow.treeflow.flow.nodes;

import com.taotao.cloud.goods.application.flow.flow.treeflow.context.FlowContext;
import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.Flow;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowSearchInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.NextFlowInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.service.FlowEventRecordInfoInterface;

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
