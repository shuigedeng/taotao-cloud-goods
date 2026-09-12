package com.taotao.cloud.goods.application.flow.flow.callback;

import lombok.Data;

import java.io.Serializable;

@Data
public class GatewayInvokerRes<T> implements Serializable {

    /**
     * 错误码值
     */
    private String failCode;

    /**
     * 错误信息
     */
    private String failMsg;

    /**
     * 调用状态 S：成功 P：处理中 F：失败
     */
    private String status;

    /**
     * 响应报文
     */
    private T body;

    /**
     * 请求号
     */
    private String tradeBizNo;

    /**
     * 交易号
     */
    private String tradeNo;

	/**
	 * 流程号
	 */
	private String flowTraceId;

	/**
	 * 渠道
	 */
	private String opName;

	/**
	 * 渠道服务
	 */
	private String opType;

	/**
	 * 业务类型
	 */
	private String businessType;

	/**
	 * 业务子类型
	 */
	private String businessSubType;

	/**
	 * 分片键
	 */
	private String splitNo;
}
