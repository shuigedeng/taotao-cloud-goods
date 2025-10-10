package com.taotao.cloud.goods.facade.domain;

public enum GatewayResponseStatus {
	S,
	F,
	R,
	P,
	CM,
	/*状态未知*/
	N,
	/*请求被限流*/
	L,
	;

	public static boolean isSuccess(GatewayResponse gatewayResponse) {
		return S == gatewayResponse.getStatus();
	}

	public static boolean isFinish(GatewayResponse gatewayResponse) {
		return S == gatewayResponse.getStatus() || F == gatewayResponse.getStatus();
	}
}
