package com.taotao.cloud.goods.application.flow.flow;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Slf4j
public class FlowTree implements Flow {
    /**
     * name
     */
    private FlowInfo name;

    /**
     * flowEventRecordInfoInterfaceSupplier
     */
    protected Supplier<FlowEventRecordInfoInterface> flowEventRecordInfoInterfaceSupplier;

    /**
     * flowTable
     */
    private Map<String, Flow> flowTable = new HashMap<>();

    /**
     * predictConditionFlowList
     */
    protected List<PredictConditionFlow> predictConditionFlowList = new ArrayList<>();

    /**
     * @param name
     * @param flowEventRecordInfoInterfaceSupplier
     */
    public FlowTree(FlowInfo name, Supplier<FlowEventRecordInfoInterface> flowEventRecordInfoInterfaceSupplier) {
        this.name = name;
        this.flowEventRecordInfoInterfaceSupplier = flowEventRecordInfoInterfaceSupplier;
    }

	/**
	 * condition
	 */
	public void grow( Predicate<FlowContext> condition, Flow flow) {
		Objects.requireNonNull(condition);
		Objects.requireNonNull(flow);
		final String flowIdentity = flow.name().flowIdentity();
		if (StringUtils.isEmpty(flowIdentity)) {
			throw new IllegalArgumentException("FlowTree grow,flow name is empty");
		}
		Flow pre = flowTable.putIfAbsent(flowIdentity, flow);
		if (null != pre) {
			if (!(flow instanceof VirtualFlow)) {
				throw new IllegalArgumentException("FlowTree grow,flow is repeat flow name is" + name);
			}
		}
		PredictConditionFlow predictConditionFlow = new PredictConditionFlow();
		predictConditionFlow.predictate = condition;
		predictConditionFlow.flow = flow;
		predictConditionFlowList.add(predictConditionFlow);
	}

	/**
	 * condition
	 */
	public void grow(Predicate<FlowContext> condition, String desc, Flow flow) {
		Objects.requireNonNull(condition);
		Objects.requireNonNull(flow);
		final String flowIdentity = flow.name().flowIdentity();
		if (StringUtils.isEmpty(flowIdentity)) {
			throw new IllegalArgumentException("(desc)FlowTree grow,flow name is empty");
		}
		Flow pre = flowTable.putIfAbsent(flowIdentity, flow);
		if (null != pre) {
			if (!(flow instanceof VirtualFlow)) {
				throw new IllegalArgumentException("(desc)FlowTree grow,flow is repeat flow name is" + name);
			}
		}
		PredictConditionFlow predictConditionFlow = new PredictConditionFlow();
		predictConditionFlow.predicate = PredicateWithDesc.valueOf(condition, desc);
		predictConditionFlow.flow = flow;
		predictConditionFlowList.add(predictConditionFlow);
	}

	@Override
	public String generateRecordId() {
		return flowEventRecordInfoInterfaceSupplier.get().nextRecordId();
	}

	@Override
	public String type() {
		return "tree";
	}

	@Override
	public Flow firstNode() {
		return this;
	}

	@Override
	public Flow search(FlowSearchInfo flowSearchInfo) {
		Objects.requireNonNull(flowSearchInfo);
		FlowInfo currentFlow = flowSearchInfo.getCurrentFlow();
		if (null == currentFlow) {
			throw new IllegalArgumentException("FlowTree search, currentFlow is null");
		}
		String currentFlowIdentity = currentFlow.flowIdentity();
		if (StringUtils.isEmpty(currentFlowIdentity)) {
			throw new IllegalArgumentException("FlowTree search, flow identity is empty");
		}

		String flowIdentity = this.name().flowIdentity();
		if (flowIdentity.equals(currentFlowIdentity)) {
			return this;
		}

		Set<String> searchedFlowSet = new HashSet<>();
		for (PredictConditionFlow predictConditionFlow : predictConditionFlowList) {
			Flow childFlow = predictConditionFlow.flow;
			String childFlowIdentity = childFlow.name().flowIdentity();

			if (searchedFlowSet.contains(childFlowIdentity)) {
				if (!(childFlow instanceof VirtualFlow)) {
					throw new IllegalArgumentException("FlowTree search, cycle detected: " + childFlowIdentity);
				}
				continue;  // VirtualFlow 跳过
			}

			searchedFlowSet.add(childFlowIdentity);
			Flow searchResult = childFlow.search(flowSearchInfo);
			if (null != searchResult) {
				return searchResult;
			}
		}
		return null;
	}

