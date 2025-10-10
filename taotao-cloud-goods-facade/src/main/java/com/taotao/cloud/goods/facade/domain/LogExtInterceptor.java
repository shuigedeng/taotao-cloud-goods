package com.taotao.cloud.goods.facade.domain;

import org.dromara.dynamictp.common.util.JsonUtil;

@slf4j
public class LogExtInterceptor implements GatewayPreInterceptor, GatewayPostInterceptor {
	public static LogExtInterceptor instance = new LogExtInterceptor();

	@Override
	public void intercept(GatewayRequest request, GatewayContext context) {
		log.info("Gateway description:{}, input:{}", context.getDescription(), request.getParam());
	}

	@Override
	public void intercept(GatewayResponse response, GatewayContext context) {
		log.info("Gateway description:{, output:{", context.getDescription(),
			substring(JsonUtil.toJson((context.getRawResponse()))));
	}

	private String substring(String str) {
		if (str.length() > 200) {
			return StringUtils.substring(str, 0, 4000) + "....";// 主要是因为有下载文件，会打很长一串
		} else {
			return str;
		}
	}

	@Override
	public boolean shouldFilter(GatewayContext context) {
		return true;
	}

}
