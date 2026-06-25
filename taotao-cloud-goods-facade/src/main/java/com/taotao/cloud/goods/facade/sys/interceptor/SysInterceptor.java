package com.taotao.cloud.goods.facade.sys.interceptor;

import com.taotao.boot.client.gateway.interceptor.TimeElapseInterceptor;
import com.taotao.boot.common.model.response.Response;
import com.taotao.boot.common.utils.json.JacksonUtils;
import com.taotao.boot.client.gateway.interceptor.GatewayPostInterceptor;
import com.taotao.boot.client.gateway.model.GatewayContext;
import com.taotao.boot.client.gateway.model.GatewayResponse;
import com.taotao.boot.client.gateway.model.GatewayResponseStatus;

/**
 * SysInterceptor
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
public class SysInterceptor<T> implements GatewayPostInterceptor<T> {
	@SuppressWarnings("rawtypes")
	private static final SysInterceptor INSTANCE = new SysInterceptor();

	private SysInterceptor() {
	}

	@SuppressWarnings("unchecked")
	public static <T> SysInterceptor<T> getInstance() {
		return (SysInterceptor<T>) INSTANCE;
	}
    @Override
    public void postIntercept( GatewayResponse<T> response, GatewayContext context ) {
		response.setGatewayRecord(context.getGatewayRecord());
		Object rawResponse = context.getRawResponse();
		if(rawResponse instanceof Response<?> response1){
			response.setStatus(GatewayResponseStatus.S);
			response.setResult((T) response1.getResult());
		}else {
			response.setResult((T) context.getRawResponse());
		}
    }

    @Override
    public boolean postShouldFilter( GatewayContext context ) {
        return context.getCatchedException() == null;
    }
}
