package com.taotao.cloud.goods.facade.gateway.model;

import lombok.Data;

@Data
public class GatewayRecord {
    private String tradeNo;
    private String traceId;
    private String businessCode;
    private String businessFlow;
    private String businessEvent;
    private String status;
    private String changedSubTraceId;
    private String failcode;
    private String failMsg;
    private String extInfo;
}
