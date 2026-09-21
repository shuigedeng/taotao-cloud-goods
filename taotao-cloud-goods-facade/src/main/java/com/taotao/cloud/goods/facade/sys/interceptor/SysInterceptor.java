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

	@SuppressWarnings("unchecked")
    @Override
    public void postIntercept( GatewayResponse<T> response, GatewayContext context ) {
		response.setGatewayRecord(context.getGatewayRecord());
		Object rawResponse = context.getRawResponse();

		if(rawResponse == null){
			response.setStatus(GatewayResponseStatus.F);
			response.setFailCode("99999");
			response.setFailMsg("RPC数据响应为空");
		}

		Response<?> responseData = (Response<?>)rawResponse;
		if(responseData.success()){
			response.setStatus(GatewayResponseStatus.S);
			response.setResult((T) responseData.getResult());
		}else {
			response.setStatus(GatewayResponseStatus.F);
			response.setFailCode(responseData.getCode());
			response.setFailMsg(responseData.getMessage());
		}
    }

    @Override
    public boolean postShouldFilter( GatewayContext context ) {
        return context.getCatchedException() == null;
    }
}
