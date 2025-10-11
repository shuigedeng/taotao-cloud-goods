package com.taotao.cloud.goods.facade.acl;

import com.taotao.cloud.goods.facade.gateway.exception.GatewayException;
import com.taotao.cloud.goods.facade.gateway.model.GatewayRecord;
import com.taotao.cloud.goods.facade.gateway.model.GatewayRequest;
import com.taotao.cloud.goods.facade.gateway.model.GatewayResponse;
import com.taotao.cloud.goods.facade.gateway.model.GatewayResponseStatus;

public class BaseAclService {

	protected <T> T getResult(GatewayResponse<T> response){
		if(GatewayResponseStatus.isSuccess(response)){
			return response.getResult();

		}
		throw new GatewayException(Integer.valueOf(response.getFailCode()), response.getFailMsg());
	}

	protected <T> GatewayRequest<T> makeRequest(T param) {
		GatewayRequest<T> gatewayRequest = new GatewayRequest<>();
		GatewayRecord gatewayRecord = new GatewayRecord();
		gatewayRequest.setParam(param);
		gatewayRequest.setGatewayRecord(gatewayRecord);
		return gatewayRequest;
	}


}
