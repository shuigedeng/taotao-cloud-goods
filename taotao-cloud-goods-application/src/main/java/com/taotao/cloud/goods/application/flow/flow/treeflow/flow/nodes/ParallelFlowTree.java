package com.taotao.cloud.goods.application.flow.flow.treeflow.flow.nodes;

import com.taotao.cloud.goods.application.flow.flow.treeflow.context.FlowContext;
import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.Flow;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.*;
import com.taotao.cloud.goods.application.flow.flow.treeflow.service.FlowEventRecordInfoInterface;
import com.taotao.cloud.goods.application.flow.flow.treeflow.status.BusinessFlowStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Supplier;

@Slf4j
public class ParallelFlowTree extends FlowTree {

	/**
	 * flowTable
	 */
	private Map<String, Flow> flowTable = new HashMap<>();

	/**
	 *
	 */
	public ParallelFlowTree(FlowInfo name, Supplier<FlowEventRecordInfoInterface> flowEventRecordInfoInterface ) {
		super(name, flowEventRecordInfoInterface);
	}

	@Override
	public Flow search( FlowSearchInfo flowSearchInfo ) {
		Objects.requireNonNull(flowSearchInfo);
		FlowInfo currentFlowInfo = flowSearchInfo.getCurrentFlow();
		if (null == currentFlowInfo) {
			throw new IllegalArgumentException("ParallelFlowTree.search, currentFlow is null");
		}
		String currentFlowIdentity = currentFlowInfo.flowIdentity();
		if (StringUtils.isEmpty(currentFlowIdentity)) {
			throw new IllegalArgumentException("ParallelFlowTree.search, flow flowIdentity is empty");
		}
		if (this.name().flowIdentity().equals(currentFlowIdentity)) {
			return this;
		}

		FlowInfo rootFlowInfo = flowSearchInfo.getRootFlow();
		if (null != rootFlowInfo) {
			String rootFlowIdentity = rootFlowInfo.flowIdentity();
			if (!StringUtils.isEmpty(rootFlowIdentity)) {
				Flow parallelSubFlow = flowTable.get(rootFlowIdentity);
				if (null != parallelSubFlow) {
					return parallelSubFlow.search(flowSearchInfo);
				}
			}
		}

		Set<String> searchedFlowSet = new HashSet<>();
		for (PredictConditionFlow predictConditionFlow : super.predictConditionFlowList) {
			Flow childFlow = predictConditionFlow.flow;
			String childFlowIdentity = childFlow.name().flowIdentity();
			if (searchedFlowSet.contains(childFlowIdentity)) {
				throw new IllegalArgumentException("ParallelFlowTree search, cycle detected: " + childFlowIdentity);
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
	public NextFlowInfo nextFlow( FlowContext flowContext ) {
		Objects.requireNonNull(flowContext);
		FlowEventRecordInfo flowEventRecordInfo = flowContext.getFlowEventRecordInfo();
		Objects.requireNonNull(flowEventRecordInfo);

		FlowEventRecordInfoInterface service = flowEventRecordInfoInterfaceSupplier.get();
		if (null == service) {
			log.warn("ParallelFlowTree nextFlow, service is null");
			return new NextFlowInfo().end();
		}

		List<FlowEventRecordInfo> subFlowEventRecordInfoL = service.getByParentId(
			flowEventRecordInfo.getRecordId(),
			flowContext.getSplitNo()
		);

		// 检查是否存在处理中的并行分支
		boolean hasProcessing = subFlowEventRecordInfoL.stream()
			.anyMatch(record -> BusinessFlowStatus.P.equals(record.getStatus()));
		if (hasProcessing) {
			return new NextFlowInfo().noEnd();
		}

		if (null == super.predictConditionFlowList || super.predictConditionFlowList.isEmpty()) {
			return new NextFlowInfo().end();
		}

		for (PredictConditionFlow predictConditionFlow : super.predictConditionFlowList) {
			if (predictConditionFlow.predicate.test(flowContext)) {
				return new NextFlowInfo().nextFlow(predictConditionFlow.flow);
			}
		}

		log.warn("ParallelFlowTree nextFlow predicate not match, flowEventRecordInfo: {}", flowEventRecordInfo);
		return new NextFlowInfo().end();
	}

	@Override
	public List<FlowEvent<FlowEventRecordInfo>> flows(FlowTreeFindInfo flowTreeFindInfo,
													  Deque<FlowEventRecordInfo> reverseFlowEventDeque,
													  String tradeNo, String mainRecordId ) {
		List<FlowEvent<FlowEventRecordInfo>> parentFlows = super.flows(flowTreeFindInfo, reverseFlowEventDeque, tradeNo,
			mainRecordId);
		if (null == parentFlows || parentFlows.isEmpty()) {
			log.warn("ParallelFlowTree.flows, parent flows is empty");
			return new ArrayList<>();
		}

		// 获取父级事件记录，设置为完成状态
		FlowEvent<FlowEventRecordInfo> parentFlowEvent = parentFlows.get(0);
		FlowEventRecordInfo parentRecord = parentFlowEvent.getFlowEventInfo();
		parentRecord.setStatus(BusinessFlowStatus.C);
		reverseFlowEventDeque.push(parentRecord);

		// 创建并行子流程事件列表
		List<FlowEvent<FlowEventRecordInfo>> parallelL = new ArrayList<>();
		FlowEventRecordInfo grandParentRecord = reverseFlowEventDeque.getLast();

		for (Map.Entry<String, Flow> entry : flowTable.entrySet()) {
			FlowEvent<FlowEventRecordInfo> subFlowEvent = new FlowEvent<>();
			FlowEventRecordInfo subRecord = new FlowEventRecordInfo();

			subFlowEvent.setFlow(entry.getValue());
			subFlowEvent.setFlowEventInfo(subRecord);

			subRecord.setParentRecordId(grandParentRecord.getRecordId());
			subRecord.setParentFlow(grandParentRecord.getCurrentFlow().getName());
			subRecord.setTradeNo(tradeNo);
			subRecord.setMainRecordId(mainRecordId);
			subRecord.setRecordId(this.generateRecordId());
			subRecord.setBusinessType(flowTreeFindInfo.getBusinessType());
			subRecord.setBusinessSubType(flowTreeFindInfo.getBusinessSubType());
			subRecord.setBusinessCode(flowTreeFindInfo.getBusinessCode());
			subRecord.setRootFlow(entry.getKey());
			subRecord.setCurrentFlow(entry.getValue().name());
			subRecord.setStatus(BusinessFlowStatus.P);
			subRecord.setType(entry.getValue().type());

			parallelL.add(subFlowEvent);
		}
		return parallelL;
	}

	/**
	 *
	 */
	@Override
	public Flow firstNode() {
		return this;
	}

	/**
	 *
	 */
	@Override
	public String type() {
		return "parallelTree";
	}

	/**
	 *
	 */
	public void parallel( Flow flow ) {
		Objects.requireNonNull(flow);
		String flowIdentity = flow.name().flowIdentity();
		if (StringUtils.isEmpty(flowIdentity)) {
			throw new IllegalArgumentException("ParallelFlow.parallel, flow flowIdentity is empty");
		}
		flowTable.put(flowIdentity, flow);
	}
}
