package com.taotao.cloud.goods.facade.domain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeElapseInterceptor implements GatewayPreInterceptor, GatewayPostInterceptor {
	private static final String START_TIME_MARK = "START_TIME_MARK";
	public static TimeElapseInterceptor instance = new TimeElapseInterceptor();

	@Override
	public boolean shouldFilter(GatewayContext context) {
		return true;
	}

	@Override
	public void intercept(GatewayRequest request, GatewayContext context) {
		context.getExtraInfo().put(START_TIME_MARK, System.currentTimeMillis());
	}

	@Override
	public void intercept(GatewayResponse response, GatewayContext context) {
		long endTime = System.currentTimeMillis();
		long startTime = (long) context.getExtraInfo().get(START_TIME_MARK);
		log.info("Gateway description:}, cost:} ms",
			context.getDescription(),
			endTime - startTime);
	}

}
