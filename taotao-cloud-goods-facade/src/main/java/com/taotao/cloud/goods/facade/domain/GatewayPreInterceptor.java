package com.taotao.cloud.goods.facade.domain;

public interface GatewayPreInterceptor<T> {
	void intercept(GatewayRequest<T> request, GatewayContext context);

	default boolean shouldFilter(GatewayContext context) {
		return true;
	}
}
