package com.taotao.cloud.goods.application.flow.flow;

import lombok.Data;

@Data
public class CallBackConsumerInfo {
    /**
     * 服务名称
     */
    private String invokeOpName;

    /**
     * 类型
     */
    private String tradeType;

    /**
     * 子类型
     */
    private String tradeSubType;

    /**
     * 事件
     */
    private String event;
}
