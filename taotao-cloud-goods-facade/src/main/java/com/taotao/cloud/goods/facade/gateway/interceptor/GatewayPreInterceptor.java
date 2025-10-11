package com.taotao.cloud.goods.facade.gateway.interceptor;

import com.taotao.cloud.goods.facade.gateway.model.GatewayContext;
import com.taotao.cloud.goods.facade.gateway.model.GatewayRequest;

public interface GatewayPreInterceptor<T> {
	void intercept(GatewayRequest<T> request, GatewayContext context);

	default boolean shouldFilter(GatewayContext context) {
		return true;
	}
}
