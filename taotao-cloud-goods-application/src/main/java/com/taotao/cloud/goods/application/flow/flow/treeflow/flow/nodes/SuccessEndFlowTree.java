package com.taotao.cloud.goods.application.flow.flow.treeflow.flow.nodes;

import com.taotao.cloud.goods.application.flow.flow.treeflow.context.FlowContext;
import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.Flow;
import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.FlowType;
import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.SuccessFlow;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowEventRecordInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowSearchInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.NextFlowInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.service.FlowEventRecordInfoInterface;
import com.taotao.cloud.goods.application.flow.flow.treeflow.status.BusinessFlowStatus;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class SuccessEndFlowTree extends FlowTree implements SuccessFlow {
    public SuccessEndFlowTree(FlowInfo name, Supplier<FlowEventRecordInfoInterface> flowEventRecordInfoInterface) {
        super(name, flowEventRecordInfoInterface);
    }

    @Override
    public Flow search(FlowSearchInfo flowSearchInfo) {
        return this.name().flowIdentity().equals(flowSearchInfo.getCurrentFlow().flowIdentity()) ? this: null;
    }

    @Override
    public Flow firstNode() {
        return this;
    }

    @Override
    public NextFlowInfo nextFlow(FlowContext flowContext) {
        FlowEventRecordInfo flowEventRecordInfo = flowContext.getFlowEventRecordInfo();
        String status = flowEventRecordInfo.getStatus();
        if (BusinessFlowStatus.S.equals(status)) {
            return new NextFlowInfo().end();
        }
        return new NextFlowInfo().noEnd();
    }

	@Override
	public void grow( Predicate<FlowContext> condition, Flow flow) {
		throw new IllegalArgumentException("SuccessEndFlowTree does not support grow operation");
	}

	@Override
	public void grow(Predicate<FlowContext> condition, String desc, Flow flow) {
		throw new IllegalArgumentException("SuccessEndFlowTree does not support grow operation");
	}

	@Override
	public String type() {
		return FlowType.SUCCESS_END_FLOW_TREE;
	}

	@Override
	public Flow getFlow() {
		return this;
	}
}
