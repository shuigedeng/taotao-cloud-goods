package com.taotao.cloud.goods.application.flow.flow;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public interface Flow extends Info {
    /**
     * name
     */
    FlowInfo name();

    Flow search(FlowSearchInfo flowSearchInfo);

    /**
     * @return
     */
    Flow firstNode();

    /**
     * @param flowContext
     * @return
     */
    NextFlowInfo nextFlow(FlowContext flowContext);

    /**
     * @return
     */
    String generateRecordId();

    /**
     * type
     */
    String type();

	default List<FlowEvent<FlowEventRecordInfo>> flows(FlowTreeFindInfo flowTreeFindInfo,
		Deque<FlowEventRecordInfo> reverseFlowEventDeque,
		String tradeNo, String mainRecordId) {
		// parallelFlow
		FlowEvent<FlowEventRecordInfo> parallelFlowRecordInfoEvent = new FlowEvent<>();
		FlowEventRecordInfo parallelFlowRecordInfo = new FlowEventRecordInfo();
		parallelFlowRecordInfoEvent.setFlowEventInfo(parallelFlowRecordInfo);
		parallelFlowRecordInfoEvent.setFlow(this);

		if (!reverseFlowEventDeque.isEmpty()) {
			FlowEventRecordInfo parentFlowEventRecordInfo = reverseFlowEventDeque.getLast();
			parallelFlowRecordInfo.setParentFlow(parentFlowEventRecordInfo.getCurrentFlow().getName());
			parallelFlowRecordInfo.setParentRecordId(parentFlowEventRecordInfo.getRecordId());
		}
		parallelFlowRecordInfo.setRecordId(this.generateRecordId());
		parallelFlowRecordInfo.setTradeNo(tradeNo);
		parallelFlowRecordInfo.setMainRecordId(mainRecordId);
		parallelFlowRecordInfo.setBusinessType(flowTreeFindInfo.getBusinessType());
		parallelFlowRecordInfo.setBusinessSubType(flowTreeFindInfo.getBusinessSubType());
		parallelFlowRecordInfo.setBusinessCode(flowTreeFindInfo.getBusinessCode());
		parallelFlowRecordInfo.setCurrentFlow(this.name());
		parallelFlowRecordInfo.setStatus(BusinessFlowStatus.P);
		parallelFlowRecordInfo.setType(this.type());

		List<FlowEvent<FlowEventRecordInfo>> flowEventRecordInfoEventL = new ArrayList<>(1);
		flowEventRecordInfoEventL.add(parallelFlowRecordInfoEvent);
		return flowEventRecordInfoEventL;
	}
	@Override
	default String info() {
		StringBuilder sb = new StringBuilder();
		FlowInfo name = name();
		return sb.append("channel[")
			.append(name.getChannel())
			.append("]")
			.append("name[")
			.append(name.getName())
			.append("]")
			.toString();
	}
}
