package com.taotao.cloud.goods.facade.domain;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 1
 */
@Slf4j
public class ExceptionProcessFilter<T> implements GatewayPostInterceptor<T> {

	@SuppressWarnings("rawtypes")
	private static final ExceptionProcessFilter INSTANCE = new ExceptionProcessFilter();

	private ExceptionProcessFilter() {}

	@SuppressWarnings("unchecked")
	public static <T> ExceptionProcessFilter<T> getInstance() {
		return (ExceptionProcessFilter<T>) INSTANCE;
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
