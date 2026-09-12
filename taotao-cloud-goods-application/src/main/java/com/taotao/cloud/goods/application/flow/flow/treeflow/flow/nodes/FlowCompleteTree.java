package com.taotao.cloud.goods.application.flow.flow.treeflow.flow.nodes;

import com.taotao.cloud.goods.application.flow.flow.treeflow.context.FlowContext;
import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.Flow;
import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.FlowType;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowEventRecordInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.NextFlowInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.service.FlowEventRecordInfoInterface;
import com.taotao.cloud.goods.application.flow.flow.treeflow.status.BusinessFlowStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Slf4j
public class FlowCompleteTree extends FlowTree {
    /**
     * flowEventRecordInfoInterfaceSupplier
     */
    protected Supplier<FlowEventRecordInfoInterface> flowEventRecordInfoInterfaceSupplier;

    /**
     * @param flowEventRecordInfoInterfaceSupplier
     */
    public FlowCompleteTree(Supplier<FlowEventRecordInfoInterface> flowEventRecordInfoInterfaceSupplier) {
        super(new FlowInfo("flowCompleteTree", "internal"), flowEventRecordInfoInterfaceSupplier);
        this.flowEventRecordInfoInterfaceSupplier = flowEventRecordInfoInterfaceSupplier;
    }

    /**
     * @return
     */
    @Override
    public String generateRecordId() {
        return flowEventRecordInfoInterfaceSupplier.get().nextRecordId();
    }

    /**
     * @param condition
     * @param flow
     */
    public void grow(Predicate<FlowContext> condition, Flow flow) {
        throw new IllegalArgumentException("VirtualFlow search, not support grow");
    }

	@Override
	public void grow( Predicate<FlowContext> condition, String desc, Flow flow) {
		throw new IllegalArgumentException("FlowCompleteTree does not support grow operation");
	}

	@Override
	public String type() {
		return FlowType.FLOW_TREE;  // 使用常量
	}

	@Override
	public FlowInfo name() {
		return new FlowInfo("flowCompleteTree", "internal");
	}

	@Override
	public NextFlowInfo nextFlow(FlowContext flowContext) {
		Objects.requireNonNull(flowContext);
		FlowEventRecordInfo recordInfo = flowContext.getFlowEventRecordInfo();
		if (null == recordInfo) {
			throw new IllegalStateException("flowEventRecordInfo is null");
		}
		recordInfo.setStatus(BusinessFlowStatus.C);
		return new NextFlowInfo().end();
	}
}
