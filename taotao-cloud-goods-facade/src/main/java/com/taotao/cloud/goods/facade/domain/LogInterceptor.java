package com.taotao.cloud.goods.facade.domain;

import com.taotao.boot.common.extension.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.dromara.dynamictp.common.util.JsonUtil;

@Slf4j
public class LogInterceptor implements GatewayPreInterceptor, GatewayPostInterceptor {

	public static LogInterceptor instance = new LogInterceptor();

	@Override
	public void intercept(GatewayResponse response, GatewayContext context) {
		log.info("Gateway description:{}, output:{}", context.getDescription(),
			substring(JsonUtil.toJson((context.getRawResponse()))));
	}

	@Override
	public void intercept(GatewayRequest request, GatewayContext context) {
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
