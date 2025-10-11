package com.taotao.cloud.goods.facade.gateway.interceptor;

import com.taotao.boot.common.extension.StringUtils;
import com.taotao.cloud.goods.facade.gateway.model.GatewayContext;
import com.taotao.cloud.goods.facade.gateway.model.GatewayRequest;
import com.taotao.cloud.goods.facade.gateway.model.GatewayResponse;
import lombok.extern.slf4j.Slf4j;
import org.dromara.dynamictp.common.util.JsonUtil;

@Slf4j
public class LogInterceptor<T> implements GatewayPreInterceptor<T>, GatewayPostInterceptor<T> {

	@SuppressWarnings("rawtypes")
	private static final LogInterceptor INSTANCE = new LogInterceptor();

	private LogInterceptor() {}

	@SuppressWarnings("unchecked")
	public static <T> LogInterceptor<T> getInstance() {
		return (LogInterceptor<T>) INSTANCE;
	}

	@Override
	public void intercept(GatewayResponse<T> response, GatewayContext context) {
		log.info("Gateway description:{}, output:{}", context.getDescription(),
			substring(JsonUtil.toJson((context.getRawResponse()))));
	}

	@Override
	public void intercept(GatewayRequest<T> request, GatewayContext context) {
//		if (request.getParam() instanceof ContractUploadReqDto || request.getParam() instanceof ContractFileSignReqDto) {
//			log.info("Gateway description:}, input:{}", context.getDescription()
//				, request.getParam());
//		} else {
//			log.info("Gateway description:}, input:{}", context.getDescription(), JsonUtil.toJson(request.getParam()));
//		}
		log.info("Gateway description:{}, input:{}", context.getDescription(), JsonUtil.toJson(request.getParam()));
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
