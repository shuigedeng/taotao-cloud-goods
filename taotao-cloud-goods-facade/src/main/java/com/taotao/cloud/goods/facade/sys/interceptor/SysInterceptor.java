package com.taotao.cloud.goods.facade.sys.interceptor;

import com.taotao.boot.common.model.response.Response;
import com.taotao.boot.common.utils.json.JacksonUtils;
import com.taotao.boot.ddd.gateway.interceptor.GatewayPostInterceptor;
import com.taotao.boot.ddd.gateway.model.GatewayContext;
import com.taotao.boot.ddd.gateway.model.GatewayResponse;
import com.taotao.boot.ddd.gateway.model.GatewayResponseStatus;

/**
 * SysInterceptor
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
public class SysInterceptor<T> implements GatewayPostInterceptor<T> {

    @Override
    public void intercept( GatewayResponse<T> response, GatewayContext context ) {
		System.out.println("sadfasdf");
		response.setGatewayRecord(context.getGatewayRecord());
		Object rawResponse = context.getRawResponse();
		if(rawResponse instanceof Response<?>){
			Response<?>  response1 = (Response<?>) rawResponse;
			response.setStatus(GatewayResponseStatus.S);
			response.setResult((T) response1.getResult());
		}else {
			response.setResult((T) context.getRawResponse());
		}
    }

    @Override
    public boolean shouldFilter( GatewayContext context ) {
        return context.getCatchedException() == null;
    }
}
