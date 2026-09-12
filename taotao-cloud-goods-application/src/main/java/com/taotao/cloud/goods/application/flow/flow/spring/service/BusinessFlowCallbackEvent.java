package com.taotao.cloud.goods.application.flow.flow.spring.service;

import com.taotao.cloud.goods.application.flow.flow.callback.GatewayInvokerRes;
import lombok.Data;

import java.io.Serializable;

@Data
public class BusinessFlowCallbackEvent<R> implements Serializable {
    /**
     * 下游系统
     */
    private String gatewayInvokeOpName;

    /**
     * 下游服务
     */
    private String serviceName;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务子类型
     */
    private String businessSubType;

    /**
     * 下游返参
     */
    private GatewayInvokerRes<R> gatewayInvokerRes;

    /**
     * 削峰次数
     */
    private Integer peakLessNum = 0;
}