	@Override
	public FlowInfo name() {
		return this.name;
	}

	@Override
	public NextFlowInfo nextFlow(FlowContext flowContext) {
		Objects.requireNonNull(flowContext);
		FlowEventRecordInfo flowEventRecordInfo = flowContext.getFlowEventRecordInfo();
		if (null == flowEventRecordInfo) {
			log.warn("FlowTree nextFlow, flowEventRecordInfo is null");
			return new NextFlowInfo().end();
		}

		if (BusinessFlowStatus.P.equals(flowEventRecordInfo.getStatus())) {
			return new NextFlowInfo().noEnd();
		}

		// 处理 F、S、C 等其他状态
		if (null == predictConditionFlowList || predictConditionFlowList.isEmpty()) {
			return new NextFlowInfo().end();
		}

		for (PredictConditionFlow predictConditionFlow : predictConditionFlowList) {
			if (predictConditionFlow.predicate.test(flowContext)) {  // 修正字段名
				return new NextFlowInfo().nextFlow(predictConditionFlow.flow);
			}
		}

		log.warn("FlowTree nextFlow predicate not match, flowEventRecordInfo: {}", flowEventRecordInfo);
		return new NextFlowInfo().end();
	}

	/**
	 * 自检
	 */
	public String selfCheck(String flowIdentity, Set<String> noVirtualSet) {
		// 虚拟节点检查
		if (!(this instanceof VirtualFlow)) {
			if (noVirtualSet.contains(this.name.flowIdentity())) {
				return flowIdentity + ":" + this.name.flowIdentity() + "重复";
			} else {
				noVirtualSet.add(this.name.flowIdentity());
				List<PredictConditionFlow> predictConditionFlowList = this.predictConditionFlowList;
				if (null != predictConditionFlowList && !predictConditionFlowList.isEmpty()) {
					for (PredictConditionFlow predictConditionFlow : predictConditionFlowList) {
						Flow flow = predictConditionFlow.flow;
						if (flow instanceof FlowTree) {
							FlowTree flowTree = (FlowTree) flow;
							String checkResult = flowTree.selfCheck(flowIdentity, noVirtualSet);
							if (!StringUtils.isEmpty(checkResult)) {
								return checkResult;
							}
						}
					}
				}
			}
		} else {
			if (!noVirtualSet.contains(this.name.flowIdentity())) {
				return flowIdentity + ":" + this.name.flowIdentity() + "虚拟节点不存在";
			}
		}
		return null;  // 自检通过
	}

	/**
	 * PredictConditionFlow
	 */
	@Data
	public static class PredictConditionFlow {
		/**
		 * predicate
		 */
		protected Predicate<FlowContext> predicate;

		/**
		 * Flow
		 */
		protected Flow flow;
	}

	/**
	 * PredicateWithDesc
	 */
	public static class PredicateWithDesc implements Predicate<FlowContext> {
		/**
		 * desc
		 */
		private String desc;

		/**
		 * predicate
		 */
		private Predicate<FlowContext> predicate;

		public String desc() {
			return this.desc;
		}

		@Override
		public boolean test(FlowContext flowContext) {
			return predicate.test(flowContext);
		}

		public static PredicateWithDesc valueOf(Predicate<FlowContext> predicate, String desc) {
			PredicateWithDesc predicateWithDesc = new PredicateWithDesc();
			predicateWithDesc.predicate = predicate;
			predicateWithDesc.desc = desc;
			return predicateWithDesc;
		}
	}


}

