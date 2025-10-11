package com.taotao.cloud.goods.facade.gateway.model;

import lombok.Data;

@Data
public class GatewayResponse<T> {
    private GatewayResponseStatus status;
    private String failCode;
    private String failMsg;
    private T result;
    private GatewayRecord gatewayRecord;
}
