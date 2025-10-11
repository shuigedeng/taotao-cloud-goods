package com.taotao.cloud.goods.facade.sys.interceptor;

import com.taotao.cloud.goods.facade.gateway.interceptor.GatewayPostInterceptor;
import com.taotao.cloud.goods.facade.gateway.model.GatewayContext;
import com.taotao.cloud.goods.facade.gateway.model.GatewayResponse;

public class SysInterceptor<T> implements GatewayPostInterceptor<T> {
	@Override
	public void intercept(GatewayResponse<T> response, GatewayContext context) {

	}

	@Override
	public boolean shouldFilter(GatewayContext context) {
		return context.getCatchedException() != null;
	}
}
