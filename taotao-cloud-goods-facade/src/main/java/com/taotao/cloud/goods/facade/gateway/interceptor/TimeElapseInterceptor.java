package com.taotao.cloud.goods.facade.gateway.interceptor;

import com.taotao.cloud.goods.facade.gateway.model.GatewayContext;
import com.taotao.cloud.goods.facade.gateway.model.GatewayRequest;
import com.taotao.cloud.goods.facade.gateway.model.GatewayResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeElapseInterceptor<T> implements GatewayPreInterceptor<T>, GatewayPostInterceptor<T> {

	private static final String START_TIME_MARK = "START_TIME_MARK";

	@SuppressWarnings("rawtypes")
	private static final TimeElapseInterceptor INSTANCE = new TimeElapseInterceptor();

	private TimeElapseInterceptor() {}

	@SuppressWarnings("unchecked")
	public static <T> TimeElapseInterceptor<T> getInstance() {
		return (TimeElapseInterceptor<T>) INSTANCE;
	}

	@Override
	public boolean shouldFilter(GatewayContext context) {
		return true;
	}

	@Override
	public void intercept(GatewayRequest<T> request, GatewayContext context) {
		context.getExtraInfo().put(START_TIME_MARK, System.currentTimeMillis());
	}

	@Override
	public void intercept(GatewayResponse<T> response, GatewayContext context) {
		long endTime = System.currentTimeMillis();
		long startTime = (long) context.getExtraInfo().get(START_TIME_MARK);
		log.info("Gateway description:{}, cost:{} ms",
			context.getDescription(),
			endTime - startTime);
	}

}
