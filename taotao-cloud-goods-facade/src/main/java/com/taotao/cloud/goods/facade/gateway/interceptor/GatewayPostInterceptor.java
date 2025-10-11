package com.taotao.cloud.goods.facade.gateway.interceptor;

import com.taotao.cloud.goods.facade.gateway.model.GatewayContext;
import com.taotao.cloud.goods.facade.gateway.model.GatewayResponse;

public interface GatewayPostInterceptor<T> {
	void intercept(GatewayResponse<T> response, GatewayContext context);

	default boolean shouldFilter(GatewayContext context) {
		return true;
	}
}
