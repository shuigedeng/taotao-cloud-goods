package com.taotao.cloud.goods.application.flow.flow;

public interface SuccessFlow {
    /**
     * failRetry
     */
    default FlowEvent<FlowEventRecordInfo> failRetry(FlowEventRecordInfo failRecord) {
        FlowEventRecordInfo retryRecord;
        FlowEvent<FlowEventRecordInfo> flowEvent = new FlowEvent<>();
        flowEvent.setFlowEventInfo(retryRecord = new FlowEventRecordInfo());
        flowEvent.setFlow(this.getFlow());
        retryRecord.setTradeNo(failRecord.getTradeNo());
        retryRecord.setParentRecordId(failRecord.getParentRecordId());
        retryRecord.setRecordId(this.getFlow().generateRecordId());
        retryRecord.setStatus(BusinessFlowStatus.P);
        retryRecord.setBusinessType(failRecord.getBusinessType());
        retryRecord.setBusinessSubType(failRecord.getBusinessSubType());
        retryRecord.setBusinessCode(failRecord.getBusinessCode());
        retryRecord.setRootFlow(failRecord.getRootFlow());
        retryRecord.setParentFlow(failRecord.getParentFlow());
        retryRecord.setCurrentFlow(failRecord.getCurrentFlow());
        retryRecord.setType(failRecord.getType());
        retryRecord.setMainRecordId(failRecord.getMainRecordId());
        retryRecord.setExtInfo(failRecord.getExtInfo());
        retryRecord.setSplitNo(failRecord.getSplitNo());

        return flowEvent;
    }

    Flow getFlow();
}
