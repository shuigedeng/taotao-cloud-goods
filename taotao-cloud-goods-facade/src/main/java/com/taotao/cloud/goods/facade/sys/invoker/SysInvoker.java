package com.taotao.cloud.goods.facade.sys.invoker;

import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.client.gateway.invoker.GatewayInvokeBuilder;
import com.taotao.boot.client.gateway.model.GatewayRequest;
import com.taotao.boot.client.gateway.model.GatewayResponse;
import com.taotao.cloud.goods.facade.sys.interceptor.RemoteCallExceptionInterceptor;
import com.taotao.cloud.goods.facade.sys.interceptor.SysInterceptor;
import com.taotao.cloud.sys.api.inner.dto.query.DictApiQuery;
import com.taotao.cloud.sys.api.inner.dto.response.DictApiResponse;
import com.taotao.cloud.sys.api.inner.query.DictQueryApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * SysInvoker
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@Component
@RequiredArgsConstructor
public class SysInvoker {

    private final DictQueryApi dictQueryApi;
//
//    @DubboReference
//    private final DictRpcService dictRpcService;

    public GatewayResponse<DictApiResponse> queryByCode(GatewayRequest<DictApiQuery> gatewayRequest ) {
        return new GatewayInvokeBuilder<DictApiQuery, DictApiResponse>()
                .description("sys系统-字典信息查询")
                .gatewayRouter(query -> {
					Request<DictApiQuery> request = Request.from(query);
					request.setBizNo("1111");
					request.setOrderNo("1111");
					return dictQueryApi.queryByCode(request);
				})
                .addLast(RemoteCallExceptionInterceptor.getInstance())
                .addLast(SysInterceptor.getInstance())
                .build()
                .invoke(gatewayRequest);
    }


}
