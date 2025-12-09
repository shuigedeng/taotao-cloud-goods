package com.taotao.cloud.goods.facade.sys.invoker;

import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.ddd.gateway.invoker.GatewayInvokeBuilder;
import com.taotao.boot.ddd.gateway.model.GatewayRequest;
import com.taotao.boot.ddd.gateway.model.GatewayResponse;
import com.taotao.cloud.goods.application.dto.sys.req.DictReq;
import com.taotao.cloud.goods.facade.sys.interceptor.SysInterceptor;
import com.taotao.cloud.sys.api.dubbo.DictRpcService;
import com.taotao.cloud.sys.api.inner.DictApi;
import com.taotao.cloud.sys.api.inner.request.DictQueryApiRequest;
import com.taotao.cloud.sys.api.inner.response.DictApiResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SysInvoker {

	private final DictApi dictApi;

	@DubboReference
	private final DictRpcService dictRpcService;

	public GatewayResponse<DictApiResponse> findByCode(GatewayRequest<DictQueryApiRequest> gatewayRequest) {
		return new GatewayInvokeBuilder<DictQueryApiRequest,DictApiResponse >()
			.description("sys系统-字典信息查询")
			.gatewayRouter(request -> dictApi.findByCode(Request.from(request)))
			.addFirst(new SysInterceptor<>())
			.build()
			.invoke(gatewayRequest);
	}


}
