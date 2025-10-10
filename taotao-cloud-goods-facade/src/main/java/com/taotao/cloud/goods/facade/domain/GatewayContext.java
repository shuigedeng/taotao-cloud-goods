package com.taotao.cloud.goods.facade.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class GatewayContext {
    private String traceId;
    private String tradeNo;
    private GatewayRecord gatewayRecord;
    private Exception catchedException;
    private Object rawResponse;
    private boolean reachedRoute;
    private String description;
    private Object request;
    private Map<String, Object> extraInfo = new HashMap<>();

}
