package com.taotao.cloud.goods.facade.gateway.interceptor;

import com.taotao.cloud.goods.facade.gateway.model.GatewayContext;
import com.taotao.cloud.goods.facade.gateway.model.GatewayResponse;
import com.taotao.cloud.goods.facade.gateway.model.GatewayResponseStatus;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 1
 */
@Slf4j
public class ExceptionProcessInterceptor<T> implements GatewayPostInterceptor<T> {

	@SuppressWarnings("rawtypes")
	private static final ExceptionProcessInterceptor INSTANCE = new ExceptionProcessInterceptor();

	private ExceptionProcessInterceptor() {}

	@SuppressWarnings("unchecked")
	public static <T> ExceptionProcessInterceptor<T> getInstance() {
		return (ExceptionProcessInterceptor<T>) INSTANCE;
	}


	@Override
	public void intercept(GatewayResponse<T> response, GatewayContext context) {
		log.error("Gateway traceId:{}, description:{}", context.getGatewayRecord().getTraceId(),
			context.getDescription(), context.getCatchedException());
		if (context.isReachedRoute()) {
			response.setStatus(GatewayResponseStatus.P);
		} else {
			response.setStatus(GatewayResponseStatus.F);
			response.setFailCode("999999");
		}
	}

	@Override
	public boolean shouldFilter(GatewayContext gatewayContext) {
		return gatewayContext.getCatchedException() != null;
	}

}
