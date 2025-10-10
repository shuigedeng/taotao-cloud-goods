package com.taotao.cloud.goods.facade.domain;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dromara.dynamictp.common.util.JsonUtil;

@Slf4j
public class LogExtInterceptor<T> implements GatewayPreInterceptor<T> , GatewayPostInterceptor<T>  {

	@SuppressWarnings("rawtypes")
	private static final LogExtInterceptor INSTANCE = new LogExtInterceptor();

	private LogExtInterceptor() {}

	@SuppressWarnings("unchecked")
	public static <T> LogExtInterceptor<T> getInstance() {
		return (LogExtInterceptor<T>) INSTANCE;
	}

	@Override
	public void intercept(GatewayRequest<T>  request, GatewayContext context) {
		log.info("Gateway description:{}, input:{}", context.getDescription(), request.getParam());
	}

	@Override
	public void intercept(GatewayResponse<T>  response, GatewayContext context) {
		log.info("Gateway description:{}, output:{}", context.getDescription(),
			substring(JsonUtil.toJson((context.getRawResponse()))));
	}

	private String substring(String str) {
		if (str.length() > 200) {
			// 主要是因为有下载文件，会打很长一串
			return StringUtils.substring(str, 0, 4000) + "....";
		} else {
			return str;
		}
	}

	@Override
	public boolean shouldFilter(GatewayContext context) {
		return true;
	}

}
