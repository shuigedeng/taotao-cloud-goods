package com.taotao.cloud.goods.facade.domain;

public interface GatewayPostInterceptor<T> {
	void intercept(GatewayResponse<T> response, GatewayContext context);

	default boolean shouldFilter(GatewayContext context) {
		return true;
	}
}
